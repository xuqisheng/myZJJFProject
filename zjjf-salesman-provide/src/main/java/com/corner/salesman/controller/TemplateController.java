package com.corner.salesman.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.salesman.common.utils.AppJson;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.model.ReportTemplate;
import com.corner.salesman.model.TmplDetailInfo;
import com.corner.salesman.model.TmplInfo;
import com.corner.salesman.service.ReportTmplService;
import com.corner.salesman.service.TmplInfoService;

/**
 * 模板控制管理类
 * @author 元宝
 * @version 2016-01-26
 */
@Controller
@RequestMapping("/mobile/tmpl")
public class TemplateController {
	
	private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);
	@Autowired
	private TmplInfoService tmplInfoService;
	@Autowired
	private ReportTmplService tmplService;

	/**
	 * 根据模板类型获取模板字段列表（v1.1版本）
	 * @param tmplVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@Deprecated
	@ResponseBody
	@RequestMapping(value = {"getTmplFieldList"})
	public Object getTmplFieldList(ReportTemplate tmplVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String type = tmplVO.getType();
			 if(StringUtils.isBlank(type)){
				 json.setMsg("模板类型不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 List<ReportTemplate> tmplList = tmplService.findReportTemplateByType(type);
			 //6、再将list数据放到AppJson中（目的满足app对该场景的数据格式要求）
			 AppJson appJs = new AppJson();
			 appJs.setList(tmplList);
			 json.setData(appJs);
			 json.setMsg("获取模板信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.info("获取模板信息异常:{}",e.getMessage());
			 json.setMsg("获取模板信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 获取模板列表接口
	 * @param tmplVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getTmplList"})
	public Object getTmplList(TmplInfo tmplInfo, HttpServletRequest request){
		Json json = new Json();
		try {
			 List<TmplInfo> tmplList = tmplInfoService.findTmplInfoList();
			 //6、再将list数据放到AppJson中（目的满足app对该场景的数据格式要求）
			 AppJson appJs = new AppJson();
			 appJs.setList(tmplList);
			 json.setData(appJs);
			 json.setMsg("获取模板列表信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.info("获取模板列表信息异常:{}",e.getMessage());
			 json.setMsg("获取模板列表信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 根据模板ID获取模板字段列表（v1.2版本）
	 * @param tmplVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getTmplAttrList"})
	public Object getTmplAttrList(TmplInfo tmplInfo, HttpServletRequest request){
		Json json = new Json();
		try {
			 String tmplId = tmplInfo.getTmplId();
			 if(StringUtils.isBlank(tmplId)){
				 json.setMsg("模板ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 List<TmplDetailInfo> tmplList = tmplInfoService.findTmplAttrList(tmplId);
			 //6、再将list数据放到AppJson中（目的满足app对该场景的数据格式要求）
			 AppJson appJs = new AppJson();
			 appJs.setList(tmplList);
			 json.setData(appJs);
			 json.setMsg("获取模板字段信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.info("获取模板字段信息异常:{}",e.getMessage());
			 json.setMsg("获取模板字段信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
}
