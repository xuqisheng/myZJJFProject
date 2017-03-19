package com.corner.salesman.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.rpc.salesman.api.service.LinePlansService;
import com.corner.salesman.common.utils.ServiceException;
import com.corner.salesman.dao.SalesmanMgMapper;
import com.corner.salesman.dao.SpGroupDataMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.model.SalesmanMg;
import com.corner.salesman.model.SpGroupData;
import com.corner.salesman.model.UserDept;
import com.corner.salesman.service.SalesmanMgService;
import com.corner.scms.config.Constants;
import com.corner.scms.utils.JedisUtils;

@Service
public class SalesmanMgServiceImpl implements SalesmanMgService {

	@Autowired
	private SalesmanMgMapper salesmanMgMapper;
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private SpGroupDataMapper spGroupDataMapper;
	@Autowired
	private LinePlansService linePlansService;
	
	/**
	 * 添加业务员信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public int addSalesmanMg(SalesmanMg salesman) throws Exception {
		
		//绑定业务员同部门的关系
		UserDept userDept = new UserDept();
		userDept.setUserId(salesman.getId());
		userDept.setDeptId(salesman.getDeptId());
		userDeptMapper.insert(userDept);
		
		return salesmanMgMapper.insertSelective(salesman);
	}
	
	/**
	 * 修改业务员信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateSalesmanMg(SalesmanMg salesman) throws Exception {
		
		try {
			String oldDeptId = salesman.getOldDeptId();//原来的
			String deptId = salesman.getDeptId();//改变后的
			String token = salesman.getToken();
			String userId = salesman.getId();
			
			if(!oldDeptId.equals(deptId)){
				UserDept userDept = new UserDept();
				userDept.setUserId(salesman.getId());
				userDept.setDeptId(salesman.getOldDeptId());
				
				SpGroupData spGroupVo = new SpGroupData();
				spGroupVo.setUserId(userId);
				spGroupVo.setDeptId(oldDeptId);
				
				//删除原来部门绑定定格的BD代表
				spGroupDataMapper.delSpGroupBindDbUserByDeptId(spGroupVo);
				
				//删除旧数据（用户与部门的关系）
				userDeptMapper.deleteUserDeptByUserId(userDept);
				//绑定业务员新的部门的关系
				userDept.setDeptId(salesman.getDeptId());
				userDeptMapper.insert(userDept);
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("userId", userId);
				paramMap.put("deptId", deptId);
				paramMap.put("oldDeptId", oldDeptId);
				
				linePlansService.updateDeptIdByUserId(paramMap);
				
				//当用户类型或删除用户标识被修改的时候，app端需要重新登录
				if(StringUtils.isNotBlank(token)){
					JedisUtils.delObject(Constants.TOKEN_PREFIX_KEY+token);
				}
			}
			
			Integer isDelete = salesman.getIsDelete();
			Integer oldIsDelete = salesman.getOldIsDelete();
			Integer userType = salesman.getUserType();
			Integer oldUserType = salesman.getOldUserType();
			Integer gender = salesman.getGender();
			Integer oldGender = salesman.getOldGender();
			
			String mobile = salesman.getMobile();
			String oldMobile = salesman.getOldMobile();
			String userName = salesman.getUserName();
			String oldUserName = salesman.getOldUserName();
			String postType = salesman.getPostType();
			String oldPostType = salesman.getOldPostType();
			String password = salesman.getPassword();
			
			//当用户类型或删除用户标识被修改的时候，app端需要重新登录
			if(!oldMobile.equals(mobile) ||!oldUserName.equals(userName) ||!oldPostType.equals(postType) 
					|| gender != oldGender || oldIsDelete != isDelete || oldUserType != userType 
					|| StringUtils.isNotBlank(password)){
				JedisUtils.delObject(Constants.TOKEN_PREFIX_KEY+token);
			}
			
			salesmanMgMapper.updateByPrimaryKeySelective(salesman);
		} catch (Exception e) {
			throw new ServiceException("修改业务员信息异常！");
		}
	}
	
	/**
	 * 删除业务员信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public int delSalesmanMg(SalesmanMg salesman) throws Exception {
		return salesmanMgMapper.updateByPrimaryKeySelective(salesman);
	}

	/**
	 * 查询业务员信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public SalesmanMg findSalesmanMgById(SalesmanMg salesman) throws Exception {
		return salesmanMgMapper.findSalesmanById(salesman);
	}

	@Override
	public Pager<SalesmanMg> getPageList(SalesmanMg salesman) throws Exception {
		 List<SalesmanMg> list = salesmanMgMapper.getPageList(salesman);
		 int size = salesmanMgMapper.getPageListSize(salesman);
		return new Pager<SalesmanMg>(size, list);
	}
	
	/**
	 * 查询设备列表信息分页方法
	 * @param salesman
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pager<SalesmanMg> getDevicePageList(SalesmanMg salesman) throws Exception{
		List<SalesmanMg> list = salesmanMgMapper.getDevicePageList(salesman);
		 int size = salesmanMgMapper.getDevicePageListSize(salesman);
		return new Pager<SalesmanMg>(size, list);
	}
	
	/**
	 * 解绑设备ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateUnbindDeviceId(SalesmanMg salesman) throws Exception{

		//将解绑信息insert到设备日志记录表中
		salesmanMgMapper.insertDeviceLog(salesman);
		//解绑设备信息
		salesmanMgMapper.updateUnbindDeviceId(salesman);
	}
	
	/**
	 * 查询部门领导分页方法
	 * @param salesman
	 * @return
	 */
	@Override
	public Pager<SalesmanMg> getDeptLeaderPageList(SalesmanMg salesman) throws Exception{
		List<SalesmanMg> list = salesmanMgMapper.getDeptLeaderPageList(salesman);
		 int size = salesmanMgMapper.getDeptLeaderPageSize(salesman);
		return new Pager<SalesmanMg>(size, list);
	}
	
	/**
	 * 查询用户列表分页方法
	 * @param record
	 * @return
	 */
	public Pager<SalesmanMg> getUserPageList(SalesmanMg salesman) throws Exception{
		List<SalesmanMg> list = salesmanMgMapper.getUserPageList(salesman);
		 int size = salesmanMgMapper.getUserPageSize(salesman);
		return new Pager<SalesmanMg>(size, list);
	}
	
	/**
	 * 根据部门编码获取部门领导列表
	 * @param salesman
	 * @return
	 */
	public List<SalesmanMg> getDeptLeaderList(String deptCode) throws Exception{
		return salesmanMgMapper.getDeptLeaderList(deptCode);
	}

	/**
	 * 校验帐号是否已经存在
	 * @param mobile
	 * @return
	 */
	public int chekAccountIsExist(String mobile) throws Exception{
		return salesmanMgMapper.chekAccountIsExist(mobile);
	}
	
	/**
	 * 根据部门ID查询对应部门用户列表
	 * @param deptCode
	 * @return
	 */
	public List<SalesmanMg> getSalesmanList(SalesmanMg salesman) throws Exception{
		String userIds = salesman.getUserIds();
		if(StringUtils.isNotBlank(userIds)){
			
			StringBuffer userStr = new StringBuffer();
			String[] userSet = userIds.split(",");
			for (int i = 0; i < userSet.length; i++) {
				if(i<userSet.length-1){
					userStr.append("'").append(userSet[i]).append("'").append(",");
				}else{
					userStr.append("'").append(userSet[i]).append("'");
				}
			}
			salesman.setUserIds(userStr.toString());
		}
		
		return salesmanMgMapper.getSalesmanList(salesman);
	}
}
