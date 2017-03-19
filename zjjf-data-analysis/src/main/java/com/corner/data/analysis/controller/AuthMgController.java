package com.corner.data.analysis.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Auth;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.auth.AuthCondition;
import com.corner.scms.config.AuthorityConfig;
import com.corner.scms.service.AuthMgService;


@Controller
@RequestMapping(value="/scms/authctl")
public class AuthMgController extends ScmsBaseWebController{
	
	private static Logger logger = LoggerFactory.getLogger(AuthMgController.class);
	
	@Autowired AuthMgService authMgService;

	@RequestMapping(value = "/AuthManagerPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "User/AuthManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/Delete.do")
	@ResponseBody
	public Object delete(@RequestParam(value = "ids", required = true) String ids) {
		if(StringUtils.isEmpty(ids)){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			String[] array = ids.split(",");
			ModelMsg msg = authMgService.deleteObjects("Auth",array);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Update.do")
	@ResponseBody
	public Object Update(HttpServletRequest request, Auth auth) {
		if(auth == null || auth.getId() == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = authMgService.updateByPrimaryKeySelective(auth);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Add.do")
	@ResponseBody
	public Object add(HttpServletRequest request, Auth auth) {
		if(auth == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			auth.setCreateTime(new Date());
			ModelMsg msg = authMgService.addObject(auth);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, AuthCondition command) {
		Pager<Auth> pager = authMgService.getAuthPageList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/getAllMenus.do")
	@ResponseBody
	public Object getAllMenus() {
		return authMgService.getAllMenus();
	}
}
