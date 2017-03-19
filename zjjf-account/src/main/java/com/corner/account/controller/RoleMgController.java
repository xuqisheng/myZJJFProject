package com.corner.account.controller;

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

import com.corner.account.beans.ro.RoleCondition;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.service.RoleMgService;
import com.corner.core.beans.Role;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;


@Controller
@RequestMapping(value="/account/rolectl")
public class RoleMgController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(RoleMgController.class);
	
	@Autowired RoleMgService roleMgService;

	@RequestMapping(value = "/RoleManagerPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心角色管理界面");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "User/RoleManage";
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
			ModelMsg msg = roleMgService.deleteObjects("Role",array);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Update.do")
	@ResponseBody
	public Object Update(HttpServletRequest request, Role role) {
		if(role == null || role.getId() == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = roleMgService.updateByPrimaryKeySelective(role);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Add.do")
	@ResponseBody
	public Object add(HttpServletRequest request, Role role) {
		if(role == null || role.getId() == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = roleMgService.addObject(role);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, RoleCondition command) {
		Pager<Role> pager = roleMgService.getRolePageList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/getAuths.do")
	@ResponseBody
	public Object getAuths(HttpServletRequest request, String id) {
		return roleMgService.getAuthsByRoleId(id);
	}

	@RequestMapping(value = "/saveAuths.do")
	@ResponseBody
	public Object saveAuths(HttpServletRequest request, String roleId,String authIds) {
		if(StringUtils.isEmpty(roleId) || StringUtils.isEmpty(authIds)){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = roleMgService.saveUserAuths(roleId,authIds);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}
}
