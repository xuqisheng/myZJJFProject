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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.model.User;
import com.corner.salesman.service.UserService;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping("/mobile/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录方法
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"login"})
	public Object login(User user, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = null;
		String userName = user.getUserName();
		String pwd = user.getPassword();
		
		try {
			logger.info("用户名：{} 密码：{} 正在登录...........", userName, pwd);
			json = userService.Login(user,request);
			//logger.info("登录返回信息为：{}", JSON.toJSONString(json));
		} catch (Exception e) {
			logger.info("用户名：{} 登录异常：{}", userName,e.getMessage());
		}
		return json;
	}
	
	/**
	 * 用户注销方法
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"logout"})
	public Object logout(User user, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			String token = user.getToken();
			JedisUtils.delObject(Constants.TOKEN_PREFIX_KEY+token);
			json.setSuccess(true);
			json.setMsg("注销成功！");
		} catch (Exception e) {
			logger.info("注销异常：{}",e.getMessage());
			json.setSuccess(false);
			json.setCode("500");
			json.setMsg("注销失败！");
		}
		return json;
	}
	
	/**
	 * 用户登录方法
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"loginFailed"})
	public Object loginFailed(User user, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			logger.info("token:{} 已经失效,请重新登录...", user.getToken());
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("会话已经失效，请重新登录！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("token已经失效，请重新登录！");
		}
		return json;
	}
	
	/**
	 * 修改用户密码
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"updateUserPwd"})
	public Object updateUserPwd(User user, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = null;
		try {
			logger.info("用户:{}提交修改信息为{}",user.getMobile(), JSON.toJSONString(user));
			json = userService.updateUserPwd(user);
			logger.info("修改密码返回信息为：{}", JSON.toJSONString(json));
		} catch (Exception e) {
			logger.info("修改密码信息异常:{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 根据部门ID获取当前部门及子部门的信息
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = {"getDeptLevelList"})
	public Object getDeptLevelList(@RequestParam(value = "deptId", required = false, defaultValue = "") String deptId){
		Json json = new Json();
		try {
			 if(StringUtils.isBlank(deptId)){
				json.setCode("500");
				json.setSuccess(false);
				json.setMsg("部门ID不能为空！");
				return json;
			 }
			
			 List levelList = userService.getDeptLevelList(deptId);
			 json.setList(levelList);
			 json.setMsg("获取部门层级列表成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			logger.info("获取部门层级列表异常:{}",e.getMessage());
			 json.setMsg("获取部门层级列表失败！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
}
