package com.corner.salesman.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.model.Monitor;
import com.corner.salesman.model.News;
import com.corner.salesman.service.MonitorService;
import com.google.common.collect.Lists;

/**
 * 消息控制管理类
 * @author 元宝
 * @version 2016-01-26
 */
@Controller
@RequestMapping("/mobile/monitor")
public class MonitorController {
	private static final Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	@Autowired
	private MonitorService monitorService;
	
	/**
	 * 监控用户是否与新的消息（包括日报 和 公告）
	 * @param monitor
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getNewInfo"})
	public Object getNewInfo(Monitor monitor, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String userId = monitor.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 int msgTotal = 0;//该属性用于记录公告与日报信息的统计（供个人中心上方信息数量显示使用）
			 Monitor notice = new Monitor();
			 String noticeTotal = JedisUtils.get(Constants.NEW_NOTICE_TOTAL_KEY+userId);
			 if(StringUtils.isNotBlank(noticeTotal)){
				 int total = Integer.parseInt(noticeTotal);
				 if(total>0){
					 notice.setTotal(noticeTotal);
					 notice.setUpdate(true);
					 msgTotal +=total;
				 }
			 }
			 
			 Monitor daily = new Monitor();
			 String dailyTotal = JedisUtils.get(Constants.NEW_DAILY_TOTAL_KEY+userId);
			 if(StringUtils.isNotBlank(dailyTotal)){
				 int total = Integer.parseInt(dailyTotal);
				 if(total>0){
					 daily.setTotal(dailyTotal);
					 daily.setUpdate(true);
					 msgTotal +=total;
				 }
			 }
			 
			 Monitor comment = new Monitor();
			 String commentTotal = JedisUtils.get(Constants.DAILY_COMMENT_TOTAL_KEY+userId);
			 if(StringUtils.isNotBlank(commentTotal)){
				 int total = Integer.parseInt(commentTotal);
				 if(total>0){
					 comment.setTotal(commentTotal);
					 comment.setUpdate(true);
				 }
			 }
			 
			 Map<String,Object> map = new HashMap<String,Object>();
			 map.put("notice", notice);
			 map.put("daily", daily);
			 map.put("comment", comment);
			 map.put("msgTotal", msgTotal);
			 
			 boolean boolNotice = notice.isUpdate();
			 boolean boolDaily = daily.isUpdate();
			 boolean boolComment = comment.isUpdate();
			 if(boolNotice == true && boolDaily == false && boolComment == false){
				 json.setMsg("有与您相关的新公告信息，请及时查看！");
			 }else if(boolNotice == false && boolDaily == true && boolComment == false){
				 json.setMsg("有与您相关的日报信息，请及时查看！");
			 }else if(boolNotice == false && boolDaily == false && boolComment == true){
				 json.setMsg("有与您相关的评论信息，请及时查看！");
			 }else if(boolNotice == true && boolDaily == false && boolComment == true){
				 json.setMsg("有与您相关的公告、评论等信息，请及时查看！");
			 }else if(boolNotice == true && boolDaily == true && boolComment == false){
				 json.setMsg("有与您相关的公告、日报等信息，请及时查看！");
			 }else if(boolNotice == false && boolDaily == true && boolComment == true){
				 json.setMsg("有与您相关的日报、评论等信息，请及时查看！");
			 }else if(boolNotice == true && boolDaily == true && boolComment == true){
				 json.setMsg("有与您相关的公告、日报及评论等信息，请及时查看！");
			 }else if(boolNotice == false && boolDaily == false  && boolComment == false){
				 json.setMsg("您当前没有新消息！");
			 }
			 json.setData(map);
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.error("获取用户是否与新的消息异常:{}",e.getMessage());
			 json.setMsg("获取用户是否与新的消息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 获取个人最新的消息列表（包括日报 、 公告及评论）
	 * @param monitor
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getNewsList"})
	public Object getNewsList(Monitor monitor, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String userId = monitor.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 AppJson appjson = new AppJson();
			 
			 List<Object> newsList = JedisUtils.getObjectList(Constants.NEWS_LIST_PREFIX_KEY+userId);
			
			 String total = JedisUtils.get(Constants.NEW_NOTICE_TOTAL_KEY+userId);
			 //检查系统公告是否有新消息，如果有则构建公告对象返回给用户（避免在发布公告发布中添加的逻辑，在列表出现多条数据的问题）
			 if(!"0".equals(total)){
					//构建消息对象
					Date date = new Date();
					News news = new News();
					news.setType(1);//类型（1、公告；2、日报；3、评论）
					news.setTotal(total);
					news.setCreateTime(DateUtils.dateToString(date, DateUtils.MD_FORMAT));
					news.setTimePoint(DateUtils.dateToString(date, DateUtils.HOUR_TIME_FORMAT));
					
					if(null == newsList){
						newsList =  new ArrayList<Object>();
					}
					newsList.add(news);
			 }
			 

			
			 //清空个人日报及公告的redis记录数
			 JedisUtils.set(Constants.NEW_DAILY_TOTAL_KEY+userId, "0", 0);
			 JedisUtils.set(Constants.NEW_NOTICE_TOTAL_KEY+userId, "0", 0);
			 //删除reids中存储的最新消息列表
			 JedisUtils.delObject(Constants.NEWS_LIST_PREFIX_KEY+userId);
			
			 //如果消息列表为空则构建一个空的集合返回给app(目的方便app端判断处理)
			 if(null == newsList){
				newsList =  new ArrayList<Object>();
			 }else{
				 //反转排序
				 Collections.reverse(newsList);
			 }
			 
			 appjson.setList(newsList);
			 json.setData(appjson);
			 json.setSuccess(true);
			 json.setMsg("获取个人最新的消息列表成功！");
		} catch (Exception e) {
			 logger.error("获取个人最新的消息列表异常:{}",e);
			 json.setMsg("获取个人最新的消息列表异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 获取个人最新的消息列表（包括日报 、 公告及评论）
	 * @param monitor
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyNewsList"})
	public Object getMyNewsList(Monitor monitor, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String userId = monitor.getUserId();
			 String deptId = monitor.getDeptId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 if(StringUtils.isBlank(deptId)){
				 json.setMsg("部门ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 
			 List<HashMap<String, Object>> list = monitorService.getMyNewsList(monitor);
			 
			 Map<String, Object> dataMap = new HashMap<String, Object>();
			 dataMap.put("list", list);
			 
			 json.setData(dataMap);
			 json.setSuccess(true);
			 json.setMsg("获取个人最新的消息列表成功！");
		} catch (Exception e) {
			 logger.error("获取个人最新的消息列表异常:{}",e);
			 json.setMsg("获取个人最新的消息列表异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 获取个人提醒消息列表
	 * @param monitor
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getWarnMsgList"})
	public Object getWarnMsgList(Monitor monitor, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String userId = monitor.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 //为接收消息通知点击查看准备数据（添加消息列表中的数据信息）
			 List<Object> newsList = JedisUtils.getObjectList(Constants.AJ_WARN_LIST_KEY+userId);
			 if(null == newsList){
				newsList = Lists.newArrayList();
			 }else{
				 //如果消息列表有数据，则需要反转将最新的数据排在最前面
				 Collections.reverse(newsList);
			 }
			 
			 Map<String, Object> dataMap = new HashMap<String, Object>();
			 dataMap.put("list", newsList);
			 
			 json.setData(dataMap);
			 json.setSuccess(true);
			 json.setMsg("获取个人提醒消息列表成功！");
			 
			 //清空原来redis中记录未读取警告数量
			 JedisUtils.set(Constants.AJ_WARN_TOTAL_KEY+userId, "0", 0);
		} catch (Exception e) {
			 logger.error("获取个人提醒消息列表异常:{}",e.getMessage());
			 json.setMsg("获取个人提醒消息列表异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
}
