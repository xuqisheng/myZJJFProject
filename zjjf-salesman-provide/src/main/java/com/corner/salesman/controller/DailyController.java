package com.corner.salesman.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.corner.salesman.common.utils.AppJson;
import com.corner.salesman.common.utils.EmojiFilter;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.AppDaily;
import com.corner.salesman.model.Daily;
import com.corner.salesman.model.DailyReply;
import com.corner.salesman.service.DailyService;

/**
 * 日报控制管理类
 * @author 元宝
 * @version 2016-01-26
 */
@Controller
@RequestMapping("/mobile/daily")
public class DailyController {
	
	private static final Logger logger = LoggerFactory.getLogger(DailyController.class);
	@Autowired
	private DailyService dailyService;

	/**
	 * 根据ID查询日报明细信息
	 * @param dailyVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getDailyById"})
	public Object getDailyById(Daily dailyVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String reportId = dailyVO.getReportId();
			 if(StringUtils.isBlank(reportId)){
				 json.setMsg("日报ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 AppDaily daily = dailyService.findDailyDetailById(reportId);
			 json.setData(daily);
			 json.setMsg("获取日报明细信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.error("获取日报明细信息异常:{}",e.getMessage());
			 json.setMsg("获取日报明细信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 查询日报列表信息（首页日报接口）
	 * @param dailyVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getDailyList"})
	public Object getDailyList(Daily dailyVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Page<Daily> page = dailyService.findDailyList(new Page<Daily>(request, response), dailyVO);
			 AppPage<Daily> target = new AppPage<Daily>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取日报列表信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("获取日报列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.error("获取日报列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 查询我的日报列表信息
	 * @param dailyVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyDailyList"})
	public Object getMyDailyList(Daily dailyVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String userId = dailyVO.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 Page<Daily> page = dailyService.findMyDailyList(new Page<Daily>(request, response), dailyVO);
			 AppPage<Daily> target = new AppPage<Daily>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取日报列表信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("获取日报列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.error("获取日报列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 添加日报信息
	 * @param dailyVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addDaily"})
	public Object addDaily(Daily dailyVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String userId = dailyVO.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setCode("500");
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 logger.info("发布日报时，用户ID不能为空！");
				 return json;
			 }
			 
			 String deptId = dailyVO.getDeptId();
			 if(StringUtils.isBlank(deptId)){
				 json.setCode("500");
				 json.setMsg("部门ID不能为空！");
				 json.setSuccess(false);
				 logger.info("发布日报时，部门ID不能为空！");
				 return json;
			 }
			 //对中文的几个字段进行转码处理
			 String distribution = dailyVO.getDistributionCenter();
			 distribution = Encodes.urlDecode(distribution);
			 if(EmojiFilter.containsEmoji(distribution)){
				json.setMsg("配送商不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 dailyVO.setDistributionCenter(distribution);
			 
			 String feedback = dailyVO.getFeedback();
			 feedback = Encodes.urlDecode(feedback);
			 if(EmojiFilter.containsEmoji(feedback)){
				json.setMsg("市场信息反馈不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 dailyVO.setFeedback(feedback);
			 
			 String strategyPlan = dailyVO.getStrategyPlan();
			 strategyPlan = Encodes.urlDecode(strategyPlan);
			 if(EmojiFilter.containsEmoji(strategyPlan)){
				json.setMsg("策略调整计划不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 dailyVO.setStrategyPlan(strategyPlan);
			 
			 String support = dailyVO.getSupport();
			 support = Encodes.urlDecode(support);
			 if(EmojiFilter.containsEmoji(support)){
				json.setMsg("需要公司支持不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 dailyVO.setSupport(support);
			 //==================================
			 String todayVisitPartners = dailyVO.getTodayVisitPartners();
			 todayVisitPartners = Encodes.urlDecode(todayVisitPartners);
			 if(EmojiFilter.containsEmoji(todayVisitPartners)){
				json.setMsg("今日拜访合作商不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 dailyVO.setTodayVisitPartners(todayVisitPartners);
			 
			 String nextDayPlanVisitPartners = dailyVO.getNextDayPlanVisitPartners();
			 nextDayPlanVisitPartners = Encodes.urlDecode(nextDayPlanVisitPartners);
			 if(EmojiFilter.containsEmoji(nextDayPlanVisitPartners)){
				json.setMsg("明日计划拜访合作商不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 dailyVO.setNextDayPlanVisitPartners(nextDayPlanVisitPartners);
			 //==================================
			 
			 String remark = dailyVO.getRemark();
			 remark = Encodes.urlDecode(remark);
			 dailyVO.setRemark(remark);
			 dailyService.addDailyInfo(dailyVO);
			 json.setMsg("添加日报信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.error("添加日报信息异常:{}",e.getMessage());
			 json.setMsg("添加日报信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 添加日报评论信息
	 * @param dailyVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addComment"})
	public Object addDailyReply(DailyReply replyVo, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String userId = replyVo.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setCode("500");
				 json.setMsg("评论者ID不能为空！");
				 json.setSuccess(false);
				 return json;
			 }
			 String reportId = replyVo.getReportId();
			 if(StringUtils.isBlank(reportId)){
				 json.setCode("500");
				 json.setMsg("日报ID不能为空！");
				 json.setSuccess(false);
				 return json;
			 }
			 
			 //对中文的几个字段进行转码处理
			 String comment = replyVo.getComment();
			 comment = Encodes.urlDecode(comment);
			 if(StringUtils.isBlank(comment)){
				 json.setCode("500");
				 json.setMsg("评论内容不能为空！");
				 json.setSuccess(false);
				 return json;
			 }
			 
			 if(EmojiFilter.containsEmoji(comment)){
				json.setMsg("评论内容不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 
			 replyVo.setComment(comment);
			 replyVo = dailyService.addDailyReply(replyVo);
			 
			 //添加评论需要返回对应评论ID，供删除时候使用
			 String commentId =  replyVo.getCommentId();
			 Map<String,Object> commentMap = new HashMap<String,Object>();
			 commentMap.put("commentId",commentId);
			 json.setData(commentMap);
			 json.setMsg("添加日报评论信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.error("添加日报评论信息异常:{}",e.getMessage());
			 json.setMsg("添加日报评论信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 查询日报评论明细列表信息
	 * @param dailyVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getCommentList"})
	public Object getCommentList(DailyReply replyVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String reportId = replyVO.getReportId();
			 if(StringUtils.isBlank(reportId)){
				 json.setMsg("日报ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 List<DailyReply> list = dailyService.findCommentList(reportId);
			 AppJson appJs = new AppJson();
			 appJs.setList(list);
			 json.setData(appJs);
			 json.setMsg("获取日报评论列表信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("获取日报评论列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.error("获取日报评论列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 查询我的日报历史评论明细列表信息
	 * @param dailyVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getHisCommentList"})
	public Object getHisCommentList(DailyReply replyVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String token = replyVO.getToken();
			 if(StringUtils.isBlank(token)){
				 json.setMsg("Token不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 String userId = replyVO.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 Page<DailyReply> page = dailyService.getHisCommentList(new Page<DailyReply>(request, response), replyVO);
			 AppPage<DailyReply> target = new AppPage<DailyReply>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取我的日报历史评论列表信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("获取我的日报历史评论列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.error("获取我的日报历史评论列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	
	/**
	 * 获取个人未读评论列表信息
	 * @param dailyVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getUnreadCommentList"})
	public Object getUnreadCommentList(DailyReply replyVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String token = replyVO.getToken();
			 if(StringUtils.isBlank(token)){
				 json.setMsg("Token不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 String userId = replyVO.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 List<DailyReply> commentList = dailyService.getUnreadCommentList(replyVO);
			 Map<String, Object> dataMap = new HashMap<String, Object>();
			 dataMap.put("list", commentList);
			 json.setData(dataMap);
			 json.setMsg("获取个人未读评论列表信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("获取个人未读评论列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
			logger.error("获取个人未读评论列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
}
