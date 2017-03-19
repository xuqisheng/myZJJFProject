package com.corner.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.corner.auth.beans.vo.ResponseVo;
import com.corner.auth.config.SessionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.auth.beans.AdmAuth;
import com.corner.auth.beans.Admin;
import com.corner.auth.beans.CustomerService;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.AuthRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.service.AdmAuthService;
import com.corner.auth.utils.ResponseUtils;
import com.corner.auth.utils.StringUtil;

@Controller
@RequestMapping(value="/auth/admAuth")
public class AdmAuthController extends AuthBaseWebController{

	@Autowired
	AdmAuthService admAuthService;
	
	@RequestMapping(value = "/getAuthListPage.do")
	public String getAuthListPage(HttpServletRequest request,  Model model, AuthRo command){
		Pager<AdmAuth> pager = admAuthService.getAuthListPage(command);
		// 共同分页封装
		model.addAttribute("authList", pager.getList());
		model.addAttribute("size", pager.getTotalSize());
		model.addAttribute("authMgRo", command);
		pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return "/admin/auth";
	}
	@RequestMapping(value = "/insertAuth.do")
	public String insertAuth(HttpServletRequest request,  Model model, AdmAuth command) throws Exception{
		if(StringUtil.stringIsNullOrEmpty(command.getAuthName()))
			return error("缺少必要元素", model, request);
		else if(command.getType()== null)
			return error("缺少必要元素", model, request);
		else if(command.getType()== 2 && StringUtil.stringIsNullOrEmpty(command.getAction()))
			return error("缺少必要元素", model, request);
		else if(command.getLevel() == null)
			return error("缺少必要元素", model, request);
		Admin admin = getCurrentUser(Admin.class, request);
		command.setCreateUser(admin.getId());
		command.setUpdateUser(admin.getId());
		command.setId(StringUtil.getUUID());
		ModelMsg modelMsg = admAuthService.insertAuth(command);
		if(!modelMsg.isSuccess())
			return error("新增权限失败", model, request);
		return "redirect:getAuthListPage.do";
	}
	@RequestMapping(value = "/updateAuth.do")
	public String updateAuth(HttpServletRequest request,  Model model, AdmAuth command) throws Exception{
		Admin admin = getCurrentUser(Admin.class, request);
		command.setUpdateUser(admin.getId());
		ModelMsg modelMsg = admAuthService.updateAuth(command);
		if(!modelMsg.isSuccess())
			return error("修改权限失败", model, request);
		return "redirect:getAuthListPage.do";
	}
	@RequestMapping(value = "/addAuth.do")
	public String addAuth(HttpServletRequest request,  Model model, String id) throws Exception{
		CustomerService customerService = getCurrentUser(CustomerService.class, request);
		AdmAuth auth = admAuthService.getAuthById(id);
		if(SessionConfig.SUPPER_KEFU_ADMIN.equals(customerService.getId()) || auth.getCreateUser().equals(customerService.getUserName())){
			ModelMsg modelMsg = admAuthService.deleteAuth(id);
			if(!modelMsg.isSuccess())
				return error("删除权限失败", model, request);
			return "redirect:getAuthListPage.do";
		}
		return error("只有ADMIN用户或者创建用户能删除权限", model, request);
	}
	@RequestMapping(value = "/goAuthEdit.do")
	public String goAuthEdit(Model model, String id) throws Exception{
		AuthRo command = new AuthRo();
		
		command.setPageSize(1000);
		command.setType(Byte.valueOf("1"));
		
		if(!StringUtil.stringIsNullOrEmpty(id)){
			AdmAuth auth = admAuthService.getAuthById(id);
			model.addAttribute("auth", auth);
		}
		Pager<AdmAuth> pager = admAuthService.getAuthListPage(command);
		model.addAttribute("authList", pager.getList());
		return "/admin/auth-edit";
	}

	@RequestMapping(value = "/getAuthById.do")
	public @ResponseBody ResponseVo getAuthById(String id) throws Exception{
		AdmAuth auth = admAuthService.getAuthById(id);
		if(auth == null){
			return ResponseUtils.sendMsg(false, "无对应数据");
		}
		return ResponseUtils.sendMsg(true, auth);
	}
	
	@RequestMapping(value = "/getAuthByAppId.do")
	public @ResponseBody Object getAuthByAppId(AuthRo command) throws Exception{
		command.setPageSize(1000);
		Pager<AdmAuth> pager = admAuthService.getAuthListPage(command);
		if(pager == null || pager.getList() == null || pager.getList().size() == 0){
			return ResponseUtils.sendMsg(false, "无对应数据");
		}
		return ResponseUtils.sendMsg(true, pager.getList());
	}
}
