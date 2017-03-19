/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.applog.web;

import java.util.HashMap;
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
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.common.web.BaseController;
import com.corner.salesman.commons.excel.ExcelUtils;
import com.corner.salesman.modules.applog.entity.Applog;
import com.corner.salesman.modules.applog.service.ApplogService;

/**
 * App日志监控Controller
 * @author 小金刚
 * @version 2016-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/applog/applog")
public class ApplogController extends BaseController {

	@Autowired
	private ApplogService applogService;
	
	@ModelAttribute
	public Applog get(@RequestParam(required=false) String id) {
		Applog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = applogService.get(id);
		}
		if (entity == null){
			entity = new Applog();
		}
		return entity;
	}
	
	@RequiresPermissions("applog:applog:view")
	@RequestMapping(value = {"list", ""})
	public String list(Applog applog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Applog> page = applogService.findPage(new Page<Applog>(request, response), applog); 
		model.addAttribute("page", page);
		return "modules/applog/applogList";
	}

	@RequiresPermissions("applog:applog:view")
	@RequestMapping(value = "form")
	public String form(Applog applog, Model model) {
		model.addAttribute("applog", applog);
		return "modules/applog/applogForm";
	}

	@RequiresPermissions("applog:applog:edit")
	@RequestMapping(value = "save")
	public String save(Applog applog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, applog)){
			return form(applog, model);
		}
		applogService.save(applog);
		addMessage(redirectAttributes, "保存App日志监控成功");
		return "redirect:"+Global.getAdminPath()+"/applog/applog/?repage";
	}
	
	@RequiresPermissions("applog:applog:edit")
	@RequestMapping(value = "delete")
	public String delete(Applog applog, RedirectAttributes redirectAttributes) {
		applogService.delete(applog);
		addMessage(redirectAttributes, "删除App日志监控成功");
		return "redirect:"+Global.getAdminPath()+"/applog/applog/?repage";
	}
	
	/**
	 * app日志导出方法
	 * @param applog
	 * @param response
	 * @throws Exception
	 */
	//@RequiresPermissions("applog:applog:edit")
	@RequestMapping(value = "findErrorLog")
	public void findErrorLogList(Applog applog,HttpServletResponse response) throws Exception{
		String fileName = "APP异常监控日志.xls";
		HashMap<String, String> titleMap=new HashMap<String, String>(); 
		titleMap.put("账号","mobile");
		titleMap.put("姓名", "userName");
		titleMap.put("设备名称", "deviceUUID");
		titleMap.put("设备标示", "deviceName");
		titleMap.put("异常信息", "errorMsg");
		titleMap.put("异常时间", "errorTime");
		titleMap.put("结束时间", "createTime");
		titleMap.put("版本号", "version");
        List<Applog> appLogList=applogService.findErrorLogList(applog);
        //System.out.println(JSONObject.toJSONString(appLogList));
		ExcelUtils.exportExcel(response, fileName, appLogList, titleMap);
	}
}