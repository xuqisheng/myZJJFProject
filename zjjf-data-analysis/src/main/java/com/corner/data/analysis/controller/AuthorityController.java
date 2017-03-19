package com.corner.data.analysis.controller;
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

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.ScManager;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.beans.vo.base.LogInVo;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.service.AuthorityService;

@Controller
//@RequestMapping(value="/scms/authority")
@RequestMapping(value="/analysis/authority")
public class AuthorityController extends ScmsBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(AuthorityController.class);
	
	@Autowired	AuthorityService authorityService;
	
	private final static String LOGIN_URL="/login/index";
	
	private final static String MAIN_URL="/main";
	
	//private final static String ALREADY_LOGIN_URL="/scms/authority/scmsMainPage.do";
	private final static String ALREADY_LOGIN_URL="/analysis/authority/scmsMainPage.do";

	
	@RequestMapping(value = "/scmsLoginPage.do")
	public String gotoLoginPage(HttpServletRequest request) throws IOException {
		return LOGIN_URL;
	}
	
	@RequestMapping(value = "/scmsMainPage.do")
	public String index(HttpServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
			LogInVo logInVo = new LogInVo();
			if(StringUtils.isEmpty(tag)){
				return LOGIN_URL;
			}else if(SessionConfig.USER_SUPPLIER.equals(tag)){
				//Supplier user =(Supplier)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				CustomerService user =(CustomerService)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				if(user == null){
					return LOGIN_URL;
				}else{
					logInVo.setUserId(user.getId());
					//logInVo.setUserName(user.getSupplierName());
					logInVo.setUserName(user.getUserName());
				}
			}else if(SessionConfig.USER_SCMG.equals(tag)){
				ScManager user =(ScManager)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				if(user == null){
					return LOGIN_URL;
				}else{
					logInVo.setUserId(user.getId());
					logInVo.setUserName(user.getSupplierName());
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
	
	@RequestMapping(value = "/editScMgPassword.do")
	@ResponseBody
	public Object editScMgPassword(HttpServletRequest request,String newPassword, Model model) {
		if( newPassword == null || newPassword.length()<6){
			return ResponseUtils.sendMsg(false,"密码长度不能小于6位");
		}
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			ModelMsg msg = authorityService.updateScMgPassword(newPassword);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getMessage());				
			}else{				
				return ResponseUtils.sendMsg(false, msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false, "请登陆后操作");			
		}
	}
	
	@RequestMapping(value = "/editSupplierPassword.do")
	@ResponseBody
	public Object editSupplierPassword(HttpServletRequest request,String newPassword, Model model) {
		if( newPassword == null || newPassword.length()<6){
			return ResponseUtils.sendMsg(false,"密码长度不能小于6位");
		}
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			ModelMsg msg = authorityService.updateSupplierPassword(newPassword);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getMessage());				
			}else{				
				return ResponseUtils.sendMsg(false, msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false, "请登陆后操作");			
		}
	}

	@RequestMapping(value = "/editKefuPassword.do")
	@ResponseBody
	public Object editKefuPassword(HttpServletRequest request,CustomerService customer, Model model) {
		if(null == customer || customer.getPassword()==null || customer.getPassword().length()<6){
			return ResponseUtils.sendMsg(false,"密码长度不能小于6位");
		}
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			ModelMsg msg = authorityService.updateKefuPassword(customer);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getMessage());				
			}else{				
				return ResponseUtils.sendMsg(false, msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false, "请登陆后操作");			
		}
	}
	
	@RequestMapping(value = "/toEditPwdPage.do")
	public String toEditPwdPage(HttpServletRequest request, Model model) throws IOException {
		CustomerService customer = (CustomerService) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		model.addAttribute("customer",customer);
		return "common/editPwd";
	}
	
	@RequestMapping(value = "/doLoginOut.do")
	public Object doLoginOut(HttpServletRequest request, Model model) {
		Subject subject  = SecurityUtils.getSubject();
		logger.info("用户正在注销"+subject.getPrincipal());
		SecurityUtils.getSubject().logout();
		return LOGIN_URL;
	}
	
	
	@RequestMapping(value = "/scMgLoginIn.do")
	@ResponseBody
	public Object scMgLoginIn(LoginRo loginRo,  HttpSession session,HttpServletRequest request,Model model) {
		//参数校验
		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword()) ) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登陆！");
		}
		//防止重复登陆
		ScManager scmser = getCurrentUser(ScManager.class,request);
		if (scmser != null) {
			return ResponseUtils.sendMsg(true,"用户已登陆",ALREADY_LOGIN_URL);
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
		//处理登陆
		try {
			UsernamePasswordToken logintoken = new UsernamePasswordToken(loginRo.getUserName(), loginRo.getPassword(), true);
			try {
				SecurityUtils.getSubject().login(logintoken);
			}catch(UnknownAccountException une){
				return ResponseUtils.sendMsg(false, "对不起您还没有注册");
			}catch (Exception e) {
				logger.error("用户登陆异常：{}",loginRo.getUserName(),e);
				return ResponseUtils.sendMsg(false, "用户名或密码不正确");
			}
			ModelMsg msg = authorityService.dealScMgSuccessLogin(loginRo,request,model);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true, "登陆成功",ALREADY_LOGIN_URL);				
			}else{				
				return ResponseUtils.sendMsg(false, msg.getMessage());				
			}
		} catch (Exception e) {
			logger.error("用户登陆后处理异常：",e);
			return ResponseUtils.sendMsg(false, "登陆异常");
		}
	}

	@RequestMapping(value = "/supplierLoginIn.do")
	@ResponseBody
	public Object supplierLoginIn(LoginRo loginRo,  HttpSession session,HttpServletRequest request,Model model) {
		//参数校验
		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword()) ) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登陆！");
		}
		//防止重复登陆
		CustomerService sp = getCurrentUser(CustomerService.class,request);
		if (sp != null) {
			return ResponseUtils.sendMsg(true,"用户已登陆",ALREADY_LOGIN_URL);
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
		//处理登陆
		try {
			UsernamePasswordToken logintoken = new UsernamePasswordToken(loginRo.getUserName(), loginRo.getPassword(), true);
			try {
				SecurityUtils.getSubject().login(logintoken);
			}catch(UnknownAccountException une){
				return ResponseUtils.sendMsg(false, "对不起您还没有注册");
			}catch (Exception e) {
				logger.error("用户登陆异常：{}",loginRo.getUserName(),e);
				return ResponseUtils.sendMsg(false, "用户名或密码不正确");
			}
			//ModelMsg msg = authorityService.dealSupplierSuccessLogin(loginRo,request,model);
			ModelMsg msg = authorityService.dealKefuSuccessLogin(loginRo,request,model);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true, "登陆成功",ALREADY_LOGIN_URL);				
			}else{				
				return ResponseUtils.sendMsg(false, msg.getMessage());				
			}
		} catch (Exception e) {
			logger.error("用户登陆后处理异常：",e);
			return ResponseUtils.sendMsg(false, "登陆异常");
		}
	}	
	
	@RequestMapping(value = "/kefuLoginIn.do")
	@ResponseBody
	public Object kefuLoginIn(LoginRo loginRo,  HttpSession session,HttpServletRequest request,Model model) {
		//参数校验
		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword()) ) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登陆！");
		}
		//防止重复登陆
		CustomerService sp = getCurrentUser(CustomerService.class,request);
		if (sp != null) {
			return ResponseUtils.sendMsg(true,"用户已登陆",ALREADY_LOGIN_URL);
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
		//处理登陆
		try {
			UsernamePasswordToken logintoken = new UsernamePasswordToken(loginRo.getUserName(), loginRo.getPassword(), true);
			try {
				SecurityUtils.getSubject().login(logintoken);
			}catch(UnknownAccountException une){
				return ResponseUtils.sendMsg(false, "对不起您还没有注册");
			}catch (Exception e) {
				logger.error("用户登陆异常：{}",loginRo.getUserName(),e);
				return ResponseUtils.sendMsg(false, "用户名或密码不正确");
			}
			ModelMsg msg = authorityService.dealKefuSuccessLogin(loginRo,request,model);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true, "登陆成功",ALREADY_LOGIN_URL);				
			}else{				
				return ResponseUtils.sendMsg(false, msg.getMessage());				
			}
		} catch (Exception e) {
			logger.error("用户登陆后处理异常：",e);
			return ResponseUtils.sendMsg(false, "登陆异常");
		}
	}	
	
	@RequestMapping(value = "/getScmsMenu.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object getScmsMenu(HttpServletRequest request,HttpSession session,Model model) {
		return authorityService.getSubjectMenus();
	}
}

