package com.corner.salesman.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.corner.salesman.common.persistence.AppPage;
import com.corner.salesman.common.utils.AppJson;
import com.corner.salesman.common.utils.EmojiFilter;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.AppSignInfo;
import com.corner.salesman.model.SignInfo;
import com.corner.salesman.service.DepartmentService;
import com.corner.salesman.service.SignInfoService;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping("/mobile/sign")
public class SignInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(SignInfoController.class);

	@Autowired
	private SignInfoService signInfoService;	
	@Autowired
	private DepartmentService deptService;
	
	
	/**
	 * 添加签到信息
	 * @param no添加公共信息tice
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addSignInfo"})
	public Object addSignInfoInfo(SignInfo signInfo, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			String signTime = signInfo.getSignTime();
			String address = signInfo.getAddress();
			Integer type = signInfo.getType();
			String picUrl = signInfo.getPicUrl();
			String remarks = signInfo.getRemarks();
			if(StringUtils.isBlank(signTime)){
				json.setMsg("签到时间不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(address)){
				json.setMsg("签到地址不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(picUrl)){
				json.setMsg("签到拍照不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(null == type){
				json.setMsg("请选择签到类型！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isNotBlank(remarks)){
				if(EmojiFilter.containsEmoji(remarks)){
					json.setMsg("签到备注不能包含特殊字符！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				}
			}

			int num = signInfoService.addSignInfo(signInfo);
			if(num>0){
				json.setMsg("添加签到信息成功！");
				json.setSuccess(true);
			}else{
				json.setSuccess(false);
				json.setCode("500");
				json.setMsg("添加签到信息失败！");
				logger.error("新增签到信息{},信息明细为：{}","失败", JSON.toJSON(signInfo));
			}
			 
		} catch (Exception e) {
			logger.info("添加签到信息异常：{}",e.getMessage());
			json.setMsg("添加签到信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		
		return json;
	}
	        
	/**
	 * 添加签到信息
	 * @param no添加公共信息tice
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addSignData"})
	public Object addSignData(SignInfo signInfo, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			String userId = signInfo.getUserId();
			String deptId = signInfo.getDeptId();
			String signTime = signInfo.getSignTime();
			String address = signInfo.getAddress();
			Integer type = signInfo.getType();
			String picUrl = signInfo.getPicUrl();
			String remarks = signInfo.getRemarks();
			String visitCust = signInfo.getVisitCust();
			String visitLine = signInfo.getVisitLine();
			
			Integer deviceType = signInfo.getDeviceType();
			Double lat = signInfo.getLatitude();
			Double lnt = signInfo.getLongitude();
			
//			address = Encodes.urlDecode(address);
//			remarks = Encodes.urlDecode(remarks);
			visitCust = Encodes.urlDecode(visitCust);
			visitLine = Encodes.urlDecode(visitLine);
			if(null != visitLine && visitLine.length()>50){
				json.setMsg("拜访路线不能超过50位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isNotBlank(visitCust)&& visitCust.length()>100){
				json.setMsg("拜访客户名称不能超过100个字符长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(null == deviceType){
				json.setMsg("设备类型不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(null == lat){
				json.setMsg("纬度不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(null == lnt){
				json.setMsg("经度不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(userId)){
				json.setMsg("用户ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(userId)){
				json.setMsg("用户ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(deptId)){
				json.setMsg("部门编码不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(signTime)){
				json.setMsg("签到时间不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(address)){
				json.setMsg("签到地址不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				//logger.info("签到校验不通过：{}", JSON.toJSON(json));
				return json;
			}
			if(StringUtils.isBlank(picUrl)){
				json.setMsg("签到拍照不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				//logger.info("签到校验不通过：{}", JSON.toJSON(json));
				return json;
			}
			if(null == type){
				json.setMsg("请选择签到类型！");
				json.setSuccess(false);
				json.setCode("500");
				//logger.info("签到校验不通过：{}", JSON.toJSON(json));
				return json;
			}
			if(StringUtils.isNotBlank(remarks)){
				if(EmojiFilter.containsEmoji(remarks)){
					json.setMsg("签到备注不能包含特殊字符！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				}
			}
			
			//转码后回填到对象中
			signInfo.setVisitCust(visitCust);
			signInfo.setVisitLine(visitLine);
			/*
			signInfo.setAddress(address);
			signInfo.setRemarks(remarks);*/

			json = signInfoService.addSignData(signInfo);
			 
		} catch (Exception e) {
			logger.info("添加签到信息异常：{}",e.getMessage());
			json.setMsg("添加签到信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		
		return json;
	}
	
	/**
	 * 获取个人签到列表信息列表
	 * @param signInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMySignList"})
	public Object getMySignList(SignInfo signInfo, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Page<SignInfo> page = signInfoService.findMySigntList(new Page<SignInfo>(request, response), signInfo);
			 AppPage<SignInfo> target = new AppPage<SignInfo>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取个人签到列表成功！");
			 json.setSuccess(true);
			//logger.info("获取签到列表信息为：", JSONArray.toJSONString(json));
		} catch (Exception e) {
			 json.setMsg("获取个人签到列表异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("获取个人签到列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 获取个人签到明细列表信息列表
	 * @param signInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMySignDetailList"})
	public Object getMySignDetailList(SignInfo signInfo, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Page<SignInfo> page = signInfoService.findMySigntDetailList(new Page<SignInfo>(request, response), signInfo);
			 AppPage<SignInfo> target = new AppPage<SignInfo>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取个人签到明细列表成功！");
			 json.setSuccess(true);
			//logger.info("获取个人签到明细列表信息为：", JSONArray.toJSONString(json));
		} catch (Exception e) {
			 json.setMsg("获取个人签到明细列表异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("获取个人签到明细列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 获取组成员签到列表信息
	 * @param signInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = {"getGroupSignList"})
	public Object getGroupSignList(SignInfo signInfo, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			//logger.info("查询签到列表信息条件：{}", JSON.toJSONString(signInfo));
	    	//1、根据用户ID查询所在用户组
			 String deptId = signInfo.getDeptId(); 
			 if(StringUtils.isBlank(deptId)){
				 deptId = deptService.findDeptIdByUserId(signInfo.getUserId());
			 }
			 signInfo.setGroupId(deptId);
			 //3、查询每个组的总人数
			 int staffTotal = deptService.getDeptUserTotal(deptId);
			 
			 //4、将查询到的数据放到list中
			 List list = new ArrayList();
			 
		     //5、根据上下班签到类型筛选对应数据
			 AppSignInfo ondutyObj = new AppSignInfo();
			 AppSignInfo offdutyObj = new AppSignInfo();
			 AppSignInfo outWorkObj = new AppSignInfo();
			 
			 signInfo.setType(1);//查询上班签到数据
			 List<SignInfo> ondutyList = signInfoService.findSigntListByGroupId(signInfo);
			 if(null != ondutyList && ondutyList.size()>0){
				 ondutyObj.setTypeName("上班签到");
				 ondutyObj.setSignList(ondutyList);
				 ondutyObj.setStaffTotal(staffTotal);
				 list.add(ondutyObj);
			 }
			 
			 signInfo.setType(2);//查询上班签到数据
			 List<SignInfo> offdutyList = signInfoService.findSigntListByGroupId(signInfo);
			 if(null != offdutyList && offdutyList.size()>0){
				 offdutyObj.setTypeName("下班签到");
				 offdutyObj.setSignList(offdutyList);
				 offdutyObj.setStaffTotal(staffTotal);
				 list.add(offdutyObj);
			 }
			 
			 signInfo.setType(3);//查询外勤签到数据
			 List<SignInfo> outWorkList = signInfoService.findOutWorkSignList(signInfo);
			 if(null != outWorkList && outWorkList.size()>0){
				 outWorkObj.setTypeName("外勤签到");
				 outWorkObj.setSignList(outWorkList);
				 outWorkObj.setStaffTotal(staffTotal);
				 list.add(outWorkObj);
			 }
			 
			 //6、再将list数据放到AppJson中（目的满足app对该场景的数据格式要求）
			 AppJson appJs = new AppJson();
			 appJs.setList(list);
			 json.setData(appJs);
			
			 json.setMsg("获取签到列表信息成功！");
			 json.setSuccess(true);
			//logger.info("获取签到列表信息为：", JSONArray.toJSONString(json));
		} catch (Exception e) {
			 json.setMsg("获取签到列表信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("获取签到列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 
	* @Title: uploadTest
	* @Author luke luke@mibodoctor.com
	* @Description: TODO(文件上传测试接口demo, 测试使用 )
	* @param @param request
	* @param @return
	* @param @throws IOException    设定文件
	* @return Object    返回类型
	* @throws
	 */ 
	/*@RequestMapping(value = "/upload.do")
	@ResponseBody
	public Object uploadTest(HttpServletRequest request,SignInfo signInfo) {
		
		logger.info("上传图片方法参数条件：{}", JSON.toJSONString(signInfo));
		List<Map<String,String>> uploadlist= null;
		try {
			uploadlist = UploadUtils.upload(request);
			//logger.info("上传图片方法参数条件uploadlist：{}", JSON.toJSONString(uploadlist));
		} catch (Exception e) {
			logger.error("图片上传失败",e);
		}
		return uploadlist;
	}*/
	
	/**
	 * 获取个人外勤签到列表信息列表
	 * @param signInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyOutSignList"})
	public Object getMyOutSignList(SignInfo signInfo, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			//外勤签到类型为3，故直接后台控制即可
			signInfo.setType(3);
			String createTime = signInfo.getCreateTime();
			String userId = signInfo.getUserId();
			if(StringUtils.isBlank(createTime)){
				json.setMsg("时间不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(null == userId){
				json.setMsg("用户ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			 Page<SignInfo> page = signInfoService.findMySigntList(new Page<SignInfo>(request, response), signInfo);
			 AppPage<SignInfo> target = new AppPage<SignInfo>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取个人外勤签到列表成功！");
			 json.setSuccess(true);
			//logger.info("获取签到列表信息为：", JSONArray.toJSONString(json));
		} catch (Exception e) {
			 json.setMsg("获取个人外勤签到列表异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("获取个人外勤签到列表异常：{}",e.getMessage());
		}
		return json;
	}
}
