package com.corner.kefu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.code.MatrixUtil;
import com.corner.core.utils.safe.MD5;
import com.corner.kefu.beans.ro.LoginRo;
import com.corner.kefu.beans.vo.AuthVo;
import com.corner.kefu.beans.vo.LogInVo;
import com.corner.kefu.config.SessionConfig;
import com.corner.kefu.service.AuthorityService;
import com.corner.kefu.service.scms.AuthMgService;

@Controller
@RequestMapping(value="/kefu/authority")
public class AuthorityController extends KefuBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(AuthorityController.class);
	
	@Autowired	AuthorityService authorityService;
	
	@Autowired AuthMgService authMgService; 
	
	private final static String LOGIN_URL="/login/index";
	
	private final static String MAIN_URL="/main";
	
	private final static String ALREADY_LOGIN_URL="/kefu/authority/scmsMainPage.do";

	
	@RequestMapping(value = "/scmsLoginPage.do")
	public String gotoLoginPage(HttpServletRequest request) throws IOException {
		return LOGIN_URL;
	}
	
	
	@RequestMapping(value = "/scmsMainPage.do")
	public String index(HttpServletRequest request, Model model) {
		Map<String, Object> map = null;
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			/**商品服务器地址**/
			SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_FASTFDSPREURL, MatrixUtil.fastfdspreurl);
			/**商品默认图片**/
			SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_DEFAULTIMG_URL, MatrixUtil.defaultImgUrl);
			
			String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
			LogInVo logInVo = new LogInVo();
			if(StringUtils.isEmpty(tag)){
				return LOGIN_URL;
			}else if(SessionConfig.USER_KEFU.equals(tag)){
				CustomerService user =(CustomerService)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				if(user == null){
					return LOGIN_URL;
				}else{
					logInVo.setUserId(user.getId()+"");
					logInVo.setUserName(user.getUserName());
					//读取菜单
					map = new HashMap<String, Object>();
					map.put("userId",getCurrentUser(CustomerService.class, request).getId());
					List<AuthVo> authVoList = authMgService.getAuthByAppIdOrRoleId(map);
					model.addAttribute("authVoList", authVoList);
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
			if(sp.getUserName().equals(loginRo.getUserName()) && sp.getPassword().equals(MD5.StringToMd5(loginRo.getPassword()))){
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

