package com.corner.salesman.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.salesman.common.utils.AppJson;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.model.MyUnderling;
import com.corner.salesman.model.User;
import com.corner.salesman.service.MyUnderlingService;

/**
 * 我的下属控制管理类
 * @author 元宝
 * @version 2016-01-26
 */
@Controller
@RequestMapping("/mobile/underling")
public class MyUnderlingController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyUnderlingController.class);
	@Autowired
	private MyUnderlingService underlingService;
	/**
	 * 获取我的下属列表信息
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyUnderlingList"})
	public Object getMyUnderlingList(MyUnderling underling, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 //检查部门ID不能为空
			 String userId = underling.getUserId();
			 if(StringUtils.isBlank(userId)){
				json.setMsg("我的下属方法用户ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 String deptId = underling.getDeptId();
			 if(StringUtils.isBlank(deptId)){
				json.setMsg("我的下属方法部门ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 String token = underling.getToken();
			 if(StringUtils.isBlank(token)){
				json.setMsg("当前会话token不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 underling = underlingService.findMyUnderlingList(underling);
			 json.setData(underling);
			 json.setMsg("获取我的下属列表信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			logger.error("获取我的下属列表信息异常:{}",e.getMessage());
			json.setMsg("获取我的下属方法异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 获取用户列表信息
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getUserList"})
	public Object getUserList(MyUnderling underling, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 List<User> userList = underlingService.getDeptUserList(underling.getUserId());
			 //6、再将list数据放到AppJson中（目的满足app对该场景的数据格式要求）
			 AppJson appJs = new AppJson();
			 appJs.setList(userList);
			 json.setData(appJs);
			 json.setMsg("获取用户列表信息成功！");
			 json.setSuccess(true);
			//logger.info("获取用户列表信息为：{}", JSONArray.toJSONString(json));
		} catch (Exception e) {
			logger.error("获取用户列表信息异常:{}",e.getMessage());
			json.setMsg("获取用户列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
}
