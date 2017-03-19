package com.corner.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.corner.auth.beans.vo.ResponseVo;
import com.corner.auth.config.SessionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.auth.beans.AdmRole;
import com.corner.auth.beans.Admin;
import com.corner.auth.beans.CustomerService;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.RoleRo;
import com.corner.auth.beans.vo.AuthVo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.RoleVo;
import com.corner.auth.service.AdmAuthService;
import com.corner.auth.service.AdmRoleService;
import com.corner.auth.utils.JSONUtil;
import com.corner.auth.utils.ResponseUtils;
import com.corner.auth.utils.StringUtil;

@Controller
@RequestMapping(value="/auth/admRole")
public class AdmRoleController extends AuthBaseWebController{

	@Autowired
	AdmRoleService roleService;
	@Autowired
	AdmAuthService authService;
	
	@RequestMapping(value = "/getRoleListPage.do")
	public String getRoleListPage(HttpServletRequest request,  Model model, RoleRo command){
		command.setSortName("updateTime");
		command.setSortOrder("desc");
		Pager<RoleVo> pager = roleService.getRoleListPage(command);
		// 共同分页封装
		model.addAttribute("roleList", pager.getList());
		model.addAttribute("size", pager.getTotalSize());
		model.addAttribute("roleMgRo", command);
		pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return "/admin/role";
	}

	@RequestMapping(value = "/insertRole.do")
	public @ResponseBody ResponseVo insertRole(HttpServletRequest request, RoleRo command) throws Exception{
		if(StringUtil.stringIsNullOrEmpty(command.getRoleName() , command.getRoleNo()))
			return ResponseUtils.sendMsg(false, "缺少参数");
		Admin admin = getCurrentUser(Admin.class, request);
		command.setCreateUser(admin.getId());
		command.setUpdateUser(admin.getId());
		command.setId(StringUtil.getUUID());
		ModelMsg modelMsg = roleService.insertRole(command);
		if(!modelMsg.isSuccess())
			return ResponseUtils.sendMsg(false, "新增角色失败");
		return ResponseUtils.sendMsg(true, "新增角色成功");
	}

	@RequestMapping(value = "/updateRole.do")
	public @ResponseBody ResponseVo updateRole(HttpServletRequest request, RoleRo command) throws Exception{
		Admin admin = getCurrentUser(Admin.class, request);
		command.setUpdateUser(admin.getId());
		ModelMsg modelMsg = roleService.updateRole(command);
		if(!modelMsg.isSuccess())
			return ResponseUtils.sendMsg(false, "修改角色失败");
		return ResponseUtils.sendMsg(true, "修改角色成功");
	}
	@RequestMapping(value = "/deleteRole.do")
	public String addRole(HttpServletRequest request,  Model model, String id) throws Exception{
		CustomerService customerService = getCurrentUser(CustomerService.class, request);
		AdmRole role = roleService.getRoleById(id);
		if(SessionConfig.SUPPER_KEFU_ADMIN.equals(customerService.getId()) || role.getCreateUser().equals(customerService.getUserName())){
			ModelMsg modelMsg = roleService.deleteRole(id);
			if(!modelMsg.isSuccess())
				return error("删除权限失败", model, request);
			return "redirect:getRoleListPage.do";
		}
		return error("只有ADMIN用户或者创建用户能删除权限", model, request);
	}
	@RequestMapping(value = "/goRoleEdit.do")
	public String goRoleEdit(HttpServletRequest request,  Model model, String id , Integer appId) throws Exception{
//		CustomerService customerService = getCurrentUser(CustomerService.class, request);
//		if(customerService != null){
//			userType = 1;
//			userId = customerService.getId();
//		}
//		Admin admin = getCurrentUser(Admin.class, request);
//		if(admin != null){
//			userType = 2;
//			userId = admin.getId();
//		}
		model.addAttribute("authList1", authService.getAuthByAppIdOrRoleId(appId , null));
		String str = "添加";
		if(!StringUtil.stringIsNullOrEmpty(id)){
			AdmRole role = roleService.getRoleById(id);
			model.addAttribute("role", role);
			String[] ids = new String[]{id};
			List<AuthVo> authVos = authService.getAuthByAppIdOrRoleId( appId , ids);
			model.addAttribute("authList2", JSONUtil.objectToJSONString(authVos));
			str = "编辑";
		}
		model.addAttribute("str", str);
		return "/admin/role-edit";
	}

	@RequestMapping(value = "/getRoleById.do")
	public @ResponseBody ResponseVo getRoleById(String id) throws Exception{
		AdmRole role = roleService.getRoleById(id);
		if(role == null){
			return ResponseUtils.sendMsg(false, "无对应数据");
		}
		return ResponseUtils.sendMsg(true, role);
	}

	@RequestMapping(value = "/checkRoleNo.do")
	public @ResponseBody Object checkRoleNo(HttpServletRequest request,  Model model, String roleNo) throws Exception{
		if(StringUtil.stringIsNullOrEmpty(roleNo))
			return error("缺少必要元素", model, request);
		RoleRo command = new RoleRo();
		command.setRoleNo(roleNo);
		Pager<RoleVo> pager = roleService.getRoleListPage(command);
		if(pager.getTotalSize() == 0){
			return ResponseUtils.sendMsg(true, "无对应数据");
		}
		return ResponseUtils.sendMsg(false, "已有该角色编号");
	}
}
