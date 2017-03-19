package com.corner.auth.controller;

import java.io.IOException;
import java.util.List;

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

import com.corner.auth.beans.Admin;
import com.corner.auth.beans.CustomerService;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.LoginRo;
import com.corner.auth.beans.vo.AuthVo;
import com.corner.auth.beans.vo.LogInVo;
import com.corner.auth.beans.vo.RoleVo;
import com.corner.auth.config.SessionConfig;
import com.corner.auth.service.AdmAuthService;
import com.corner.auth.service.AdmRoleService;
import com.corner.auth.service.AuthorityService;
import com.corner.auth.utils.ResponseUtils;
import com.corner.auth.utils.safe.MD5;

@Controller
@RequestMapping(value="/auth/authority")
public class AuthorityController extends AuthBaseWebController {

	@Autowired
	AuthorityService authorityService;
	@Autowired
	AdmRoleService admRoleService;
	@Autowired
	AdmAuthService admAuthService;
	
	private final static String LOGIN_URL="/login/index";
	
	private final static String MAIN_URL="/main";
	
	private final static String ALREADY_LOGIN_URL="/auth/authority/authMainPage.do";

	
	@RequestMapping(value = "/authLoginPage.do")
	public String gotoLoginPage(HttpServletRequest request) throws IOException {
		return LOGIN_URL;
	}
	
	
	@RequestMapping(value = "/authMainPage.do")
	public String index(HttpServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
			LogInVo logInVo = new LogInVo();
			if(StringUtils.isEmpty(tag)){
				return LOGIN_URL;
			}else if(SessionConfig.USER_ADMIN.equals(tag)){
				Admin user = this.getCurrentUser(Admin.class, request);
				if(user == null){
					return LOGIN_URL;
				}else{
					logInVo.setUserId(user.getId()+"");
					logInVo.setUserName(user.getUserName());
					List<RoleVo> RoleVos = admRoleService.getRoleByAppIdOrUserId(null, user.getId());
					String[] strings = new String[RoleVos.size()];
					for (int i = 0; i < RoleVos.size(); i++) {
						strings[i] = RoleVos.get(i).getId();
					}
					List<AuthVo> authVos = admAuthService.getAuthByAppIdOrRoleId(null, strings);
					model.addAttribute("authVoList", authVos);
				}
			}else if(SessionConfig.USER_KEFU.equals(tag)){
				CustomerService user =(CustomerService)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				if(user == null){
					return LOGIN_URL;
				}else{
					logInVo.setUserId(user.getId()+"");
					logInVo.setUserName(user.getUserName());
				}
			}else{
				return LOGIN_URL;
			}
			model.addAttribute("logInVo", logInVo);
			return MAIN_URL;
		}else{
			return LOGIN_URL;
		}
	}
	
	@RequestMapping(value = "/authLoginIn.do")
	@ResponseBody
	public Object authLoginIn(LoginRo loginRo,  HttpSession session,HttpServletRequest request,Model model) {
		//参数校验
		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword()) ) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登录！");
		}
		//防止重复登录
		Admin admin = getCurrentUser(Admin.class,request);
		if (admin != null) {
			if(admin.getUserName().equals(loginRo.getUserName()) && admin.getPassword().equals(MD5.StringToMd5(loginRo.getPassword()))){
				return ResponseUtils.sendMsg(true,"用户已登录",ALREADY_LOGIN_URL);
			}
		}
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
			ModelMsg msg = authorityService.dealAdminSuccessLogin(loginRo,request,model);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true, "登录成功",ALREADY_LOGIN_URL);				
			}else{				
				return ResponseUtils.sendMsg(false, msg.getMessage());				
			}
		} catch (Exception e) {
			logger.error("用户登录后处理异常：",e);
			return ResponseUtils.sendMsg(false, "登录异常");
		}
	}
	
	/**
	 * 
	* @Title: doLoginOut 
	* @Description:注销
	* @param @param request
	* @param @param model
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/doLoginOut.do")
	public Object doLoginOut(HttpServletRequest request, Model model) {
		Subject subject  = SecurityUtils.getSubject();
		logger.info("用户正在注销"+subject.getPrincipal());
		SecurityUtils.getSubject().logout();
		return LOGIN_URL;
	}
	
	
}

