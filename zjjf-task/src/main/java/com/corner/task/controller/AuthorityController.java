package com.corner.task.controller;

import com.corner.task.beans.Admin;
import com.corner.task.beans.ro.LoginRo;
import com.corner.task.beans.vo.LogInVo;
import com.corner.task.config.SessionConfig;
import com.corner.task.service.AuthorityService;
import com.corner.task.util.PropertiesCacheUtil;
import com.corner.task.util.ResponseUtils;
import com.corner.task.util.StringUtil;
import com.corner.task.util.safe.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/task/authority")
public class AuthorityController extends TaskBaseWebController{

	private final static String MAIN_URL="/main";

	private final static String ALREADY_LOGIN_URL= PropertiesCacheUtil.getValue(PropertiesCacheUtil.SYSTEM_DEFAULTLOGINMAIN ,PropertiesCacheUtil.SYSTEM);
	@Autowired
	AuthorityService authorityService;
	
	@RequestMapping(value = "/taskLoginIn.do")
	@ResponseBody
	public Object taskLoginIn(LoginRo loginRo, Model model ,HttpServletRequest request) {
		if(StringUtil.stringIsNullOrEmpty(loginRo.getUserName() , loginRo.getPassword())){
			return ResponseUtils.sendMsg(false, "缺少用户名或者密码");
		}
		loginRo.setPassword(MD5.StringToMd5(loginRo.getPassword()));
		Admin admin = authorityService.getUserByAdminCredential(loginRo);
		if(admin == null){
			return ResponseUtils.sendMsg(false, "用户名或者密码错误");
		}
		request.getSession().setAttribute(SessionConfig.USER_SESSION_KEY , admin);
		return ResponseUtils.sendMsg(true, "登陆成功" , ALREADY_LOGIN_URL);
	}

	@RequestMapping(value = "/taskMainPage.do")
	public String index(HttpServletRequest request, Model model , LoginRo loginRo) {
		LogInVo logInVo = new LogInVo();
		logInVo.setUserName(loginRo.getUserName());
		model.addAttribute("logInVo", logInVo);
		return MAIN_URL;
	}
}
