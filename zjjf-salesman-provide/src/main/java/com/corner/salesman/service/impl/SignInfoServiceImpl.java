package com.corner.salesman.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.rpc.salesman.api.service.DictService;
import com.corner.rpc.salesman.api.service.LinePlansService;
import com.corner.rpc.salesman.api.service.ShopService;
import com.corner.rpc.salesman.api.service.UserService;
import com.corner.rpc.salesman.api.service.VisitHisRecordService;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.IdGen;
import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.commons.push.UMengPushTools;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.commons.utils.DateUtils;
import com.corner.salesman.controller.ShopInfoController;
import com.corner.salesman.dao.SignInfoMapper;
import com.corner.salesman.dao.SignTimeRecordMapper;
import com.corner.salesman.dao.TrackRecordMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.model.SignInfo;
import com.corner.salesman.model.SignTimeRecord;
import com.corner.salesman.model.TrackRecord;
import com.corner.salesman.model.User;
import com.corner.salesman.service.SignInfoService;
import com.google.common.collect.Lists;
import com.zjjf.analysis.producer.ajie.IStoreTurnoverService;

/**
 * 创建时间：2015-1-27 下午5:22:59
 * 
 * @author 元宝
 * @version v0.1
 */
@Service("signInfoService")
@Transactional(readOnly = true)
public class SignInfoServiceImpl implements SignInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopInfoController.class);
	
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private SignInfoMapper signInfoMapper;
	@Autowired
	private SignTimeRecordMapper signTimeRecordMapper;
	@Autowired
	private TrackRecordMapper trackRecordMapper;
	@SuppressWarnings("rawtypes")
	@Autowired
	private IStoreTurnoverService storeTurnoverService;
	@Autowired
	private VisitHisRecordService visitHisRecordService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private LinePlansService linePlansService;
	@Autowired
	private UserService userService;
	@Autowired
	private DictService dictService;
	
	/**
	 * 我的签到列表(分页方法)
	 * @param signInfo
	 * @return
	 */
    public Page<SignInfo> findMySigntList(Page<SignInfo> page, SignInfo signInfo) throws Exception{
    	// 设置分页参数
		signInfo.setPage(page);
		// 执行分页查询
		List<SignInfo> list = signInfoMapper.findMySigntList(signInfo);
		
		page.setList(list);
		return page;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<SignInfo> findMySigntDetailList(Page<SignInfo> page, SignInfo signInfo) throws Exception {
		// 设置分页参数
		signInfo.setPage(page);
		// 执行分页查询
		List<SignInfo> list = signInfoMapper.findMySigntDetailList(signInfo);
		
		if(null != list && list.size()>0){
			
			for(SignInfo picVo : list){
				List picList = null;
				String picUrl = picVo.getPicUrl();
				//将图片切割放于list中
				if(StringUtils.isNotBlank(picUrl)){
					picList = new ArrayList();
					String[] picItem = picUrl.split(",");
					for (int i = 0; i < picItem.length; i++) {
						//如果图片url不为空则拼接完整的url地址
						picList.add(picItem[i]);
					}
					//将图片ID置空
					picVo.setPicUrl(null);
				}
				picVo.setPicList(picList);
			}
		}
		
		page.setList(list);
		return page;
	}
	
    /**
     * 根据用户组ID查询该组签到的信息列表
     * @param signInfo
     * @return
     */
	@Override
    public List<SignInfo> findSigntListByGroupId(SignInfo signInfo) throws Exception {
		
		/*//查询签到库中最新的时间
		String signMaxDate = signInfoMapper.getSigntMaxDate();
		String createTime = signInfo.getCreateTime();
		Date currentDate = DateUtils.stringToDate(createTime, DateUtils.NORMAL_DATE_FORMAT);
		Date dbMaxDate = DateUtils.stringToDate(signMaxDate, DateUtils.NORMAL_DATE_FORMAT);
		//取数据库表中的最大时间和当前的查询时间比较，如果当前时间大于数据库时间，则查询数据库中最大时间的数据（规避周末没签到，进入页面没有数据的场景）
		int i = currentDate.compareTo(dbMaxDate);
		if(i>0){
			signInfo.setCreateTime(signMaxDate);
		}*/
		
		//如果查询时间为空则查询当前数据库最新时间
		String createTime = signInfo.getCreateTime();
		if(StringUtils.isBlank(createTime)){
			String signMaxDate = signInfoMapper.getSigntMaxDate();
			signInfo.setCreateTime(signMaxDate);
		}
		
		return signInfoMapper.findSigntListByGroupId(signInfo);
	}
	
	/**
	 * 根据时间查询当前所有外勤签到列表
	 * @param signInfo
	 * @return
	 */
    public List<SignInfo> findOutWorkSignList(SignInfo signInfo) throws Exception{
    	String createTime = signInfo.getCreateTime();
		if(StringUtils.isBlank(createTime)){
			String signMaxDate = signInfoMapper.getSigntMaxDate();
			signInfo.setCreateTime(signMaxDate);
		}
		
		return signInfoMapper.findOutWorkSignList(signInfo);
    }
    
    /**
     * 添加签到信息
     * @param signInfo
     * @return
     */
	@Transactional(readOnly = false)
	@Override
    public int addSignInfo(SignInfo signInfo) throws Exception{
		
		String userId = signInfo.getUserId();
    	String deptId = userDeptMapper.findDeptIdByUserId(userId);
    	if(StringUtils.isNotBlank(deptId)){
    		signInfo.setGroupId(deptId);
    	}

    	//对app端转码的中文进行解码处理
    	String remarks = signInfo.getRemarks();
    	String address = signInfo.getAddress();
    	address = Encodes.urlDecode(address);
    	remarks = Encodes.urlDecode(remarks);
    	signInfo.setAddress(address);
    	signInfo.setRemarks(remarks);
		signInfo.setId(IdGen.uuid());
		signInfo.setCreateBy(userId);
		signInfo.setWeek(DateUtils.getChineseWeekday());
		signInfo.setCreateTime(DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT));
		return signInfoMapper.insertSignInfo(signInfo);
	}
    
    
    /**
     * 添加签到信息
     * @param signInfo
     * @return
     */
	@Transactional(readOnly = false)
	@Override
    public Json addSignData(SignInfo signInfo) throws Exception{
		
		Json json = new Json();
		
		Date currDate = new Date();
		String userId = signInfo.getUserId();
    	String deptId = signInfo.getDeptId();
    	Integer signType = signInfo.getType();
    	String lineId = signInfo.getLineId();
    	String shopNo = signInfo.getShopNo();
    	String markType = signInfo.getMarkType();
    	Double latitude = signInfo.getLatitude();
    	Double longitude = signInfo.getLongitude();
    	
    	StringBuilder signKey = new StringBuilder();
    	signKey.append(DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT_6));
    	signKey.append("_");
    	signKey.append(userId);
    	signKey.append("_");
    	signKey.append(signType);
    	
    	int ii = signKey.toString().lastIndexOf("_");
		String recordId = signKey.toString().substring(0, ii);
    	String signTimeRecord = JedisUtils.get(signKey.toString());
    	
    	try{
	    	if(signType == 1 && StringUtils.isBlank(signTimeRecord)){
	    		
	    		try{
		    		//如果第一次签到，则添加一条签到时间记录到时间记录表中
		    		SignTimeRecord signTimeVO = new SignTimeRecord();
		        	signTimeVO.setId(IdGen.uuid());
		        	signTimeVO.setUserId(userId);
		        	signTimeVO.setWeek(DateUtils.getChineseWeekday());
		        	signTimeVO.setStartTime(DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT));
		        	//防止重复签到场景
		        	int count = signTimeRecordMapper.checkIsExist(userId);
		        	if(count>0){
		        		signTimeRecordMapper.updateSignTimeByUserId(signTimeVO);
		        	}else{
		        		signTimeRecordMapper.insertSelective(signTimeVO);
		        	}
		        	
		        	//计算过期时间
		    		String startTime = DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT);
		    		String endTime = DateUtils.dateToString(DateUtils.addDays(currDate, 1), "yyyy-MM-dd")+" 00:00:00";
		    		int cacheSeconds =new Long( DateUtils.dateDiff(startTime, endTime, DateUtils.DATETIME_FORMAT)).intValue();
		    		//redis中记录签到
		    		JedisUtils.set(signKey.toString(), "YES", cacheSeconds);
		    		
		    		//获取所有配置数据字典中非业务员部门的列表，如果当前用户部门在list范围内，则跳过消息推送逻辑
		    		List<String> deptFilterList = dictService.getFilterDeptList();
		    		if(!deptFilterList.contains(deptId)){
		    			//签到时检查业务员拜访计划消息推送
			    		this.sendSignWarn(signInfo, currDate, cacheSeconds);
		    		}
		    		//================签到后发送拜访消息提醒 start (屏蔽下方代码块，启用上一行独立方法代替)=======================
		    		/*Map<String,Object> paraMap = new HashMap<String,Object>();
		    		paraMap.put("salesmanId", userId);
		    		paraMap.put("week", DateUtils.getChineseWeekday());
		    		List<HashMap<String,Object>> lineList = linePlansService.getMyTodayVisitPansList(paraMap);
		    		String visitSubject = null;
		    		if(null != lineList && !lineList.isEmpty()){
		    			HashMap<String,Object> lineMap = lineList.get(0);
		    			String lineName = (String)lineMap.get("lineName");
		    			//lineName = lineName.contains("路线")?lineName:lineName+"路线";
		    			//提醒：您今天拜访xxx路线，祝你旗开得胜
		    			visitSubject = "您今天拜访"+lineName+"，祝你旗开得胜！";
		    			UMengPushTools.getInstance().sendSignVisitCustomizedcast(userId, lineName, true);
		    		}else{
		    			//提醒：糟糕，您今天的拜访计划路线尚未安排
		    			visitSubject = "糟糕，您今天的拜访计划路线尚未安排！";
		    			UMengPushTools.getInstance().sendSignVisitCustomizedcast(userId, null, false);
		    		}
		    		
					//记录个人未查阅的警告消息数
					long warnTotal = JedisUtils.incr(Constants.AJ_PLANS_CHANGE_TOTAL_KEY+userId);
						
					Map<String,Object> warnObj = new HashMap<String,Object>();
					warnObj.put("subject", visitSubject);
					warnObj.put("type", "6");
					warnObj.put("total", warnTotal+"");
					warnObj.put("typeName", "拜访计划");
					warnObj.put("createTime", DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT_7));
					
					//保存个人最新消息提醒信息(覆盖原来的消息)
					JedisUtils.setObject(Constants.AJ_PLANS_CHANGE_KEY+userId, warnObj, 0);*/
					//================签到后发送拜访消息提醒 end=======================
					
	    		}catch(Exception e){
	    			logger.error("=======================上班签到方法块异常，异常信息为：{}",e);
	    		}
	    		
	    	}else if(signType == 1 && StringUtils.isNotBlank(signTimeRecord)){
	    		json.setSuccess(false);
				json.setCode("500");
				json.setMsg("今日已上班签到！");
				return json;
	    	}else if(signType == 2 && StringUtils.isBlank(signTimeRecord)){
	    		
	    		String startWorkSign = JedisUtils.get(recordId+"_1");
	    		//签下班到规则： 签下班到必须先签上班到
	    		if(StringUtils.isBlank(startWorkSign)){
	    			json.setSuccess(false);
	    			json.setCode("500");
	    			json.setMsg("未签上班到，不能签下班到！");
	    			return json;
	    		}else{
	        		//修改下班签到时间记录表中
	        		SignTimeRecord signTimeVO = new SignTimeRecord();
	            	signTimeVO.setUserId(userId);
	            	signTimeVO.setEndTime(DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT));
	            	signTimeRecordMapper.updateSignTimeByUserId(signTimeVO);
	    			
	    			//计算过期时间
	        		String startTime = DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT);
	        		String endTime = DateUtils.dateToString(DateUtils.addDays(currDate, 1), "yyyy-MM-dd")+" 00:00:00";
	        		int cacheSeconds =new Long( DateUtils.dateDiff(startTime, endTime, DateUtils.DATETIME_FORMAT)).intValue();
	        		//redis中记录签到
	        		JedisUtils.set(signKey.toString(), "YES", cacheSeconds);
	    		}
				
	    	}else if(signType == 2 && StringUtils.isNotBlank(signTimeRecord)){
	    		json.setSuccess(false);
				json.setCode("500");
				json.setMsg("今日已下班签到！");
				return json;
	    	}else if(signType == 3){
	    		
	    		String startWorkSign = JedisUtils.get(recordId+"_1");
	    		String endWorkSign = JedisUtils.get(recordId+"_2");
	    		if(StringUtils.isBlank(startWorkSign) && StringUtils.isBlank(endWorkSign)){
	    			json.setSuccess(false);
	    			json.setCode("500");
	    			json.setMsg("注意：上班签到后，方可外勤签到！");
	    			return json;
	    		}
	    		/*else if(StringUtils.isNotBlank(startWorkSign) && StringUtils.isNotBlank(endWorkSign)){
	    			json.setSuccess(false);
	    			json.setCode("500");
	    			json.setMsg("注意：下班签到后，不可外勤签到！");
	    			return json;
	    		}*/
	    		
	    		if(StringUtils.isBlank(signTimeRecord)){
	    			//如果第一次外勤签到，则修改一条外勤签到时间记录表中
	        		SignTimeRecord signTimeVO = new SignTimeRecord();
	        		signTimeVO.setId(IdGen.uuid());
	        		signTimeVO.setUserId(userId);
	        		signTimeVO.setWeek(DateUtils.getChineseWeekday());
	            	signTimeVO.setOutWorkStart(DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT));
	            	int count = signTimeRecordMapper.checkIsExist(userId);
	            	if(count>0){
	            		signTimeRecordMapper.updateSignTimeByUserId(signTimeVO);
	            	}else{
	            		signTimeRecordMapper.insertSelective(signTimeVO);
	            	}
	            	
	        		//计算过期时间
	        		String startTime = DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT);
	        		String endTime = DateUtils.dateToString(DateUtils.addDays(currDate, 1), "yyyy-MM-dd")+" 00:00:00";
	        		int cacheSeconds =new Long( DateUtils.dateDiff(startTime, endTime, DateUtils.DATETIME_FORMAT)).intValue();
	        		//redis中记录签到
	        		JedisUtils.set(signKey.toString(), "YES", cacheSeconds);
	        		
	    		}else if(StringUtils.isNotBlank(signTimeRecord)){
	        		
	        		//如果第二次外勤签到，则修改后续外勤签到时间记录表的外勤签到最新时间字段中
	        		SignTimeRecord signTimeVO = new SignTimeRecord();
	        		signTimeVO.setUserId(userId);
	            	signTimeVO.setOutWorkEnd(DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT));
	            	signTimeRecordMapper.updateSignTimeByUserId(signTimeVO);
	            	//记录当前拜访店铺签到状态(修改)
            		signInfoMapper.updateShopVisitRecord(signInfo);
	        	}
	    		
	    		//==========================外勤签到记录信息方法块 start==============================
	    		try{
		    		//第一步：检查线路数据是否在tbl_shop_visit_his_record_t表，如果不存在则insert
		    		int lineNum = visitHisRecordService.checkTodayLineIsExist(lineId);
		    		int shopNum = visitHisRecordService.checkTodayShopNoIsExist(shopNo);
		    		
		    		if(lineNum==0){
		        		//第一次外勤签到需要初始化一条当前路线数据，供累计汇总使用
		        		visitHisRecordService.insertVisitHisRecord(lineId);
		    		}
		    		
		    		if(shopNum==0){
	            		//记录当前拜访店铺签到状态（新增）
	            		signInfo.setCreateTime(DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT));
	            		signInfoMapper.insertShopVisitRecord(signInfo);
		    		}else{
	            		//记录当前拜访店铺签到状态(修改)
	            		signInfoMapper.updateShopVisitRecord(signInfo);
		    		}
		    		
		    		//第二步：如果外勤签的是离店到，则判断tbl_shop_visit_record_t表中对应店铺是否已经签过离店，如果签过则不再更新统计累加操作
		    		int visitNum = visitHisRecordService.checkShopVisitStatus(shopNo);
		    		if("2".equals(markType) && visitNum==0){
		    			visitHisRecordService.updateVisitHisRecord(lineId);
		    		}
		    		
		    		//如果客户经纬度为空，则更新客户坐标信息
		    		Map<String,Object> paraMap = new HashMap<String,Object>();
		    		paraMap.put("latitude", latitude);
		    		paraMap.put("longitude", longitude);
		    		paraMap.put("shopNo", shopNo);
		    		shopService.updateShopCoordinate(paraMap);
	    		}catch(Exception e){
	    			logger.error("=======================外勤签到记录信息方法块异常，异常信息为：{}",e);
	    		}
	    		//==========================外勤签到记录信息方法块 end==============================
	    	}
	    	
	    	//对app端转码的中文进行解码处理
	    	String remarks = signInfo.getRemarks();
	    	String address = signInfo.getAddress();
	    	address = Encodes.urlDecode(address);
	    	remarks = Encodes.urlDecode(remarks);
	    	signInfo.setAddress(address);
	    	signInfo.setRemarks(remarks);
			signInfo.setId(IdGen.uuid());
			signInfo.setCreateBy(userId);
			signInfo.setWeek(DateUtils.getChineseWeekday());
			signInfo.setCreateTime(DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT));
	    	if(StringUtils.isNotBlank(deptId)){
	    		signInfo.setGroupId(deptId);
	    	}
	    	
	    	//手动签到时记录用户轨迹
	    	this.addTrackRecord(signInfo);
			int num = signInfoMapper.insertSignInfo(signInfo);
			
			if(num>0){
				
				try{
					//如果是外勤签到，则需要更新当前客户对应的数据（需要将数据分析统计表记录上拜访标识）
					if(StringUtils.isNotBlank(shopNo) && signType == 3){
						String dayTimeStr = DateUtils.dateToCompactString(currDate);//yyyyMMdd
					    Integer dayTime = Integer.parseInt(dayTimeStr);
						storeTurnoverService.updateIsVisitByStoreId(dayTime, shopNo, 1);
					}
				}catch(Exception e){
					logger.error("=======================调研数据分析接口异常，异常信息为：{}",e);
				}
			   
				json.setMsg("添加签到信息成功！");
				json.setSuccess(true);
			}else{
				//防止insert失败的时候redis中保存的场景
				JedisUtils.del(signKey.toString());
				json.setSuccess(false);
				json.setCode("500");
				json.setMsg("添加签到信息失败！");
			}
			
    	}catch(Exception e){
    		//防止insert失败的时候redis中保存的场景
    		JedisUtils.del(signKey.toString());
    		throw new Exception(e);
    	}
		return json;
	}
	
	/**
	 * 签到时检查业务员拜访计划消息推送
	 * @param signInfo
	 * @param currDate
	 * @param cacheSeconds
	 */
	public void sendSignWarn(SignInfo signInfo,Date currDate,int cacheSeconds){
		
		try{
		    User redisUser = null;
		    String deptId = signInfo.getDeptId();
		    String userId = signInfo.getUserId();
		    String token = signInfo.getToken();
		    String salesmanName = null;
		    
		    //因为签到方法缺少用户名称，所以通过redis取得用户对象，再获取用户名
			if(StringUtils.isNotBlank(token)){
				redisUser = (User)JedisUtils.getObject(Constants.TOKEN_PREFIX_KEY+token);
			}
			
			//如果用户对象在redis中找不到，则按照用户ID查询用户信息
			if(null == redisUser){
				redisUser = new User();
				com.corner.rpc.salesman.model.User userVO = userService.getUserById(userId);
				BeanUtils.copyProperties(userVO, redisUser);
			}
			
			if(null != redisUser){
				salesmanName = redisUser.getUserName();
			}
			
			//================签到后发送拜访消息提醒 start=======================
    		Map<String,Object> paraMap = new HashMap<String,Object>();
    		paraMap.put("salesmanId", userId);
    		paraMap.put("week", DateUtils.getChineseWeekday());
    		List<HashMap<String,Object>> lineList = linePlansService.getMyTodayVisitPansList(paraMap);
    		String visitSubject = null;
    		if(null != lineList && !lineList.isEmpty()){
    			HashMap<String,Object> lineMap = lineList.get(0);
    			String lineName = (String)lineMap.get("lineName");
    			//lineName = lineName.contains("路线")?lineName:lineName+"路线";
    			//提醒：您今天拜访xxx路线，祝你旗开得胜
    			visitSubject = "您今天拜访"+lineName+"，祝你旗开得胜！";
    			UMengPushTools.getInstance().sendSignVisitCustomizedcast(userId, lineName, true);
    		}else{
    			//提醒：糟糕，您今天的拜访计划路线尚未安排
    			visitSubject = "糟糕，您今天的拜访计划路线尚未安排！";
    			UMengPushTools.getInstance().sendSignVisitCustomizedcast(userId, null, false);
    			
    			//====================通知领导跟进 start===========================
    			String leaderTile = null;
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
    					leaderTile = salesmanName+"今日拜访计划尚未安排,请尽快安排！";
    					Map<String,Object> warnObj = new HashMap<String,Object>();
    					warnObj.put("subject", leaderTile);
    					warnObj.put("type", "5");
    					warnObj.put("total", "0");
    					warnObj.put("typeName", "拜访提醒");
    					warnObj.put("createTime", DateUtils.dateToString(currDate, DateUtils.DATETIME_FORMAT_7));
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
    		
			//记录个人未查阅的警告消息数
			long warnTotal = JedisUtils.incr(Constants.AJ_PLANS_CHANGE_TOTAL_KEY+userId);
				
			Map<String,Object> warnObj = new HashMap<String,Object>();
			warnObj.put("subject", visitSubject);
			warnObj.put("type", "6");
			warnObj.put("total", warnTotal+"");
			warnObj.put("typeName", "拜访计划");
			warnObj.put("createTime", DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT_7));
			
			//保存个人最新消息提醒信息(覆盖原来的消息)
			JedisUtils.setObject(Constants.AJ_PLANS_CHANGE_KEY+userId, warnObj, 0);
			//================签到后发送拜访消息提醒 end=======================
		}catch(Exception e){
			logger.error("=======================调用签到时检查拜访计划消息知会方法异常，异常信息为：{}",e);
		}
	}
	
	/**
	 * 手动签到时记录用户轨迹
	 * @param signInfo
	 */
	public void addTrackRecord(SignInfo signInfo){
		TrackRecord trackRecord = new TrackRecord();
		BeanUtils.copyProperties(signInfo, trackRecord);
		trackRecord.setPositionName(signInfo.getAddress());//填充位置名称
		Date date = new Date();
		trackRecord.setTimePoint(DateUtils.dateToString(date, DateUtils.HOUR_TIME_FORMAT));
		trackRecord.setId(IdGen.uuid());
		trackRecord.setCreateTime(DateUtils.dateToString(date, DateUtils.DATETIME_FORMAT));
		//2、往历史表中添加
		trackRecordMapper.insertTrackRecord(trackRecord);
		//3、根据用户ID删除最新个人轨迹表中的数据
		trackRecordMapper.deleteTrackRecordNewByUserId(trackRecord);
		//4、分别往最新轨迹表和轨迹表中存储
		trackRecordMapper.insertTrackRecordNew(trackRecord);
	}
	
    /**
     * 检查用户是否已经签到过
     * @param signInfo
     * @return
     */
    public int checkUserIsSignt(SignInfo signInfo) throws Exception{
    	return signInfoMapper.checkUserIsSignt(signInfo);
    }
}
	
