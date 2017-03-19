package com.corner.scms.controller;

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

import com.corner.core.beans.ScManager;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.safe.MD5;
import com.corner.scms.beans.CustomerService;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.beans.vo.base.LogInVo;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.service.AuthorityService;
import com.corner.scms.utils.RandomUtils;
import com.corner.scms.utils.SendPhoneMsgUtil;



@Controller
@RequestMapping(value="/scms/authority")
public class AuthorityController extends ScmsBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(AuthorityController.class);
	
	@Autowired	AuthorityService authorityService;
	
	private final static String LOGIN_URL="/login/index";
	
	private final static String MAIN_URL="/main";
	
	private final static String ALREADY_LOGIN_URL="/scms/authority/scmsMainPage.do";

	
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
				Supplier user =(Supplier)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				if(user == null){
					return LOGIN_URL;
				}else{
					logInVo.setUserId(user.getId());
					logInVo.setUserName(user.getSupplierName());
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
			return ResponseUtils.sendMsg(false, "请登录后操作");			
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
			return ResponseUtils.sendMsg(false, "请登录后操作");			
		}
	}

	@RequestMapping(value = "/editKefuPassword.do")
	@ResponseBody
	public Object editKefuPassword(HttpServletRequest request,String newPassword, Model model) {
		if( newPassword == null || newPassword.length()<6){
			return ResponseUtils.sendMsg(false,"密码长度不能小于6位");
		}
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			ModelMsg msg = authorityService.updateKefuPassword(newPassword);
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
		return LOGIN_URL;
	}
	
	
	@RequestMapping(value = "/scMgLoginIn.do")
	@ResponseBody
	public Object scMgLoginIn(LoginRo loginRo,  HttpSession session,HttpServletRequest request,Model model) {
		//参数校验
		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword()) ) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登录！");
		}
		//防止重复登录
		ScManager scmser = getCurrentUser(ScManager.class,request);
		if (scmser != null) {
			return ResponseUtils.sendMsg(true,"用户已登录",ALREADY_LOGIN_URL);
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
			ModelMsg msg = authorityService.dealScMgSuccessLogin(loginRo,request,model);
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

	@RequestMapping(value = "/supplierLoginIn.do")
	@ResponseBody
	public Object supplierLoginIn(LoginRo loginRo,  HttpSession session,HttpServletRequest request,Model model) {
		//参数校验
		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword()) ) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登录！");
		}
		//防止重复登录
		/*Supplier sp = getCurrentUser(Supplier.class,request);
		if (sp != null) {
			return ResponseUtils.sendMsg(true,"用户已登录",ALREADY_LOGIN_URL);
		}*/
		//防止重复登录
		CustomerService sp = getCurrentUser(CustomerService.class,request);
		if (sp != null) {
			return ResponseUtils.sendMsg(true,"用户已登录",ALREADY_LOGIN_URL);
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
//			ModelMsg msg = authorityService.dealSupplierSuccessLogin(loginRo,request,model);
			ModelMsg msg = authorityService.dealKefuSuccessLogin(loginRo,request,model);
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
	
	@RequestMapping(value = "/kefuLoginIn.do")
	@ResponseBody
	public Object kefuLoginIn(LoginRo loginRo,  HttpSession session,HttpServletRequest request,Model model) {
		//参数校验
		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword()) ) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登录！");
		}
		//防止重复登录
		CustomerService sp = getCurrentUser(CustomerService.class,request);
		if (sp != null) {
			return ResponseUtils.sendMsg(true,"用户已登录",ALREADY_LOGIN_URL);
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
			ModelMsg msg = authorityService.dealKefuSuccessLogin(loginRo,request,model);
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
	
	@RequestMapping(value = "/getScmsMenu.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object getScmsMenu(HttpServletRequest request,HttpSession session,Model model) {
		return authorityService.getSubjectMenus();
	}
	
	@RequestMapping("returnUpdateLoginPassword.do")
	public String returnUpdateLoginPassword(){
		return "/login/modify-pwd";
	}
	
	
	@RequestMapping("/updateLoginPassword.do")
	@ResponseBody
	public Object updateLoginPassword(HttpServletRequest request,String usedPassword,String newPassword){
		//得到供应商对象
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		String password = authorityService.getPasswordById(supplier.getId());
		try {
			if(MD5.StringToMd5(usedPassword).equals(password)){
				authorityService.updateLoginPassword(supplier.getId(), MD5.StringToMd5(newPassword));
				return ResponseUtils.sendMsg(true, "修改成功,请牢记密码,在下次登录时使用。");
			}else{
				return ResponseUtils.sendMsg(false, "原密码输入有误！");
			}
		} catch (Exception e) {
			logger.error("修改供应商密码时出错了",e);
			return ResponseUtils.sendMsg(false, "修改失败！");
		}
	}
	
	
	/**
	 * 
	* @Title: toNextUpdatePayPassword 
	* @Description:跳转到修改支付密码页
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toNext.do")
	public String toNextUpdatePayPassword(HttpServletRequest request,HttpSession session) {
		//验证手机短信
		//验证验证码
		
		return "";
	}
	
	
	/**
	 * 
	* @Title: toUpdatePayPassword 
	* @Description:跳转到验证支付密码页
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toUpdatePayPassword.do")
	public String toUpdatePayPassword(Model model,HttpServletRequest request) throws Exception{
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		model.addAttribute("supplier", supplier);
		String mobile = supplier.getMobile();
		if(StringUtil.stringIsNullOrEmpty(mobile)||mobile.length()!=11){
			model.addAttribute("supplierMobile", "账号没有绑定手机号,请联系客服!");
		}
		mobile=mobile.substring(0, 3)+"*****"+mobile.substring(8);
		model.addAttribute("supplierMobile", mobile);
		return "user/pay-password-modify";
	}
	
	
	/**
	 * 
	* @Title: updatePayPassword 
	* @Description:修改支付密码
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/updatePayPassword.do")
	@ResponseBody
	public Object updatePayPassword() {
		try {
			
			return ResponseUtils.sendMsg(true, "");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return ResponseUtils.sendMsg(false, "");
		}
	}

	
	/**
	 * 
	* @Title: sendMsgCode 
	* @Description:发送短信验证码
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/sendMsgCode.do")
	@ResponseBody
	public Object sendMsgCode(HttpSession session,HttpServletRequest request) {
		try {
			String msgCode = RandomUtils.getRandNum(6);
			session.setAttribute(SessionConfig.USER_PHONE_MSGCODE, msgCode);
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(supplier==null){
				return ResponseUtils.sendMsg(false, "请重新登录!");
			}
			//SendPhoneMsgUtil.sendPhoneMsg(supplier.getMobile(), msgCode);
			SendPhoneMsgUtil.sendPhoneMsg("18565687394", msgCode);
			return ResponseUtils.sendMsg(true, "发送短信验证码成功!");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return ResponseUtils.sendMsg(false, "");
		}
	}

	/**
	 * 
	* @Title: toEditPassWord 
	* @Description:跳转到密码管理页面
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toEditPassWord.do")
	public String toEditPassWord(HttpServletRequest request,Model model) throws Exception{
		Supplier supplier = getCurrentUser(Supplier.class, request);
		model.addAttribute("supplier", supplier);
		return "user/password";
	}
	
}

