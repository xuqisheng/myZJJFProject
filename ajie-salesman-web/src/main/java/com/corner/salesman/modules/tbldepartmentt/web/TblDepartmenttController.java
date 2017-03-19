/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.tbldepartmentt.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.corner.salesman.common.config.Global;
import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.web.BaseController;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.commons.persistence.Json;
import com.corner.salesman.modules.tbldepartmentt.entity.TblDepartmentt;
import com.corner.salesman.modules.tbldepartmentt.service.TblDepartmenttService;

/**
 * 部门信息Controller
 * 
 * @author 小金刚
 * @version 2016-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/tbldepartmentt/tblDepartmentt")
public class TblDepartmenttController extends BaseController {

	@Autowired
	private TblDepartmenttService tblDepartmenttService;

	@ModelAttribute
	public TblDepartmentt get(@RequestParam(required = false) String id) {
		TblDepartmentt entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tblDepartmenttService.get(id);
		}
		if (entity == null) {
			entity = new TblDepartmentt();
		}
		return entity;
	}
	
	@RequiresPermissions("tbldepartmentt:tblDepartmentt:view")
	@RequestMapping(value = { "list", "" })
	public String list(TblDepartmentt tblDepartmentt,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<TblDepartmentt> page = tblDepartmenttService.findPage(
				new Page<TblDepartmentt>(request, response), tblDepartmentt);
		model.addAttribute("page", page);
		return "modules/tbldepartmentt/tblDepartmenttList";
	}
	
	/**
	 * 查询部门领导方法
	 * @param tblDepartmentt
	 * @param model
	 * @return
	 */
	@RequiresPermissions("tbldepartmentt:tblDepartmentt:view")
	@RequestMapping(value = "form")
	public String form(TblDepartmentt tblDepartmentt, Model model) {
		List<TblDepartmentt> list = tblDepartmenttService
				.selpid(tblDepartmentt);
		model.addAttribute("list", list);
		model.addAttribute("tblDepartmentt", tblDepartmentt);
		return "modules/tbldepartmentt/tblDepartmenttForm";
	}
	
	@RequiresPermissions("tbldepartmentt:tblDepartmentt:view")
	@RequestMapping(value = "forms")
	public String forms(TblDepartmentt tblDepartmentt, Model model) {
		List<TblDepartmentt> list = tblDepartmenttService
				.selpid(tblDepartmentt);
		List<TblDepartmentt> userinfo = tblDepartmenttService
				.userinfo(tblDepartmentt);
		model.addAttribute("list", list);
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("tblDepartmentt", tblDepartmentt);
		return "modules/tbldepartmentt/tblDepartmentt-edit";
	}

	@RequiresPermissions("tbldepartmentt:tblDepartmentt:edit")
	@RequestMapping(value = "save")
	public String save(TblDepartmentt tblDepartmentt, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tblDepartmentt)) {
			return form(tblDepartmentt, model);
		}
		tblDepartmenttService.save(tblDepartmentt);
		addMessage(redirectAttributes, "保存部门信息成功");
		return "redirect:" + Global.getAdminPath()
				+ "/tbldepartmentt/tblDepartmentt/?repage";
	}

	@RequiresPermissions("tbldepartmentt:tblDepartmentt:edit")
	@RequestMapping(value = "delete")
	public String delete(TblDepartmentt tblDepartmentt,
			RedirectAttributes redirectAttributes) {
		tblDepartmenttService.delete(tblDepartmentt);
		addMessage(redirectAttributes, "删除部门信息成功");
		return "redirect:" + Global.getAdminPath()
				+ "/tbldepartmentt/tblDepartmentt/?repage";
	}
	/**
	 * 删除用户信息方法
	 */
	@RequestMapping(value = "deluser")
	@ResponseBody
	public Object deluser(TblDepartmentt deluser) {
		
		Json json = new Json();
		try{
			tblDepartmenttService.deluser(deluser);
			json.setSuccess(true);
			json.setMsg("删除用户信息成功！");
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("删除用户信息异常！");
			json.setCode("500");
		}
		return json;
	}

	@RequiresPermissions("tbldepartmentt:tblDepartmentt:view")
	@RequestMapping(value = { "userlist" })
	public String userlist(TblDepartmentt tblDepartmentt,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<TblDepartmentt> user = tblDepartmenttService
				.getUserPageList(tblDepartmentt);
		model.addAttribute("user", user);
		return "modules/tbldepartmentt/user-list";
	}
	
	
}