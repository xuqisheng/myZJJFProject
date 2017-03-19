/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.appsesalesman.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.corner.salesman.common.config.Global;
import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.web.BaseController;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.modules.appsesalesman.entity.Applistsalesman;
import com.corner.salesman.modules.appsesalesman.service.ApplistsalesmanService;

/**
 * 账户信息Controller
 * @author 小金刚
 * @version 2016-08-11
 */
@Controller
@RequestMapping(value = "${adminPath}/appsesalesman/applistsalesman")
public class ApplistsalesmanController extends BaseController {

	@Autowired
	private ApplistsalesmanService applistsalesmanService;
	
	@ModelAttribute
	public Applistsalesman get(@RequestParam(required=false) String id) {
		Applistsalesman entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = applistsalesmanService.get(id);
		}
		if (entity == null){
			entity = new Applistsalesman();
		}
		return entity;
	}
	
	@RequiresPermissions("appsesalesman:applistsalesman:view")
	@RequestMapping(value = {"list", ""})
	public String list(Applistsalesman applistsalesman, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Applistsalesman> page = applistsalesmanService.findPage(new Page<Applistsalesman>(request, response), applistsalesman); 
		model.addAttribute("page", page);
		return "modules/appsesalesman/applistsalesmanList";
	}

	@RequiresPermissions("appsesalesman:applistsalesman:view")
	@RequestMapping(value = "form")
	public String form(Applistsalesman applistsalesman, Model model) {
		List<Applistsalesman> list= applistsalesmanService.seldeptName(applistsalesman);
		List<Applistsalesman> listlab = applistsalesmanService.selpost(applistsalesman);
		model.addAttribute("listlab", listlab);
		model.addAttribute("list", list);
		model.addAttribute("applistsalesman", applistsalesman);	
		return "modules/appsesalesman/applistsalesmanForm";
	}

	@RequiresPermissions("appsesalesman:applistsalesman:edit")
	@RequestMapping(value = "save")
	public String save(Applistsalesman applistsalesman, Model model, RedirectAttributes redirectAttributes){
		if (!beanValidator(model, applistsalesman)){
			return form(applistsalesman, model);
		}
		applistsalesmanService.addSalesman(applistsalesman);
		addMessage(redirectAttributes, "保存账户信息成功");
		return "redirect:"+Global.getAdminPath()+"/appsesalesman/applistsalesman/?repage";
	}
	
	@RequiresPermissions("appsesalesman:applistsalesman:edit")
	@RequestMapping(value = "delete")
	public String delete(Applistsalesman applistsalesman, RedirectAttributes redirectAttributes){
		applistsalesmanService.delete(applistsalesman);
		addMessage(redirectAttributes, "删除账户信息成功");
		return "redirect:"+Global.getAdminPath()+"/appsesalesman/applistsalesman/?repage";
	}

	@RequiresPermissions("appsesalesman:applistsalesman:edit")
	@RequestMapping(value = "seldept")
	public String seldept(Applistsalesman seldeptName,Applistsalesman label, HttpServletRequest request, HttpServletResponse response, Model model){
		List<Applistsalesman> list= applistsalesmanService.seldeptName(seldeptName);
		List<Applistsalesman> listlab = applistsalesmanService.selpost(label);
		model.addAttribute("listlab", listlab);
		model.addAttribute("list", list);
		return "modules/appsesalesman/applistsalesmanForm";
	}
	
}