/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.shop.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.common.web.BaseController;
import com.corner.salesman.commons.persistence.Json;
import com.corner.salesman.modules.shop.entity.LinePlans;
import com.corner.salesman.modules.shop.service.LinePlansService;

/**
 * 线路规划Controller
 * @author setsail
 * @version 2016-08-05
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/linePlans")
public class LinePlansController extends BaseController {

	@Autowired
	private LinePlansService lPlansService;
	@Autowired
	private com.corner.rpc.salesman.api.service.LinePlansService linePlansService;
	
	@ModelAttribute
	public LinePlans get(@RequestParam(required=false) String id) {
		LinePlans entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lPlansService.get(id);
		}
		if (entity == null){
			entity = new LinePlans();
		}
		return entity;
	}
	
	@RequiresPermissions("shop:linePlans:view")
	@RequestMapping(value = {"list", ""})
	public String list(LinePlans linePlans, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LinePlans> page = lPlansService.findPage(new Page<LinePlans>(request, response), linePlans); 
		model.addAttribute("page", page);
		return "modules/shop/linePlansList";
	}

	@RequiresPermissions("shop:linePlans:view")
	@RequestMapping(value = "form")
	public String form(LinePlans linePlans, Model model) {
		model.addAttribute("linePlans", linePlans);
		return "modules/shop/linePlansForm";
	}

	@RequiresPermissions("shop:linePlans:edit")
	@RequestMapping(value = "save")
	public String save(LinePlans linePlans, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, linePlans)){
			return form(linePlans, model);
		}
		lPlansService.save(linePlans);
		addMessage(redirectAttributes, "保存线路规划成功");
		return "redirect:"+Global.getAdminPath()+"/shop/linePlans/?repage";
	}
	
	@RequiresPermissions("shop:linePlans:edit")
	@RequestMapping(value = "delete")
	public String delete(LinePlans linePlans, RedirectAttributes redirectAttributes) {
		lPlansService.delete(linePlans);
		addMessage(redirectAttributes, "删除线路规划成功");
		return "redirect:"+Global.getAdminPath()+"/shop/linePlans/?repage";
	}
	
	@RequestMapping(value = "deleteShopLineByLineId")
	@ResponseBody
	public Object deleteShopLineByLineId(String lineId) {
		
		Json json = new Json();
		try{
			linePlansService.deleteShopLineByLineId(lineId);
			json.setSuccess(true);
			json.setMsg("删除路线信息成功！");
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("删除路线信息异常！");
			json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 保存路线规划方法
	 * @param linePlans
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveLinePlans")
	@ResponseBody
	public Object saveLinePlans(LinePlans linePlans, Model model) {
		Json json = new Json();
		try{
			com.corner.rpc.salesman.model.LinePlans plansVo = new com.corner.rpc.salesman.model.LinePlans();
			BeanUtils.copyProperties(plansVo,linePlans);
			linePlansService.saveLinePlans(plansVo);
			json.setSuccess(true);
			json.setMsg("保存路线规划成功！");
		}catch(Exception e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("保存路线规划异常！");
			json.setCode("500");
		}
		return json;
	}
	
	
	/**
	 * 获取用户路线规划方法
	 * @param linePlans
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "toLinePlans")
	public String toLinePlans(LinePlans linePlans, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		model.addAttribute("linePlans", linePlans);
		return "redirect:"+Global.getAdminPath()+"/shop/linePlans/queryLinePlans?repage";
	}
	
	/**
	 * 获取用户路线规划方法
	 * @param linePlans
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "queryLinePlans")
	public String queryLinePlans(LinePlans linePlans, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		com.corner.rpc.salesman.model.LinePlans plansVo = new com.corner.rpc.salesman.model.LinePlans();
		BeanUtils.copyProperties(plansVo,linePlans);
		
		List<com.corner.rpc.salesman.model.LinePlans> lineList = linePlansService.queryLinePlansList(plansVo);
		model.addAttribute("linePlans", linePlans);
		model.addAttribute("lineList", lineList);
		return "modules/shop/linePlansForm";
	}

}