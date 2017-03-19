package com.corner.salesman.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.rpc.salesman.api.service.SpGroupLineService;
import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.common.utils.IdGen;
import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.common.utils.MD5Utils;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.dao.UserMapper;
import com.corner.salesman.model.User;
import com.corner.salesman.service.UserService;

/**
 * 创建时间：2015-1-27 下午5:22:59
 * 
 * @author andy
 * @version 2.2
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private SpGroupLineService spgLineService;
	
	/**
	 * 设置签到标识回填到用户对象中
	 * @param user
	 */
	public void setSign2User(User user){
		//组装redis键的前缀
		StringBuilder signKey = new StringBuilder();
    	signKey.append(DateUtils.dateToString(new Date(), DateUtils.COMPACT_DATE_FORMAT));
    	signKey.append("_");
    	signKey.append(user.getUserId());
    	//从redis中获取是否签到标识
    	String signTimeRecord = JedisUtils.get(signKey.toString()+"_1");//签到key
    	Integer signStart = StringUtils.isNotBlank(signTimeRecord)?1:0;
    	signTimeRecord = JedisUtils.get(signKey.toString()+"_2");//签退key
    	Integer signEnd = StringUtils.isNotBlank(signTimeRecord)?1:0;
    	//回填到用户对象中
    	user.setSignStart(signStart);
    	user.setSignEnd(signEnd);
	}
	/**
	 * 用户登录方法
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public Json Login(User user,HttpServletRequest request) throws Exception {
		Json json = new Json();
		
		//如果token不为空，则检查redis中的用户信息是否存在，如果存在则直接返回
		String token = user.getToken();
		String version = user.getVersion();
		if(StringUtils.isNotBlank(token)){
			User redisUser = (User)JedisUtils.getObject(Constants.TOKEN_PREFIX_KEY+token);
			
			if(null != redisUser){
				//设置签到标识回填到用户对象中
				setSign2User(redisUser);
				//如果登录过期时间小于5小时，则延期2天
				Long ttlTime = JedisUtils.getTtl(Constants.TOKEN_PREFIX_KEY+token);
				if(null != ttlTime && ttlTime > 0 &&ttlTime<Constants.DEADLINE_CACHESECONDS){
					JedisUtils.setObject(Constants.TOKEN_PREFIX_KEY+token, redisUser, Constants.TOKEN_CACHESECONDS);
				}
				//获取最新的时间频率值返回给app(控制获取坐标的频率)
				String timeHz = JedisUtils.get(Constants.TIME_HZ_VAL_KEY);
				if(StringUtils.isBlank(timeHz)){
					timeHz = userMapper.getTimeHzThresholdVal();
					JedisUtils.set(Constants.TIME_HZ_VAL_KEY,timeHz,0);
				}
				redisUser.setTimeHz(timeHz);
				json.setData(redisUser);
				json.setMsg("登录成功！");
				json.setSuccess(true);
				return json;
			}else{
				logger.info("token:{} 已经失效,请重新登录...", token);
				json.setCode("500");
				json.setSuccess(false);
				json.setMsg("长时间未登录，请重新登录！");
				return json;
			}
		}
		
		
		//0、数据校验
		String userName = user.getUserName();
		String currentPwd = user.getPassword();
		String deviceUUID = user.getDeviceUUID();
		if(StringUtils.isBlank(userName)){
			json.setMsg("用户帐号不能为空！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}else{
			//前端是借用userName字段带过来的
			user.setMobile(userName);
		}
		if(StringUtils.isBlank(currentPwd)){
			json.setMsg("用户密码不能为空！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		if(StringUtils.isBlank(deviceUUID)){
			json.setMsg("设备UUID不能为空！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		//根据uuid查询用户信息(确保只能用绑定的手机登录)
		User userResult = userMapper.findUserBydeviceUUID(user);
		if(null == userResult){
			//1、根据用户登录名称查询用户信息
			userResult = userMapper.findUserByMobile(user);
		}
		
		//2、如果用户不存在则返回手机端提醒用户
		if(null == userResult){
			json.setMsg("用户信息不存在！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		
		currentPwd = MD5Utils.StringToMd5(currentPwd);
		String dbPwd = userResult.getPassword();
		String dbUserName = userResult.getMobile();//手机号为登录名称
		//logger.info("============登录帐号：{},当前密码：{},数据库帐号：{},数据库密码：{}",userName,currentPwd,dbUserName,dbPwd);
		
		//3、如果登录密码和数据库的密码不一致的时候返回错误提示
		if(!userName.equals(dbUserName)){
			json.setMsg("该设备已经绑定手机号，请用绑定手机号或解绑设备帐号后登录！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		//3、如果登录密码和数据库的密码不一致的时候返回错误提示
		if(!dbPwd.equals(currentPwd)){
			json.setMsg("密码输入有误！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		
		//根据设备ID判断，如果设备ID为空时，需要将当前设备ID更新到表中
		String deviceId = userResult.getDeviceUUID();
		if(StringUtils.isBlank(deviceId)){
			userMapper.updateUserDeviceInfo(user);
		}else{
			//当不为空的时候必须验证是否使用绑定的手机登录
			if(!deviceId.equals(deviceUUID)){
				json.setMsg("请使用绑定的手机登录！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
		}
		//6、设置会话ID
		userResult.setSessionid(request.getSession().getId());
		userResult.setPassword("");
		
		//获取当前表的原来的token,用于防止垃圾数据删除所用
		String hiToken = userResult.getToken();
		JedisUtils.delObject(Constants.TOKEN_PREFIX_KEY+hiToken);
		//7、设置token给两天内不需要登录的场景做准备
		token = IdGen.uuid();
		userResult.setToken(token);
		setSign2User(userResult);//设置签到标识回填到用户对象中
		
		//登录核对是否为db代表，如果返回值大于0，表示为业务代表
		try{
			String userId = userResult.getUserId();
			int total = spgLineService.queryUserIsDBType(userId);
			userResult.setBdType(total>0?"1":"0");
		}catch(Exception e){
			logger.error("登录核对是否为db代表接口异常：{}",e.getMessage());
		}
		
		JedisUtils.setObject(Constants.TOKEN_PREFIX_KEY+token, userResult, Constants.TOKEN_CACHESECONDS);
		
		//获取最新的时间频率值返回给app(控制获取坐标的频率)
		String timeHz = JedisUtils.get(Constants.TIME_HZ_VAL_KEY);
		if(StringUtils.isBlank(timeHz)){
			timeHz = userMapper.getTimeHzThresholdVal();
			JedisUtils.set(Constants.TIME_HZ_VAL_KEY,timeHz,0);
		}
		userResult.setTimeHz(timeHz);
		userResult.setVersion(version);//将当前版本更新到表中
		
		userMapper.updateLoginLastTime(userResult);
		json.setData(userResult);
		json.setMsg("登录成功！");
		json.setSuccess(true);
		return json;
	}
	
	
	

    /**
     * 修改用户密码
     * @param user
     * @return
     */
	@Override
	@Transactional(readOnly = false)
	public Json updateUserPwd(User user) throws Exception {
		Json json = new Json();
		//0、数据校验
		String mobile = user.getMobile();
		String currentPwd = user.getPassword();
		String newPwd = user.getNewPwd();
		String confirmPwd = user.getConfirmPwd();
		String deviceUUID = user.getDeviceUUID();
		if(StringUtils.isBlank(mobile)){
			json.setMsg("帐号不能为空！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		if(StringUtils.isBlank(currentPwd)){
			json.setMsg("密码不能为空！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		if(StringUtils.isBlank(newPwd)){
			json.setMsg("新密码不能为空！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		if(StringUtils.isBlank(confirmPwd)){
			json.setMsg("确认密码不能为空！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		
		if(!newPwd.equals(confirmPwd)){
			json.setMsg("新密码和确认密码不一致！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		if(newPwd.equals(currentPwd)){
			json.setMsg("新密码不能与原密码一致！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		
		if(StringUtils.isBlank(deviceUUID)){
			json.setMsg("设备UUID不能为空！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		//根据uuid查询用户信息(确保只能用绑定的手机登录)
		User userResult = userMapper.findUserBydeviceUUID(user);
		if(null == userResult){
			//1、根据用户登录名称查询用户信息
			userResult = userMapper.findUserByMobile(user);
		}
		//2、如果用户不存在则返回手机端提醒用户
		if(null == userResult){
			json.setMsg("用户信息不存在！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		
		currentPwd = MD5Utils.StringToMd5(currentPwd);
		String dbPwd = userResult.getPassword();
		//3、如果登录密码和数据库的密码不一致的时候返回错误提示
		if(!dbPwd.equals(currentPwd)){
			json.setMsg("您输入的原密码有误！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		
		String deviceId = userResult.getDeviceUUID();
		//4、验证是否使用绑定的手机登录
		if(StringUtils.isNotBlank(deviceId) && !deviceId.equals(deviceUUID)){
			json.setMsg("请使用绑定的手机号登录！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		
		//将输入密码加密后设置到对象中做更新条件
		user.setPassword(currentPwd);
		//对新密码加密回填对象供修改
		newPwd = MD5Utils.StringToMd5(newPwd);
		user.setNewPwd(newPwd);
		int num = userMapper.updateUserPwd(user);
		if(num<=0){
			json.setMsg("密码修改失败！");
			json.setSuccess(false);
			json.setCode("500");
			return json;
		}
		
		json.setMsg("密码修改成功！");
		json.setSuccess(true);
		return json;
	}
	
    /**
     * 根据用户ID检查是否是部门领导（大约0表示为领导）
     * @param user
     * @return
     */
    public int checkIsLeader(String userId) throws Exception{
    	return userDeptMapper.checkIsLeader(userId);
    }
    
    /**
     * 获取我所在的区域编码
     * @param userId
     * @return
     */
    public HashMap<String,String> getMyInArea(String userId) throws Exception{
    	return userDeptMapper.getMyInArea(userId);
    }
    
    /**
     * 根据部门ID获取当前部门及子部门的信息
     * @param deptId
     * @return
     */
    public List<HashMap<String, String>> getDeptLevelList(String deptId) throws Exception{
    	
		List<HashMap<String, String>> regionList = null;
		//如果是总裁办、销售部 和 运营部门 则直接按照最高级别的权限查看
		if("C_1".equals(deptId) || "XSB001".equals(deptId)|| "YY001".equals(deptId)|| "TEST001".equals(deptId)){
			deptId = "XSB001";
			regionList = userMapper.getKPIDeptLevelList(deptId);
			if(null != regionList && !regionList.isEmpty()){
				//for(int i=0;i<regionList.size();i++){
					HashMap<String, String> map = regionList.get(0);
					//if(i==0){
						map.put("deptName", "全国");
						map.put("deptId", "ALL");
						map.put("pid", "");
						map.put("provinceId", "");
						map.put("cityId", "");
						map.put("areaId", "");
					//}
					
					/*else{
						String provinceId = map.get("provinceId");
						String cityId = map.get("cityId");
						String areaId = map.get("areaId");
						map.put("provinceId", StringUtils.isBlank(provinceId)?"":provinceId);
						map.put("cityId", StringUtils.isBlank(cityId)?"":cityId);
						map.put("areaId", StringUtils.isBlank(areaId)?"":areaId);
					}*/
				//}
			}
			
		}else{
			regionList = userMapper.getKPIDeptLevelList(deptId);
			//获取部门下方是否没有下属部门，如果没有则使用用户来填充
			if(null != regionList && regionList.size()==1){
				List<HashMap<String, String>> spgList = userMapper.getKPIDeptLevelSpGroupList(deptId);
				regionList.addAll(spgList);
			}
		}
		
		//处理部门省市区编码为空的时候，属性返回json被mybatis过滤，特此便利回填空站位
		if(null != regionList && !regionList.isEmpty()){
			for(int i=0;i<regionList.size();i++){
				HashMap<String, String> map = regionList.get(i);
				Object provinceId = map.get("provinceId");
				Object cityId = map.get("cityId");
				Object areaId = map.get("areaId");
				Object salesmanId = map.get("salesmanId");
				map.put("provinceId", provinceId == null?"":provinceId+"");
				map.put("cityId", cityId == null?"":cityId+"");
				map.put("areaId", areaId == null?"":areaId+"");
				map.put("salesmanId", salesmanId == null?"":salesmanId+"");
			}
		}
		
		return regionList;
    }
    
    /**
     * 根据部门ID获取当前部门及下一级部门的数据信息
     * @param deptId
     * @return
     */
    public List<HashMap<String,String>> getKPIDeptLevelList(String deptId) throws Exception{
    	return userMapper.getKPIDeptLevelList(deptId);
    }
}
