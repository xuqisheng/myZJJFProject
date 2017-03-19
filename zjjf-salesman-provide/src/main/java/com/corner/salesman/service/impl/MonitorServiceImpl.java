package com.corner.salesman.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.commons.utils.DateUtils;
import com.corner.salesman.dao.DailyReplyMapper;
import com.corner.salesman.dao.NoticeMapper;
import com.corner.salesman.dao.ReportMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.model.Monitor;
import com.corner.salesman.service.MonitorService;
import com.google.common.collect.Lists;
/**
 * 公告信息Service
 * @author 元宝
 * @version 2016-01-26
 */
@Service("monitorService")
@Transactional(readOnly = true)
public class MonitorServiceImpl implements MonitorService {
	
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private DailyReplyMapper replyMapper;
	@Autowired
	private UserDeptMapper userDeptMapper;
	
	/**
	 * 获取用户首页消息列表数据
	 * @param monitor
	 * @return
	 */
	@Override
	public List<HashMap<String, Object>> getMyNewsList(Monitor monitor) throws Exception {
		 String userId = monitor.getUserId();
		 String deptId = monitor.getDeptId();
		 String userType = monitor.getUserType();
		 
		 //1、获取一条最新公告信息
		 Map<String,Object> paramMap = new HashMap<String,Object>();
		 paramMap.put("userId", userId);
		 paramMap.put("deptId", deptId);
		 paramMap.put("userType", userType);
		 
		 HashMap<String, Object> noticeMap = noticeMapper.getNewNotic2One(paramMap);

		 //3、获取一条最新的评论
		 HashMap<String, Object> commentMap = replyMapper.getNewComment2One(paramMap);
		 
		 //TODO 4、获取一条最新的待审核数据
		 
		 //2、获取一条最新的工作日志
		 //根据用户ID粗略校验，如果在管理映射表中有用户可以查询具体的数据信息，否则则能看到自己
		 int num = userDeptMapper.checkIsLeader(userId);
    	 if(num>0){
    		//1.如果检查管理者的映射有值，则查询部门所有数据，userId必须为空
    		 paramMap.put("userId", null);
    	 }
    	 
    	 paramMap.put("createBy", userId);
		 HashMap<String, Object> reportMap = reportMapper.getNewReport2One(paramMap);
		 
		 //5、获取一条当天日报为提交提醒数据
		 @SuppressWarnings("unchecked")
		 HashMap<String, Object> warnMap = (HashMap<String, Object>)JedisUtils.getObject(Constants.AJ_WARN_OBJ_KEY+userId);
		 
		 //6、获取拜访计划消息提醒信息
		 @SuppressWarnings("unchecked")
		 HashMap<String, Object> plansMap = (HashMap<String, Object>)JedisUtils.getObject(Constants.AJ_PLANS_CHANGE_KEY+userId);
		 
		//将公告、日报、评论等模块最新消息添加list返回
		 List<HashMap<String,Object>> list = Lists.newArrayList();
		 //获取redis消息队列中记录的各个消息的未读记录数，然后回填对应的map中带回首页显示
		 if(null != noticeMap){
			 String noticeTotal = JedisUtils.get(Constants.NEW_NOTICE_TOTAL_KEY+userId);
			 noticeMap.put("total",  StringUtils.isBlank(noticeTotal)?"0":noticeTotal);
			 list.add(noticeMap);
		 }else{
			 setDefaultValue("1", "公告", list);
		 }
		 
		 if(null != reportMap){
			 String dailyTotal = JedisUtils.get(Constants.NEW_DAILY_TOTAL_KEY+userId);
			 reportMap.put("total",  StringUtils.isBlank(dailyTotal)?"0":dailyTotal);
			 list.add(reportMap);
		 }else{
			 setDefaultValue("2", "工作日志", list);
		 }
		 
		 if(null != commentMap){
			 String commentTotal = JedisUtils.get(Constants.DAILY_COMMENT_TOTAL_KEY+userId);
			 commentMap.put("total", StringUtils.isBlank(commentTotal)?"0":commentTotal);
			 list.add(commentMap);
		 }else{
			 setDefaultValue("3", "日志点评/回复", list);
		 }
		 
		 if(null != warnMap){
			//记录个人未查阅的警告消息数
			 String warnTotal = JedisUtils.get(Constants.AJ_WARN_TOTAL_KEY+userId);
			 warnMap.put("total", StringUtils.isBlank(warnTotal)?"0":warnTotal);
			 list.add(warnMap);
		 }else{
			 setDefaultValue("5", "阿街提醒", list);
		 }
		 
		 if(null != plansMap){
			//记录个人未查阅的警告消息数
			 String warnTotal = JedisUtils.get(Constants.AJ_PLANS_CHANGE_TOTAL_KEY+userId);
			 plansMap.put("total", StringUtils.isBlank(warnTotal)?"0":warnTotal);
			 list.add(plansMap);
		 }else{
			 setDefaultValue("6", "拜访计划", list);
		 }
		 
		 //虚拟一个审核对象（仅提供管理者使用）
		 //if("1".equals(userType)){
			HashMap<String,Object> approveMap = new HashMap<String,Object>();
			approveMap.put("subject", "客户审核入口");
			approveMap.put("type", "4");
			approveMap.put("total", "0");
			approveMap.put("typeName", "客户审核");
			approveMap.put("createTime", DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT_7));
			list.add(approveMap);
		 //}

		return list;
	}
	
	public void setDefaultValue(String type,String typeName,List<HashMap<String,Object>> newsList){
		HashMap<String,Object> warnObj = new HashMap<String,Object>();
		warnObj.put("subject", "暂无消息");
		warnObj.put("type", type);
		warnObj.put("total", "0");
		warnObj.put("typeName", typeName);
		warnObj.put("createTime", DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT_7));
		newsList.add(warnObj);
	}

}
