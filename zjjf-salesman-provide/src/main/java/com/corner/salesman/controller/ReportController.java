package com.corner.salesman.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.salesman.common.persistence.AppPage;
import com.corner.salesman.common.utils.EmojiFilter;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.AppReport;
import com.corner.salesman.model.DailyReply;
import com.corner.salesman.model.Report;
import com.corner.salesman.service.ReportService;

/**
 * 报告控制管理类
 * @author 元宝
 * @version 2016-01-26
 */
@Controller
@RequestMapping("/mobile/report")
public class ReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Autowired
	private ReportService reportService;

	/**
	 * 根据ID查询报告明细信息
	 * @param report
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getReportById"})
	public Object getReportById(Report report, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String reportId = report.getReportId();
			 if(StringUtils.isBlank(reportId)){
				 json.setMsg("报告ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 String userId = report.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 AppReport appRpt = reportService.findReportDetailById(report);
			 json.setData(appRpt);
			 json.setMsg("获取报告明细信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.error("获取报告明细信息异常:{}",e.getMessage());
			 json.setMsg("获取报告明细信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 查询报告列表信息（首页报告接口）
	 * @param report
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getReportList"})
	public Object getReportList(Report report, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Page<Report> page = reportService.findReportList(new Page<Report>(request, response), report);
			 AppPage<Report> target = new AppPage<Report>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取报告列表信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("获取报告列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.error("获取报告列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 查询我的报告列表信息
	 * @param report
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyReportList"})
	public Object getMyReportList(Report report, HttpServletRequest request, HttpServletResponse response){
		Json json = new Json();
		try {
			 String userId = report.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 Page<Report> page = reportService.findMyReportList(new Page<Report>(request, response), report);
			 AppPage<Report> target = new AppPage<Report>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取报告列表信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("获取报告列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.error("获取报告列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 添加报告信息
	 * @param report
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addReport"})
	public Object addReport(Report report, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String userId = report.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setCode("500");
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 logger.info("发布日志时，用户ID不能为空！");
				 return json;
			 }
			 
			 String userName = report.getUserName();
			 if(StringUtils.isBlank(userName)){
				 json.setCode("500");
				 json.setMsg("用户名称不能为空！");
				 json.setSuccess(false);
				 logger.info("发布日志时，用户名称不能为空！");
				 return json;
			 }
			 
			 String deptId = report.getDeptId();
			 if(StringUtils.isBlank(deptId)){
				 json.setCode("500");
				 json.setMsg("部门ID不能为空！");
				 json.setSuccess(false);
				 logger.info("发布日志时，部门ID不能为空！");
				 return json;
			 }
			 
			 String tmplId = report.getTmplId();
			 if(StringUtils.isBlank(tmplId)){
				 json.setCode("500");
				 json.setMsg("模板编码不能为空！");
				 json.setSuccess(false);
				 logger.info("发布日志时，模板ID不能为空！");
				 return json;
			 }
			 
			 //对中文的几个字段进行转码处理
			 String content = report.getContent();
			 content = Encodes.urlDecode(content);
			 
			 if(StringUtils.isBlank(content)){
				 json.setCode("500");
				 json.setMsg("日志内容不能为空！");
				 json.setSuccess(false);
				 logger.info("发布日志内容不能为空！");
				 return json;
			 }
			 
			 if(EmojiFilter.containsEmoji(content)){
				json.setMsg("填报信息不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 report.setContent(content);
			 
			 
			 String remark = report.getRemark();
			 remark = Encodes.urlDecode(remark);
			 if(EmojiFilter.containsEmoji(remark)){
				json.setMsg("备注信息不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 report.setRemark(remark);
			 reportService.addReportInfo(report);
			 json.setMsg("添加报告信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.error("添加报告信息异常:{}",e.getMessage());
			 json.setMsg("添加报告信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	/**
	 * 删除日志评论
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"delDailyCommnent"})
	public Object delDailyCommnent(DailyReply record,HttpServletRequest request) throws Exception{
		Json json=new Json();
		try {
			 String commentId = record.getCommentId();
			 if(StringUtils.isBlank(commentId)){
				 json.setCode("500");
				 json.setMsg("评论ID不能为空");
				 json.setSuccess(false);
				 return json;
			 }
			 reportService.delDailyCommnent(record);
			 json.setMsg("删除评论成功！");
			 json.setSuccess(true);
			 json.setCode("200");
		} catch (Exception e) {
			 json.setMsg("删除评论失败！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		
		return json;
		
	}
}
