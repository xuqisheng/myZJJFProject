package com.corner.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.corner.auth.config.SessionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.auth.beans.Admin;
import com.corner.auth.beans.Auth;
import com.corner.auth.beans.CustomerService;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.AuthRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.service.AuthService;
import com.corner.auth.utils.ResponseUtils;
import com.corner.auth.utils.StringUtil;

@Controller
@RequestMapping(value="/auth/auth")
public class AuthController extends AuthBaseWebController{

	@Autowired
	AuthService authMgService;
	
	@RequestMapping(value = "/getAuthListPage.do")
	public String getAuthListPage(HttpServletRequest request,  Model model, AuthRo command){
		Pager<Auth> pager = authMgService.getAuthListPage(command);
		// 共同分页封装
		model.addAttribute("authList", pager.getList());
		model.addAttribute("size", pager.getTotalSize());
		model.addAttribute("authMgRo", command);
		pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return "/system/auth";
	}
	@RequestMapping(value = "/insertAuth.do")
	public String insertAuth(HttpServletRequest request,  Model model, Auth command) throws Exception{
		Admin admin = getCurrentUser(Admin.class, request);
		command.setCreateUser(admin.getUserName());
		command.setId(StringUtil.getUUID());
		ModelMsg modelMsg = authMgService.insertAuth(command);
		if(!modelMsg.isSuccess())
			return error("新增权限失败", model, request);
		return "redirect:getAuthListPage.do";
	}
	@RequestMapping(value = "/updateAuth.do")
	public String updateAuth(HttpServletRequest request,  Model model, Auth command) throws Exception{
		ModelMsg modelMsg = authMgService.updateAuth(command);
		if(!modelMsg.isSuccess())
			return error("修改权限失败", model, request);
		return "redirect:getAuthListPage.do";
	}
	@RequestMapping(value = "/addAuth.do")
	public String addAuth(HttpServletRequest request,  Model model, String id) throws Exception{
		CustomerService customerService = getCurrentUser(CustomerService.class, request);
		Auth auth = authMgService.getAuthById(id);
		if(SessionConfig.SUPPER_KEFU_ADMIN.equals(customerService.getId()) || auth.getCreateUser().equals(customerService.getUserName())){
			ModelMsg modelMsg = authMgService.deleteAuth(id);
			if(!modelMsg.isSuccess())
				return error("删除权限失败", model, request);
			return "redirect:getAuthListPage.do";
		}
		return error("只有ADMIN用户或者创建用户能删除权限", model, request);
	}
	@RequestMapping(value = "/goAuthEdit.do")
	public String goAuthEdit(HttpServletRequest request,  Model model, String id) throws Exception{
		AuthRo command = new AuthRo();
		
		command.setPageSize(1000);
		command.setType(Byte.valueOf("1"));
		
		if(!StringUtil.stringIsNullOrEmpty(id)){
			Auth auth = authMgService.getAuthById(id);
			model.addAttribute("auth", auth);
			
			command.setAppId(auth.getAppId());	//默认查询店包的
		}else{
			command.setAppId(Byte.valueOf("2"));	//默认查询店包的
		}
		Pager<Auth> pager = authMgService.getAuthListPage(command);
		model.addAttribute("authList", pager.getList());
		return "/system/auth-edit";
	}
	@ResponseBody
	@RequestMapping(value = "/getAuthById.do")
	public Object getAuthById(HttpServletRequest request,  Model model, String id) throws Exception{
		Auth auth = authMgService.getAuthById(id);
		if(auth == null){
			return ResponseUtils.sendMsg(false, "无对应数据");
		}
		return ResponseUtils.sendMsg(true, auth);
	}
	@ResponseBody
	@RequestMapping(value = "/getAuthByAppId.do")
	public Object getAuthByAppId(HttpServletRequest request,  Model model, AuthRo command) throws Exception{
		command.setPageSize(1000);
		Pager<Auth> pager = authMgService.getAuthListPage(command);
		if(pager == null || pager.getList() == null || pager.getList().size() == 0){
			return ResponseUtils.sendMsg(false, "无对应数据");
		}
		return ResponseUtils.sendMsg(true, pager.getList());
	}
}
