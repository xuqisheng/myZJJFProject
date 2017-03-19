package com.corner.scms.controller;

import com.corner.core.beans.PlantProtocol;
import com.corner.core.beans.SignResult;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.User;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.code.MatrixUtil;
import com.corner.core.utils.safe.MD5;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.beans.vo.base.LogInVo;
import com.corner.scms.beans.vo.sc.ScmsAuthVo;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.service.AuthMgService;
import com.corner.scms.service.AuthorityService;
import com.corner.scms.service.sp.ScmsPlantProtocoMgService;
import com.corner.scms.service.sp.ScmsSpSignResultMgService;
import com.corner.scms.service.sp.ScmsSupplierMgService;
import com.corner.scms.task.LogOutRunnable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping(value="/scms/authority")
public class AuthorityController extends ScmsBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(AuthorityController.class);
	
	@Autowired	AuthorityService authorityService;

	@Autowired ScmsSupplierMgService scmsSupplierMgService;


	@Autowired AuthMgService authMgService;

	@Autowired ScmsPlantProtocoMgService scmsPlantProtocoMgService;

	@Autowired ScmsSpSignResultMgService scmsSpSignResultMgService;
	
	@Autowired ThreadPoolTaskExecutor taskExecutor;
	
	private final static String LOGIN_URL="/login/index";
	private final static String MAIN_URL="/main";
	
	private final static String ALREADY_LOGIN_URL="/scms/authority/scmsMainPage.do";

	/**	批发商登陆入口	**/
	@RequestMapping(value = "/scmsLoginPage.do")
	public String scmsLoginPage(HttpServletResponse response) throws IOException {
		return LOGIN_URL;
	}
	@RequestMapping(value = "/scmsMainPage.do")
	public String index(HttpServletRequest request, Model model) throws Exception {
		//登录时角色参数
		String roleId = "";
		//登录时上级菜单权限的id
		String appId = "";
		//登录页面传过来的标识
		String loginStr = request.getParameter("loginStr");
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
			}else if(SessionConfig.USER_SUPPLIER.equals(tag)){
				Supplier user =(Supplier)subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
				if(user == null){
					return LOGIN_URL;
				}else{
					roleId = user.getSupplierType() == 2 ? "Scms_Supply_User_Role_Local" : "Scms_Supply_User_Role";
					appId = "3";
					logInVo.setUserId(user.getId());
					logInVo.setUserName(user.getSupplierName());
				}

				//获取批发商需要签署的最新版协议
				PlantProtocol plantProtocol = scmsPlantProtocoMgService.getLastPlantProtocol();
				
				if(plantProtocol!=null){
					Map<String, Object>map = new HashMap<String,Object>();
					map.put("supplier", user);
					map.put("plantProtocol",plantProtocol);
					List<SignResult> list = scmsSpSignResultMgService.selectSignResult(map);
					if(list==null||list.size()==0){
						//model.addAttribute("plantProtocolContent", plantProtocol.getContent());
						model.addAttribute("plantProtocol", plantProtocol);
						model.addAttribute("noSign", true);
					}else {
						model.addAttribute("noSign", false);
					}
				}
			}else{
				return LOGIN_URL;
			}
			model.addAttribute("loginStr", loginStr);
			//登录后左侧菜单数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			List<ScmsAuthVo> ScmsAuthVoList = authMgService.getAuthByAppIdOrRoleId(map);
			model.addAttribute("ScmsAuthVoList", ScmsAuthVoList);
			model.addAttribute("logInVo", logInVo);
			return MAIN_URL;
		}else{
			return LOGIN_URL;
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

	@RequestMapping(value = "/doLoginOut.do")
	public Object doLoginOut(HttpServletRequest request, Model model) {
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);		
			logger.info("用户正在注销"+subject.getPrincipal());
			//subject.getSession().removeAttribute(sessionKey);
			taskExecutor.execute(new LogOutRunnable(subject));
			if(SessionConfig.USER_SUPPLIER.equals(tag)){
				return "redirect:/scms/authority/scmsLoginPage.do";
			}else{
				return "redirect:/scms/authority/scmsLoginPage.do";
			}
		}
		return LOGIN_URL;
	}
	/**
	 * 批发商登录
	* @Title
	* @Description: TODO 
	* @param @param loginRo
	* @param @param session
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年3月4日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping(value = "/supplierLoginIn.do")
	@ResponseBody
	public Object supplierLoginIn(LoginRo loginRo,  HttpSession session,HttpServletRequest request,Model model) {
		loginRo.setUserName(loginRo.getUserName().trim());
		//参数校验
		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword()) ) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登录！");
		}
		//防止重复登录
		User sp = getCurrentUser(User.class,request);
		if (sp != null) {
			if(sp.getMobile().equals(loginRo.getUserName()) && sp.getPassword().equals(MD5.StringToMd5(loginRo.getPassword()))){
				return ResponseUtils.sendMsg(true,"用户已登录",ALREADY_LOGIN_URL);
			}
		}
//		查出批发商用户的id
		String userType = "";
		LoginRo loginRo2 = new LoginRo();
		loginRo2.setUserName(loginRo.getUserName());
		String id = "";
		try {
			id = authorityService.getUserIdByLoginRo("user", loginRo2);
			if(StringUtil.stringIsNullOrEmpty(id)){
				id = authorityService.getUserIdByLoginRo("ERPWarehouseUser", loginRo2);
				if(StringUtil.stringIsNullOrEmpty(id)){
					return ResponseUtils.sendMsg(false, "用户名或密码不正确");
				}
				userType = "ERPWarehouseUser";
			}
		}catch (Exception e){
			return ResponseUtils.sendMsg(false, "用户名或密码不正确");
		}

		//校验验证码
		/*
		if (StringUtils.isEmpty(loginRo.getCheckCode())) {
			session.removeAttribute(SessionConfig.USER_SESSION_CODE);
			return ResponseUtils.sendMsg(false, "验证码不能为空");
		}
		String serverCode = (String) session.getAttribute(SessionConfig.USER_SESSION_CODE);
		if (serverCode == null || !serverCode.toLowerCase().equals(loginRo.getCheckCode().trim().toLowerCase())) {
			session.removeAttribute(SessionConfig.USER_SESSION_CODE);
			return ResponseUtils.sendMsg(false, "验证码错误");
		}*/
		//处理登录
		
		try {
			
			UsernamePasswordToken logintoken = new UsernamePasswordToken(id, loginRo.getPassword(), true);
			try {
				SecurityUtils.getSubject().login(logintoken);
			}catch(UnknownAccountException une){
				return ResponseUtils.sendMsg(false, "对不起您还没有注册");
			}catch (Exception e) {
				logger.error("用户登录异常：{}",loginRo.getUserName(),e);
				return ResponseUtils.sendMsg(false, "用户名或密码不正确");
			}
			loginRo.setUserName(id);
			ModelMsg msg = authorityService.dealSupplierSuccessLogin(loginRo,request,model , userType);
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
}

