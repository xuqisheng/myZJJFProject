/**   
* @Title: ScmsPasswordController.java 
* @Package com.corner.scms.controller 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月24日 上午11:11:48 
* @version V1.0   
*/

package com.corner.scms.controller;

import com.corner.core.beans.Supplier;
import com.corner.core.beans.User;
import com.corner.core.pay.wx.util.MD5Util;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.safe.MD5;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.beans.vo.base.LogInVo;
import com.corner.scms.beans.vo.sc.ScmsAuthVo;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.service.AuthMgService;
import com.corner.scms.service.AuthorityService;
import com.corner.scms.service.UserService;
import com.corner.scms.service.sp.ScmsSupplierMgService;
import com.corner.scms.utils.RandomUtils;
import com.corner.scms.utils.SendPhoneMsgUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: ScmsPasswordController 
* @Description:密码相关控制器
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年2月24日 上午11:11:48 
*  
*/

@Controller
@RequestMapping("/scms/password")
public class ScmsPasswordController extends ScmsBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(ScmsPasswordController.class);

	@Autowired
	ScmsSupplierMgService scmsSupplierMgService;
	@Autowired
	UserService userService;
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	AuthMgService authMgService;

	/**
	 * 
	* @Title: forgerPassword 
	* @Description:忘记支付密码/或设置密码
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/forgerPassword.do")
	public String forgerPassword(Model model,HttpServletRequest request) {
		model.addAttribute("loginStr", "supply");
		Map<String, Object>map = new HashMap<String,Object>();
		map.put("roleId", "Scms_Supply_User_Role");
		map.put("upId", "scms_supply");
		List<ScmsAuthVo> ScmsAuthVoList = authMgService.getAuthByAppIdOrRoleId(map);
		model.addAttribute("ScmsAuthVoList", ScmsAuthVoList);
		LogInVo logInVo = new LogInVo();
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
			if(SessionConfig.USER_SUPPLIER.equals(tag)){
				Supplier supplier = this.getCurrentUser(Supplier.class, request);
				logInVo.setUserId(supplier.getId());
				logInVo.setUserName(supplier.getSupplierName());
			}
		}
		
		model.addAttribute("logInVo", logInVo);
		model.addAttribute("forget", true);
		model.addAttribute("str", "supply");
		return "main";
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
	public Object sendMsgCode(HttpSession session, HttpServletRequest request) {
		try {
			String msgCode = RandomUtils.getRandNum(6);
			session.setAttribute(SessionConfig.USER_PHONE_MSGCODE, msgCode);
			Subject subject  = SecurityUtils.getSubject();
			String mobile = "";
			if(subject.isAuthenticated()){
				String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
				if(SessionConfig.USER_SUPPLIER.equals(tag)){
					Supplier supplier = this.getCurrentUser(Supplier.class, request);
					mobile = supplier.getMobile();
				}
			}
			String phoneCode = (String) session.getAttribute("phoneCode");
			if(StringUtil.stringIsNullOrEmpty(phoneCode)){
				String content = "您正在设置支付密码，校验码"+msgCode+"，请于30分钟内输入，工作人员不会向您索取，请勿泄露。";
				SendPhoneMsgUtil.sendPhoneMsg(mobile, content);
				session.setAttribute("phoneCode", msgCode);
				//session.setAttribute("phoneCodeTime", new Date());
			}else {
				return ResponseUtils.sendMsg(true, "短信验证码已经发送!");	
			}
			return ResponseUtils.sendMsg(true, "发送短信验证码成功!");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return ResponseUtils.sendMsg(false, "");
		}
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
	public String toUpdatePayPassword(Model model, HttpServletRequest request) throws Exception {
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
			String mobile = "";
			if(SessionConfig.USER_SUPPLIER.equals(tag)){
				Supplier supplier = this.getCurrentUser(Supplier.class, request);
				mobile = supplier.getMobile();
				if(StringUtil.stringIsNullOrEmpty(supplier.getPayPassword())){
					model.addAttribute("noPayPassword", true);
				}
			}
			if (StringUtil.stringIsNullOrEmpty(mobile) || mobile.length() != 11) {
				model.addAttribute("supplierMobile", "账号没有绑定手机号,请联系客服!");
			}
			mobile = mobile.substring(0, 3) + "*****" + mobile.substring(8);
			model.addAttribute("supplierMobile", mobile);
		}
		return "user/pay-password-modify-check";
	}

	
	/**
	 * 
	* @Title: validatePhoneCode 
	* @Description: 
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/validatePhoneCode.do")
	@ResponseBody
	public Object validatePhoneCode(HttpServletRequest request, HttpSession session) {
		// 验证手机短信
		String phoneCode = request.getParameter("phoneCode");
		if (StringUtil.stringIsNullOrEmpty(phoneCode)) {
			return ResponseUtils.sendMsg(false, "请输入手机验证码!");
		}
		if (phoneCode.length() != 6) {
			return ResponseUtils.sendMsg(false, "请输入6位手机验证码!");
		}
		/*Date phoneCodeDate = (Date) session.getAttribute("phoneCodeTime");
		if (phoneCodeDate == null) {
			return ResponseUtils.sendMsg(false, "请点击获取短信校验码!");
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(phoneCodeDate);
		Calendar now = Calendar.getInstance();
		Long interval = now.getTimeInMillis() - calendar.getTimeInMillis();
		if (interval > 60000) {
			return ResponseUtils.sendMsg(false, "请再次获取短信校验码!");
		}*/
		String sessionPhoneCode = (String) session.getAttribute("phoneCode");
		if (StringUtil.stringIsNullOrEmpty(sessionPhoneCode)) {
			return ResponseUtils.sendMsg(false, "请点击获取短信校验码!");
		}
		if (!sessionPhoneCode.equals(phoneCode)) {
			return ResponseUtils.sendMsg(false, "短信校验码不正确!");
		}
		//session.removeAttribute("phoneCodeTime");
		session.removeAttribute("phoneCode");
		return ResponseUtils.sendMsg(true);
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
	public String toNextUpdatePayPassword(HttpServletRequest request, HttpSession session) {
		// 验证手机短信
		/*String phoneCode = request.getParameter("phoneCode");
		if (StringUtil.stringIsNullOrEmpty(phoneCode)) {
			return "redirect:/scms/password/toUpdatePayPassword.do?errorMessage=5";
		}
		if (phoneCode.length() != 6) {
			return "redirect:/scms/password/toUpdatePayPassword.do?errorMessage=4";
		}
		Date phoneCodeDate = (Date) session.getAttribute("phoneCodeTime");
		if (phoneCodeDate == null) {
			return "redirect:/scms/password/toUpdatePayPassword.do?errorMessage=2";
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(phoneCodeDate);
		Calendar now = Calendar.getInstance();
		Long interval = now.getTimeInMillis() - calendar.getTimeInMillis();
		if (interval > 60000) {
			return "redirect:/scms/password/toUpdatePayPassword.do?errorMessage=3";
		}
		String sessionPhoneCode = (String) session.getAttribute("phoneCode");
		if (StringUtil.stringIsNullOrEmpty(sessionPhoneCode)) {
			return "redirect:/scms/password/toUpdatePayPassword.do?errorMessage=2";
		}
		if (!sessionPhoneCode.equals(phoneCode)) {
			return "redirect:/scms/password/toUpdatePayPassword.do?errorMessage=1";
		}
		session.removeAttribute("phoneCodeTime");
		session.removeAttribute("phoneCode");*/
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
	public Object updatePayPassword(HttpServletRequest request) {
		try {
			String newpwd = request.getParameter("newpwd");// 新密码
			String renewpwd = request.getParameter("renewpwd");// 确认密码
			String mess = request.getParameter("mess");// 密码强度 0-弱 1-中 2-强
			String referer = request.getHeader("Referer");// 浏览器后退
			if (!referer.contains("toNext.do")) {
				return ResponseUtils.sendMsg(false, "504");
			}
			if (StringUtil.stringIsNullOrEmpty(newpwd) || StringUtil.stringIsNullOrEmpty(renewpwd)
					|| StringUtil.stringIsNullOrEmpty(mess)) {
				return ResponseUtils.sendMsg(false, "502");// 缺少必要参数
			}
			if (!newpwd.equals(renewpwd)) {
				return ResponseUtils.sendMsg(false, "501");// 2次输入的密码不一样
			}
			// TODO 密码是否合法检测

			newpwd = MD5Util.MD5Encode(newpwd, "UTF-8");
			Subject subject  = SecurityUtils.getSubject();
			if(subject.isAuthenticated()){
				String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
				int res = 0;
				if(SessionConfig.USER_SUPPLIER.equals(tag)){
					Supplier supplier = this.getCurrentUser(Supplier.class, request);
					supplier.setPayPassword(newpwd);
					supplier.setCol1(mess);
					res = scmsSupplierMgService.updateSupplier(supplier);
				}
				if(res == 0){
					return ResponseUtils.sendMsg(false, "修改失败");
				}
			}
			return ResponseUtils.sendMsg(true, "");
		} catch (Exception e) {
			logger.error("", e);
			return ResponseUtils.sendMsg(false, e.toString());// 支付失败,程序异常
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
	public String toEditPassWord(HttpServletRequest request, Model model) throws Exception {
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
			if(SessionConfig.USER_SUPPLIER.equals(tag)){
				Supplier supplier = getCurrentUser(Supplier.class, request);
				supplier = scmsSupplierMgService.selectByPrimaryKey(supplier.getId());
				model.addAttribute("supplier", supplier);
			}
		}
		return "user/password";
	}

	// 返回修改登录密码页面
	@RequestMapping("returnUpdateLoginPassword.do")
	public String returnUpdateLoginPassword(HttpServletRequest request, Model model) {
		return "/user/password-modify";
	}
	
	//判断原密码是否正确
	@RequestMapping("chickPasswordIsOk.do")
	@ResponseBody
	public Object chickPasswordIsOk(String str,HttpServletRequest request,String usedPassword){
		String password = "";
		try {
			Subject subject  = SecurityUtils.getSubject();
			if(subject.isAuthenticated()){
				String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
				if(SessionConfig.USER_SUPPLIER.equals(tag)){
					Supplier supplier = getCurrentUser(Supplier.class, request);
					User user = userService.selectUserBySpId(supplier.getId());
					password = user.getPassword();
				}else{
					return ResponseUtils.sendMsg(false, "请求有误！");
				}
			}
			if(!MD5.StringToMd5(usedPassword.trim()).equals(password.trim())){
				return ResponseUtils.sendMsg(false,"原密码输入有误" );
			}else{
				return ResponseUtils.sendMsg(true);
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "出错了！");
		}
		 
	}
	// 修改批发商登录密码
	@RequestMapping("/updateLoginPassword.do")
	@ResponseBody
	public Object updateLoginPassword(HttpServletRequest request, String usedPassword, String newPassword , String col2) throws Exception{
		Subject subject  = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			String tag =(String) subject.getSession().getAttribute(SessionConfig.USER_TYPE_KEY);
			int res = 0;
			newPassword = MD5.StringToMd5(newPassword.trim());
			if(SessionConfig.USER_SUPPLIER.equals(tag)){
				Supplier supplier = this.getCurrentUser(Supplier.class, request);
				User user = userService.selectUserBySpId(supplier.getId());
				if(!MD5.StringToMd5(usedPassword.trim()).equals(user.getPassword()))
					return ResponseUtils.sendMsg(false, "原密码输入有误！");
				user.setPassword(newPassword);
				user.setIntensity(Integer.valueOf(col2));
				res = userService.updateUser(user);
			}
			if(res == 0){
				return ResponseUtils.sendMsg(false, "修改失败");
			}
		}
		return ResponseUtils.sendMsg(true, "修改成功,请牢记密码,在下次登录时使用。");
	}
	// 修改经销商  批发商 仓库 等的密码统一接口   返回手机 校验码页面
	@RequestMapping("/login_passwd.do")
	public String login_passwd(HttpSession session,LoginRo ro,Model model){
		model.addAttribute("ro",ro);
		return "user/forget-password-check";
	}
	
	
	// 修改经销商  批发商 仓库 等的密码统一接口   手机 短信校验码 验证  验证手机号码  并发送验证码
		@RequestMapping("/login_passwd_checkphone.do") //checkCode  : 1批发商 2 经销商 3 仓库
		@ResponseBody
		public Object login_passwd_edit(LoginRo ro,Model model,HttpServletRequest request,HttpSession session){
			try {
				if(ro.getCheckCode()==null){
					return ResponseUtils.sendMsg(false, "非法操作!");
				}
			//用  手机号 查询 不同的 表 是否存在手机号  
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("type", ro.getCheckCode());
				map.put("mobile", ro.getPhoneNumber());
				int result=this.authorityService.getUserByTypeAndMobile(map);
				if(result<=0){
					return ResponseUtils.sendMsg(false, "手机号无效!");
				}
				//发送短信验证码;
				
					String msgCode1 = RandomUtils.getRandNum(6);
					if(session.getAttribute(ro.getPhoneNumber())==null){
						String msgCode="您正在重置登录密码，校验码"+msgCode1+"，请于30分钟内输入，工作人员不会向您索取，请勿泄露。";
						SendPhoneMsgUtil.sendPhoneMsg(ro.getPhoneNumber(), msgCode);
						session.setAttribute("type", ro.getCheckCode());
						session.setAttribute(ro.getPhoneNumber(), msgCode1);
						return ResponseUtils.sendMsg(true, "发送短信验证码成功!");
					}else{
						return ResponseUtils.sendMsg(true, "验证码发送成功，请注意查收");
					}
				
			} catch (Exception e) {
				logger.error(e.toString(), e);
				return ResponseUtils.sendMsg(false, "短信发送繁忙，请稍候再试!");
			}
			
		}
		
		
		// 修改经销商  批发商 仓库 等的密码统一接口  手机号 和验证码提交 进入到  修改密码页面
		@RequestMapping("/login_passwd_edit.do")
		@ResponseBody
		public Object login_passwd_edit(HttpServletRequest request,HttpSession session,LoginRo ro,Model model){
			if(ro.getCheckCode()==null){
				return ResponseUtils.sendMsg(false, "非法操作!");
			}
			if(ro.getPhoneNumber()==null){
				return ResponseUtils.sendMsg(false, "非法操作!");
			}
			if(ro.getCheck()==null){
				return ResponseUtils.sendMsg(false, "非法操作!");
			}
			//用  手机号 查询 不同的 表 是否存在手机号  
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("type", ro.getCheckCode());
			map.put("mobile", ro.getPhoneNumber());
			int result=this.authorityService.getUserByTypeAndMobile(map);
			if(result<=0){
				return ResponseUtils.sendMsg(false, "手机号无效!");
			}
			
			String msgCode=(String) session.getAttribute(ro.getPhoneNumber());
			if(msgCode==null){
				return ResponseUtils.sendMsg(false, "请确认该手机已发送验证码再执行此操作!");
			}
			if(ro.getCheck().equals(msgCode)){
				//匹配成功
				session.setAttribute("Phone_Number", ro.getPhoneNumber());
				session.removeAttribute(ro.getPhoneNumber());
				return ResponseUtils.sendMsg(true,"password/login_passwd_edit_success.do"); //修改密码页面
			}else{
				session.setAttribute("count", session.getAttribute("count")==null?1:(Integer)session.getAttribute("count")+1);
				if((Integer)session.getAttribute("count")>=5){
					return ResponseUtils.sendMsg(false, "你多次提交验证码错误，请稍候再试!");
				}
				return ResponseUtils.sendMsg(false, "验证码错误!");
			}
		}
		
		// 修改经销商  批发商 仓库 等的密码统一接口  手机号 和验证码提交 进入到  修改密码页面
		@RequestMapping("/login_passwd_edit_success.do")
		public String login_passwd_edit_success(LoginRo ro,HttpServletRequest request,HttpSession session,Model model){
			String phone=(String) session.getAttribute("Phone_Number");
			String type=(String) session.getAttribute("type");
			if(type==null){
				model.addAttribute("result", " 非法操作!");
				return "";
			}
			if(phone==null){
				model.addAttribute("result", " 非法操作!");
				return "";
			}
			model.addAttribute("type", type);
			return "user/forget-password-modify";
		}
		
		
		// 修改经销商  批发商 仓库 等的密码统一接口  手机号 和验证码提交 进入到  修改密码页面
		@RequestMapping("/login_passwd_update.do")
		public String login_passwd_update(LoginRo ro,HttpServletRequest request,HttpSession session){
			String phone=(String) session.getAttribute("Phone_Number");
			String type=(String) session.getAttribute("type");
			if(phone==null&&type==null){
				return "";
			}
			if(phone==null&&type!=null){
				return "user/forget-password-complete";
			}
			String phonenumber=(String) session.getAttribute("Phone_Number");
			String passwd=MD5.StringToMd5(ro.getPassword());
			int num = authorityService.updateLoginPasswordByDel(Integer.parseInt(type), phonenumber,passwd);
			if(num!=0){
				session.removeAttribute("Phone_Number");
				return "user/forget-password-complete";   //完成
			}else{
				return "user/forget-password-modify";   //失败
			}
		}
}
