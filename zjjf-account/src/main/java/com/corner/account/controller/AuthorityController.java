package com.corner.account.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.account.beans.ro.LoginRo;
import com.corner.account.config.SessionConfig;
import com.corner.account.service.AuthorityService;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.ResponseUtils;

@Controller
@RequestMapping(value="/account/authority")
public class AuthorityController extends AccountBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(AuthorityController.class);
	
	@Autowired	AuthorityService authorityService;

	
	@RequestMapping(value = "/AccountLoginPage.do")
	public String gotoLoginPage(HttpServletRequest request) throws IOException {
		return "Login";
	}
	
	@RequestMapping(value = "/AccountIndexPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户登录主页");
		Accounter user =(Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		if(user == null){
			return "Login";
		}
		model.addAttribute("currentAccounter", user);
		return "Index";
	}
	
	@RequestMapping(value = "/editpassword.do")
	@ResponseBody
	public Object editpassword(HttpServletRequest request,String newPassword, Model model) {
		if( newPassword == null || newPassword.length()<6){
			return ResponseUtils.sendMsg(false,"密码长度不能小于6位");
		}
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			ModelMsg msg = authorityService.updatePassword(newPassword);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getMessage());				
			}else{				
				return ResponseUtils.sendMsg(false, msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false, "请登录后操作");			
		}
	}
	
	@RequestMapping(value = "/doLoginOut.do")
	public Object doLoginOut(HttpServletRequest request, Model model) {
		Subject subject  = SecurityUtils.getSubject();
		logger.info("用户正在注销"+subject.getPrincipal());
		SecurityUtils.getSubject().logout();
		return "Login";
	}
	
	
	@RequestMapping(value = "/doLoginIn.do")
	@ResponseBody
	public Object loginIn(LoginRo loginRo,  HttpSession session,HttpServletRequest request,Model model) {
		//参数校验
		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword()) ) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登录！");
		}
		//防止重复登录
		Accounter accounter = getCurrentUser(request);
		if (accounter != null) {
			return ResponseUtils.sendMsg(true,"用户已登录","/AccountIndexPage.do");
		}
		//校验验证码
		if (StringUtils.isEmpty(loginRo.getCheckCode())) {
			session.removeAttribute(SessionConfig.USER_SESSION_CODE);
			return ResponseUtils.sendMsg(false, "验证码不能为空");
		}
		String serverCode = (String) session.getAttribute(SessionConfig.USER_SESSION_CODE);
		if (serverCode == null || !serverCode.toLowerCase().equals(loginRo.getCheckCode().trim().toLowerCase())) {
			session.removeAttribute(SessionConfig.USER_SESSION_CODE);
			return ResponseUtils.sendMsg(false, "验证码错误");
		}
		session.removeAttribute(SessionConfig.USER_SESSION_CODE);
		//处理登录
		try {
			UsernamePasswordToken logintoken = new UsernamePasswordToken(loginRo.getUserName(), loginRo.getPassword(), true);
			try {
				SecurityUtils.getSubject().login(logintoken);
			}catch(UnknownAccountException une){
				return ResponseUtils.sendMsg(false, "对不起您还没有注册");
			}catch (Exception e) {
				logger.error("用户登录异常：{}",loginRo.getUserName(),e);
				return ResponseUtils.sendMsg(false, "用户名或密码不正确");
			}
			ModelMsg msg = authorityService.dealSuccessLogin(loginRo,request,model);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true, "登录成功");				
			}else{				
				return ResponseUtils.sendMsg(false, msg.getMessage());				
			}
		} catch (Exception e) {
			logger.error("用户登录后处理异常：",e);
			return ResponseUtils.sendMsg(false, "登录异常");
		}
	}
	
	@RequestMapping(value = "/getAccountMenu.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object getAccountMenu(HttpServletRequest request,HttpSession session,Model model) {
		return authorityService.getSubjectMenus();
	}
}

