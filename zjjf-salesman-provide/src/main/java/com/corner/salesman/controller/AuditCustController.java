package com.corner.salesman.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.salesman.common.utils.Json;
import com.corner.salesman.service.AuditCustService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/** 
 * 审核客户控制分发层
 * 创建人：yuanbao 
 * 创建时间：2016-09-13
 */
@Controller
@RequestMapping(value="/mobile/audit")
public class AuditCustController{
	
	private static final Logger logger = LoggerFactory.getLogger(AuditCustController.class);
	@Autowired
	private AuditCustService auditCustService;

	/**
	 * 获取待审核客户列表信息
	 * @param userId
	 * @param deptId
	 * @param userType
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getAuditCustList"})
	public Object getAuditCustList(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "deptId", required = false, defaultValue = "") String deptId,
			@RequestParam(value = "userType", required = false, defaultValue = "") String userType,
			@RequestParam(value = "status", required = false, defaultValue = "") String status,
			@RequestParam(value = "mobile", required = false, defaultValue = "") String mobile) {

		Json json = new Json();
		List<HashMap<String,Object>> auditList = null;
		HashMap<String, Object> paramMap = Maps.newHashMap();
		
		 try{
			if(StringUtils.isBlank(userId)){
				json.setMsg("用户ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			if(StringUtils.isBlank(deptId)){
				json.setMsg("部门ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			if(StringUtils.isBlank(userType)){
				json.setMsg("用户类型不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(mobile)){
				json.setMsg("手机号不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(status)){
				json.setMsg("审核状态不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			paramMap.put("userId", userId);
			paramMap.put("deptId", deptId);
			paramMap.put("userType", userType);
			paramMap.put("mobile", mobile);
			paramMap.put("status", status);
			
			auditList = auditCustService.getAuditCustList(paramMap);
			
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("list", auditList!=null?auditList:Lists.newArrayList());
			
			json.setData(resultMap);
			json.setSuccess(true);
			json.setCode("200");
			json.setMsg("获取待审核客户列表信息成功！");
			
		 }catch(Exception e){
			 logger.error("========方法 名称为：getAuditCustList的方法,获取待审核客户列表信息异常：{}",e);
			json.setSuccess(false);
			json.setCode("500");
			json.setMsg("获取待审核客户列表信息异常！");
		 }
		
		return json;
	}
	

	/**
	 * 获取待审核客户明细信息
	 * @param userId
	 * @param deptId
	 * @param userType
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getAuditCustDetail"})
	public Object getAuditCustDetail(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "deptId", required = false, defaultValue = "") String deptId,
			@RequestParam(value = "storeId", required = false, defaultValue = "") String storeId) {

		Json json = new Json();
		Map<String, Object> paramMap = Maps.newHashMap();
		
		 try{
			if(StringUtils.isBlank(userId)){
				json.setMsg("用户ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			if(StringUtils.isBlank(deptId)){
				json.setMsg("部门ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			if(StringUtils.isBlank(storeId)){
				json.setMsg("店铺ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			paramMap.put("userId", userId);
			paramMap.put("deptId", deptId);
			paramMap.put("storeId", storeId);
			
			Map<String, Object> custDetail = auditCustService.getAuditCustDetail(paramMap);
			/*HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("resultObj", custDetail);*/
			
			json.setData(custDetail);
			json.setSuccess(true);
			json.setCode("200");
			json.setMsg("获取待审核客户明细信息成功！");
			
		 }catch(Exception e){
			 logger.error("========方法 名称为：getAuditCustDetail的方法,获取待审核客户明细信息异常：{}",e);
			json.setSuccess(false);
			json.setCode("500");
			json.setMsg("获取待审核客户明细信息异常！");
		 }
		
		return json;
	}

	/**
	 * 获取待审核客户明细信息
	 * @param userId
	 * @param deptId
	 * @param userType
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"updateStoreStatus"})
	public Object updateStoreStatus(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
			@RequestParam(value = "deptId", required = false, defaultValue = "") String deptId,
			@RequestParam(value = "storeId", required = false, defaultValue = "") String storeId,
			@RequestParam(value = "spGroupId", required = false, defaultValue = "") String spGroupId,
			@RequestParam(value = "mobile", required = false, defaultValue = "") String mobile,
			@RequestParam(value = "status", required = false, defaultValue = "") String status) {

		Json json = new Json();
		Map<String, Object> paramMap = Maps.newHashMap();
		
		 try{
			if(StringUtils.isBlank(userId)){
				json.setMsg("用户ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(userName)){
				json.setMsg("用户名称不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(deptId)){
				json.setMsg("部门ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			if(StringUtils.isBlank(storeId)){
				json.setMsg("店铺ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(mobile)){
				json.setMsg("手机号不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(status)){
				json.setMsg("审批状态不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			paramMap.put("userId", userId);
			paramMap.put("userName", userName);
			paramMap.put("deptId", deptId);
			paramMap.put("storeId", storeId);
			paramMap.put("mobile", mobile);
			paramMap.put("status", status);
			//同意的时候定格ID不为空，拒绝的时间定格ID为空
			if("1".equals(status)){
				
				if(StringUtils.isBlank(spGroupId)){
					json.setMsg("定格ID不能为空！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				}
				paramMap.put("spGroupId", Integer.parseInt(spGroupId));
			}
			
			int resultNum = auditCustService.updateStoreStatus(paramMap);

			if(resultNum>0){
				String msgStr = "1".equals(status)?"客户信息审核通过！":"拒绝客户审核成功！";
				json.setSuccess(true);
				json.setCode("200");
				json.setMsg(msgStr);
			}else{
				json.setSuccess(false);
				json.setCode("500");
				json.setMsg("客户信息审核失败！");
			}
			
		 }catch(Exception e){
			 logger.error("========方法 名称为：updateAuditCust的方法,审核客户信息异常：{}",e);
			json.setSuccess(false);
			json.setCode("500");
			json.setMsg("审核客户信息异常！");
		 }
		
		return json;
	}


}