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

import com.corner.auth.beans.Admin;
import com.corner.auth.beans.CustomerService;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.UserRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.RoleVo;
import com.corner.auth.beans.vo.UserVo;
import com.corner.auth.service.CustomerServiceService;
import com.corner.auth.service.PublicService;
import com.corner.auth.service.RoleService;
import com.corner.auth.utils.JSONUtil;
import com.corner.auth.utils.ResponseUtils;
import com.corner.auth.utils.StringUtil;

@Controller
@RequestMapping(value="/auth/user")
public class UserController extends AuthBaseWebController{

	@Autowired
	CustomerServiceService customerServiceService;
	@Autowired
	RoleService roleService;
	@Autowired
	PublicService publicService;
	
	@RequestMapping(value = "/getUserListPage.do")
	public String getUserListPage(HttpServletRequest request,  Model model, UserRo command){
		command.setSortName("updateTime");
		command.setSortOrder("desc");
		Pager<UserVo> pager = customerServiceService.getUserListPage(command);
		// 共同分页封装
		model.addAttribute("userList", pager.getList());
		model.addAttribute("size", pager.getTotalSize());
		model.addAttribute("userMgRo", command);
		pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return "/system/user";
	}
	@RequestMapping(value = "/insertUser.do")
	public @ResponseBody ResponseVo insertUser(HttpServletRequest request,  Model model, UserRo command) throws Exception{
		if(StringUtil.stringIsNullOrEmpty(command.getUserName()))
			return ResponseUtils.sendMsg(false, "缺少参数");
		Admin admin = getCurrentUser(Admin.class, request);
		command.setCreaterId(admin.getId());
		command.setId(StringUtil.getUUID());
		command.setLastIP(request.getRemoteAddr());
		ModelMsg modelMsg = customerServiceService.insertUser(command);
		if(!modelMsg.isSuccess())
			return ResponseUtils.sendMsg(false, "新增角色失败");
		return ResponseUtils.sendMsg(true, "新增角色成功");
	}
	@RequestMapping(value = "/updateUser.do")
	public @ResponseBody ResponseVo updateUser(HttpServletRequest request,  Model model, UserRo command) throws Exception{
		ModelMsg modelMsg = customerServiceService.updateUser(command);
		command.setLastIP(request.getRemoteAddr());
		if(!modelMsg.isSuccess())
			return ResponseUtils.sendMsg(false, "修改角色失败");
		return ResponseUtils.sendMsg(true, "修改角色成功");
	}
	@RequestMapping(value = "/deleteUser.do")
	public String addUser(HttpServletRequest request,  Model model, String id) throws Exception{
		CustomerService customerService = getCurrentUser(CustomerService.class, request);
		CustomerService user = customerServiceService.getUserById(id);
		if(SessionConfig.SUPPER_KEFU_ADMIN.equals(customerService.getId()) || user.getUserName().equals(customerService.getUserName())){
			ModelMsg modelMsg = customerServiceService.deleteUser(id);
			if(!modelMsg.isSuccess())
				return error("删除权限失败", model, request);
			return "redirect:getUserListPage.do";
		}
		return error("只有ADMIN用户或者创建用户能删除权限", model, request);
	}
	@RequestMapping(value = "/goUserEdit.do")
	public String goUserEdit(HttpServletRequest request,  Model model, String id , Integer appId) throws Exception{
		String userId = "";
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
		model.addAttribute("roleList1", roleService.getRoleByAppIdOrUserId(appId, userId));
		model.addAttribute("departmentList", publicService.getAllDepartmentList());
		String str = "添加";
		if(!StringUtil.stringIsNullOrEmpty(id)){
			CustomerService user = customerServiceService.getUserById(id);
			model.addAttribute("user", user);
			List<RoleVo> roleVos = roleService.getRoleByAppIdOrUserId( appId , user.getId());
			model.addAttribute("roleList2", JSONUtil.objectToJSONString(roleVos));
			str = "编辑";
		}
		model.addAttribute("str", str);
		return "/system/user-edit";
	}
	@RequestMapping(value = "/getUserById.do")
	public @ResponseBody
	ResponseVo getUserById(HttpServletRequest request, Model model, String id) throws Exception{
		CustomerService user = customerServiceService.getUserById(id);
		if(user == null){
			return ResponseUtils.sendMsg(false, "无对应数据");
		}
		return ResponseUtils.sendMsg(true, user);
	}
//	@ResponseBody
//	@RequestMapping(value = "/checkUserNo.do")
//	public Object checkUserNo(HttpServletRequest request,  Model model, String userNo) throws Exception{
//		if(StringUtil.stringIsNullOrEmpty(userNo))
//			return error("缺少必要元素", model, request);
//		UserRo command = new UserRo();
//		command.setUserNo(userNo);
//		Pager<UserVo> pager = customerServiceService.getUserListPage(command);
//		if(pager.getTotalSize() == 0){
//			return ResponseUtils.sendMsg(true, "无对应数据");
//		}
//		return ResponseUtils.sendMsg(false, "已有该角色编号");
//	}
}
