/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.apprunlog.web;

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

import com.alibaba.fastjson.JSONObject;
import com.corner.salesman.common.config.Global;
import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.web.BaseController;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.commons.excel.ExcelUtils;
import com.corner.salesman.modules.applog.entity.Applog;
import com.corner.salesman.modules.apprunlog.entity.AppRunLog;
import com.corner.salesman.modules.apprunlog.service.AppRunLogService;

/**
 * App运行日志Controller
 * @author 小金刚
 * @version 2016-08-30
 */
@Controller
@RequestMapping(value = "${adminPath}/apprunlog/appRunLog")
public class AppRunLogController extends BaseController {

	@Autowired
	private AppRunLogService appRunLogService;
	
	@ModelAttribute
	public AppRunLog get(@RequestParam(required=false) String id) {
		AppRunLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = appRunLogService.get(id);
		}
		if (entity == null){
			entity = new AppRunLog();
		}
		return entity;
	}
	
	@RequiresPermissions("apprunlog:appRunLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(AppRunLog appRunLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AppRunLog> page = appRunLogService.findPage(new Page<AppRunLog>(request, response), appRunLog); 
		model.addAttribute("page", page);
		return "modules/apprunlog/appRunLogList";
	}

	@RequiresPermissions("apprunlog:appRunLog:view")
	@RequestMapping(value = "form")
	public String form(AppRunLog appRunLog, Model model) {
		model.addAttribute("appRunLog", appRunLog);
		return "modules/apprunlog/appRunLogForm";
	}

	@RequiresPermissions("apprunlog:appRunLog:edit")
	@RequestMapping(value = "save")
	public String save(AppRunLog appRunLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, appRunLog)){
			return form(appRunLog, model);
		}
		appRunLogService.save(appRunLog);
		addMessage(redirectAttributes, "保存App运行日志成功");
		return "redirect:"+Global.getAdminPath()+"/apprunlog/appRunLog/?repage";
	}
	
	@RequiresPermissions("apprunlog:appRunLog:edit")
	@RequestMapping(value = "delete")
	public String delete(AppRunLog appRunLog, RedirectAttributes redirectAttributes) {
		appRunLogService.delete(appRunLog);
		addMessage(redirectAttributes, "删除App运行日志成功");
		return "redirect:"+Global.getAdminPath()+"/apprunlog/appRunLog/?repage";
	}
	/**
	 * app运行日志导出方法
	 * @param applog
	 * @param response
	 * @throws Exception
	 */
	//@RequiresPermissions("apprunlog:appRunLog:edit")
	@RequestMapping(value = "findAppRunLogList")
	public void findAppRunLogList(AppRunLog apprunlog,HttpServletResponse response) throws Exception{
		String fileName = "APP运行日志.xls";
		HashMap<String, Object> titleMap=new HashMap<String, Object>(); 
		titleMap.put("用户","userName");
		titleMap.put("设备类型", "deviceType");
		titleMap.put("设备名称", "deviceName");
		titleMap.put("是否活着", "keepAlive");
		titleMap.put("上传状态", "uploadState");
		titleMap.put("网络", "network");
		titleMap.put("GPS", "gps");
		titleMap.put("App时间", "appTime");
		titleMap.put("服务器时间", "createTime");
		titleMap.put("数据信息", "dataInfo");
        List<AppRunLog> data=appRunLogService.findAppRunLogList(apprunlog);
        //System.out.println(JSONObject.toJSONString(data));
		ExcelUtils.exportExcel(response, fileName, data, titleMap);
	}
}