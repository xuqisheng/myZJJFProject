package com.corner.salesman.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.corner.rpc.salesman.api.service.UserService;
import com.corner.rpc.salesman.api.service.VisitHisRecordService;
import com.corner.rpc.salesman.model.VisitHisRecord;
import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.commons.push.UMengPushTools;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.commons.utils.DateUtils;
import com.google.common.collect.Lists;

/**
 *任务调度类
 *
 * @author longxian 2014/8/16 17:46
 */
@Component
@Lazy(false)
public class JobScheduled {
	
	private static Logger logger = LoggerFactory.getLogger(JobScheduled.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private VisitHisRecordService visitRecordService;
	
    public static SimpleDateFormat sdf_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
    
    //@Scheduled(cron="0 58 8 * * ? ")   //每天下班18：35执行
    //@Scheduled(cron="0 0/20 * * * ? ")   //每5秒执行一次 
    @Scheduled(cron = "0 58 8 ? * MON-SAT")//周一至周六的上午8:58触发 
    public void startAMSignJob() {
		Date currDate = new Date();
		String startTime = DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT);
    	logger.info("==============上班签到消息提醒任务调度开始，时间为：{}===========",startTime);

   		try {
   	    	Map<String,Object> map = new HashMap<String,Object>();
   	    	map.put("type", 1);//上班签到类型
			List<HashMap<String, Object>> userList = userService.getSignWarnUserList(map);
			
			String subject = "九点钟剑出鞘，英雄，记得签到哦！";
			StringBuffer alias = callPushAlertMethod(userList, "5", subject);
			
			//向所有未发送日报的人推送提醒消息
       		if(StringUtils.isNotBlank(alias.toString())){
       			UMengPushTools.getInstance().sendSignCustomizedcast(alias.toString(), "1");
       		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("======================上班签到消息提醒任务调度方法：startAMSignJob异常：{}",e);
		}
    }
    
    //@Scheduled(cron="0 35 18 * * ? ")   //每天下班18：35执行
    //@Scheduled(cron="0 0/30 * * * ? ")   //每30分钟执行一次 
    @Scheduled(cron = "0 35 18 ? * MON-SAT")//周一至周六的上午18:35触发 
    public void startPMSignJob() {
		Date currDate = new Date();
		String startTime = DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT);
    	logger.info("==============下班签到消息提醒任务调度开始，时间为：{}===========",startTime);

   		try {
   	    	Map<String,Object> map = new HashMap<String,Object>();
   	    	map.put("type", 2);//下班签到类型
			List<HashMap<String, Object>> userList = userService.getSignWarnUserList(map);
			
			String subject = "忙碌了一天您辛苦了，英雄，记得签退哦！";
			StringBuffer alias = callPushAlertMethod(userList, "5", subject);
			
			//向所有未发送日报的人推送提醒消息
       		if(StringUtils.isNotBlank(alias.toString())){
       			UMengPushTools.getInstance().sendSignCustomizedcast(alias.toString(), "2");
       		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("======================下班签到消息提醒任务调度方法：startPMSignJob异常：{}",e);
		}
    }
    
    /**
      1  秒（0~59）
      2  分钟（0~59）
      3 小时（0~23）
      4  天（0~31）
      5 月（0~11）
      6  星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
      7.年份（1970－2099）
      "0 15 10 * * ? *" 2016上午10:15触发
     */
    //@Scheduled(cron = "0 0 22 * * ?")
    //@Scheduled(cron="0 0/15 * * * ? ")   //每15分钟执行一次 
    //"0 0 22 ? * MON-SAT" 周一至周六的上午22:00触发 
    @Scheduled(cron = "0 0 22 ? * MON-SAT")
    public void callReportWarnJob() {
        try{
       		StringBuffer sbStr =  new StringBuffer();
       		boolean bool = true;
       		
			Date currDate = new Date();
    		String startTime = DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT);
    		
       		logger.info("==============日报消息提醒任务调度开始，时间为：{}===========",startTime);
       		List<HashMap<String, Object>> userList = userService.getReportWarnUserList(null);
       		logger.info("==============日报消息提醒===========");
       		
       		if(null != userList && !userList.isEmpty()){
       			for(HashMap<String, Object> userMap : userList){
       				String userId = (String)userMap.get("userId");
       				if(bool){
       					bool = false;
       					sbStr.append(userId);
       				}else{
       					sbStr.append(",").append(userId);
       				}
       				//记录个人未查阅的警告消息数
       				long warnTotal = JedisUtils.incr(Constants.AJ_WARN_TOTAL_KEY+userId);
       				
       				//为接收消息通知点击查看准备数据（添加消息列表中的数据信息）
					List<Object> newsList = JedisUtils.getObjectList(Constants.AJ_WARN_LIST_KEY+userId);
					if(null == newsList){
						newsList = Lists.newArrayList();
					}
					
					//计算过期时间
		    		String endTime = DateUtils.dateToString(DateUtils.addDays(currDate, 1), "yyyy-MM-dd")+" 00:00:00";
		    		int cacheSeconds =new Long( DateUtils.dateDiff(startTime, endTime, DateUtils.DATETIME_FORMAT)).intValue();
					
					Map<String,Object> warnObj = new HashMap<String,Object>();
					warnObj.put("subject", "您今天的日志尚未提交");
					warnObj.put("type", "5");
					warnObj.put("total", warnTotal+"");
					warnObj.put("typeName", "日志提醒");
					warnObj.put("createTime", DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT_7));
					newsList.add(warnObj);
					//将消息存入消息列表中
					JedisUtils.setObjectList(Constants.AJ_WARN_LIST_KEY+userId, newsList, cacheSeconds);
					
       				//保存个人最新消息提醒信息(覆盖原来的消息)
					warnObj.put("typeName", "阿街提醒");
       				JedisUtils.setObject(Constants.AJ_WARN_OBJ_KEY+userId, warnObj, 0);
       			}
       		}
       		
       		//向所有未发送日报的人推送提醒消息
       		if(StringUtils.isNotBlank(sbStr.toString())){
       			UMengPushTools.getInstance().sendAlertCustomizedcast(sbStr.toString());
       		}
  		  	logger.info("==============日报消息提醒任务调度结束===========");
  	   }catch (Exception e) {
  		  e.printStackTrace();
  		logger.error("======================日报消息提醒任务调度方法：initAjieWarnScheduled异常：{}",e);
  	   }
    }
    
    
    public StringBuffer callPushAlertMethod(List<HashMap<String, Object>> userList,String type,String subject){
    	
    	StringBuffer sbStr =  new StringBuffer();
        try{
       		boolean bool = true;
       		
			Date currDate = new Date();
    		String startTime = DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT);
    		
       		logger.info("==============打卡消息提醒任务调度开始，时间为：{}===========",startTime);
       		
       		if(null != userList && !userList.isEmpty()){
       			for(HashMap<String, Object> userMap : userList){
       				String userId = (String)userMap.get("userId");
       				if(bool){
       					bool = false;
       					sbStr.append(userId);
       				}else{
       					sbStr.append(",").append(userId);
       				}
       				//记录个人未查阅的警告消息数
       				long warnTotal = JedisUtils.incr(Constants.AJ_WARN_TOTAL_KEY+userId);
       				
       				//为接收消息通知点击查看准备数据（添加消息列表中的数据信息）
					List<Object> newsList = JedisUtils.getObjectList(Constants.AJ_WARN_LIST_KEY+userId);
					if(null == newsList){
						newsList = Lists.newArrayList();
					}
					
					//计算过期时间
		    		String endTime = DateUtils.dateToString(DateUtils.addDays(currDate, 1), "yyyy-MM-dd")+" 00:00:00";
		    		int cacheSeconds =new Long( DateUtils.dateDiff(startTime, endTime, DateUtils.DATETIME_FORMAT)).intValue();
					
					Map<String,Object> warnObj = new HashMap<String,Object>();
					warnObj.put("subject", subject);
					warnObj.put("type", type);
					warnObj.put("total", warnTotal+"");
					warnObj.put("typeName", "打卡提醒");
					warnObj.put("createTime", DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT_7));
					newsList.add(warnObj);
					//将消息存入消息列表中
					JedisUtils.setObjectList(Constants.AJ_WARN_LIST_KEY+userId, newsList, cacheSeconds);
					
       				//保存个人最新消息提醒信息(覆盖原来的消息)
					warnObj.put("typeName", "阿街提醒");
       				JedisUtils.setObject(Constants.AJ_WARN_OBJ_KEY+userId, warnObj, 0);
       			}
       		}
       		logger.info("==============打卡消息提醒任务调度结束===========");
  		  	
  	   }catch (Exception e) {
  		  e.printStackTrace();
  		logger.error("======================打卡消息提醒任务调度方法：callPushAlertMethod异常：{}",e);
  	   }
       
      return sbStr;
    }
    
    /**
     * 检查工作任务完成情况，如果任务完成小于计划的50%，则提醒业务员
     * 
     */
    @Scheduled(cron="0 0 16 * * ? ")   //每5秒执行一次 
    public void callCheckJob() {
    	
    	int ii =0;
		Date currDate = new Date();
		String startTime = DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT);
    	logger.info("==============拜访任务完成情况消息提醒开始，时间为：{}===========",startTime);

   		try {
   			VisitHisRecord record = new VisitHisRecord();
   			record.setPostType("1");//查询岗位为业务员类型的用户
			List<HashMap<String, Object>> userList = visitRecordService.getTodayVisitPlansList(record);
			
			if(null != userList && !userList.isEmpty()){
				
				for(HashMap<String,Object> map :userList){
					
					Object shopTotal = map.get("shopTotal");
					Object visitTotal = map.get("visitTotal");
					String salesmanId = (String)map.get("salesmanId");
					String salesmanName = (String)map.get("salesmanName");
					String deptId = (String)map.get("deptId");
					
					int allNum = shopTotal==null?0:Integer.parseInt(shopTotal.toString()); 
					int visitNum = visitTotal==null?0:Integer.parseInt(visitTotal.toString()); 
					//如果用户为分配客户，则忽略该用户的提醒
					if(allNum == 0){
						continue;
					}else if(allNum>0 && allNum/2>visitNum){
						ii+=1;
						//将提醒信息set到redis队列中
						//为接收消息通知点击查看准备数据（添加消息列表中的数据信息）
						List<Object> newsList = JedisUtils.getObjectList(Constants.AJ_WARN_LIST_KEY+salesmanId);
						if(null == newsList){
							newsList = Lists.newArrayList();
						}
						
						//计算过期时间
			    		String endTime = DateUtils.dateToString(DateUtils.addDays(currDate, 1), "yyyy-MM-dd")+" 00:00:00";
			    		int cacheSeconds =new Long( DateUtils.dateDiff(startTime, endTime, DateUtils.DATETIME_FORMAT)).intValue();
						
			    		int unVistNun = allNum-visitNum;
			    		String subject = "英雄，前方还有"+unVistNun+"家客户，加油哦！";
			    		String leaderTile = salesmanName+"今日还有"+unVistNun+"家客户未拜访，要及时督促哦！";
						Map<String,Object> warnObj = new HashMap<String,Object>();
						warnObj.put("subject", subject);
						warnObj.put("type", "5");
						warnObj.put("total", "0");
						warnObj.put("typeName", "拜访提醒");
						warnObj.put("createTime", DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT_7));
						newsList.add(warnObj);
						//将消息存入消息列表中
						JedisUtils.setObjectList(Constants.AJ_WARN_LIST_KEY+salesmanId, newsList, cacheSeconds);
						
	       				//保存个人最新消息提醒信息(覆盖原来的消息)
						warnObj.put("typeName", "阿街提醒");
	       				JedisUtils.setObject(Constants.AJ_WARN_OBJ_KEY+salesmanId, warnObj, 0);
	       				//记录个人未查阅的警告消息数
	       				JedisUtils.incr(Constants.AJ_WARN_TOTAL_KEY+salesmanId);
	       				
	       				//向当天拜访完成小于50%的业务员推送提醒消息
	       	       		if(StringUtils.isNotBlank(salesmanId)){
	       	       			UMengPushTools.getInstance().sendVisitWarnCustomizedcast(salesmanId, subject);
	       	       		}
	       				
	       				//====================通知领导跟进 start===========================
	       				StringBuffer leaderStr =  new StringBuffer();
	       				List<String> leaderList = userService.getDeptLeaderIdList(deptId);
	       				if(null != leaderList && !leaderList.isEmpty()){
	       					for(String leaderId : leaderList){
	       						leaderStr.append(leaderId).append(",");
	       						//获取领导的消息队列
	       						List<Object> newsLeader = JedisUtils.getObjectList(Constants.AJ_WARN_LIST_KEY+leaderId);
								if(null == newsLeader){
									newsLeader = Lists.newArrayList();
								}
								
								warnObj.put("subject", leaderTile);//领导的提醒内容
								warnObj.put("typeName", "拜访提醒");
								newsLeader.add(warnObj);
								//将消息存入消息列表中
								JedisUtils.setObjectList(Constants.AJ_WARN_LIST_KEY+leaderId, newsLeader, cacheSeconds);
								
			       				//保存个人最新消息提醒信息(覆盖原来的消息)
								warnObj.put("typeName", "阿街提醒");
			       				JedisUtils.setObject(Constants.AJ_WARN_OBJ_KEY+leaderId, warnObj, 0);
			       				//记录个人未查阅的警告消息数
			       				JedisUtils.incr(Constants.AJ_WARN_TOTAL_KEY+leaderId);
	       					}
	       					
	       					//调用消息推送提醒领导
	       		       		if(StringUtils.isNotBlank(leaderStr.toString())){
	       		       			UMengPushTools.getInstance().sendVisitWarnCustomizedcast(leaderStr.toString(),leaderTile);
	       		       		}
	       				}
	       			//====================通知领导跟进 end===========================
					}
				}
				
			}
			
       		logger.info("====================拜访提醒：业务员总数量：{},实际提醒拜访人数为：{}",userList.size(),ii);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("======================拜访任务完成情况调度方法：callCheckJob异常：{}",e);
		}
    }
}