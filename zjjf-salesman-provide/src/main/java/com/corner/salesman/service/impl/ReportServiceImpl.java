package com.corner.salesman.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.common.utils.IdGen;
import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.commons.push.UMengPushTools;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.dao.DailyReplyMapper;
import com.corner.salesman.dao.ReportMapper;
import com.corner.salesman.dao.ReportMarkReadMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.model.AppReport;
import com.corner.salesman.model.DailyReply;
import com.corner.salesman.model.Field;
import com.corner.salesman.model.Report;
import com.corner.salesman.model.ReportMarkRead;
import com.corner.salesman.model.UserDept;
import com.corner.salesman.service.ReportService;
import com.google.common.collect.Lists;

/**
 * @描述：报告业务层实现类
 * @author Longx
 * @创建时间  2016-01-26
 */
@Service
public class ReportServiceImpl implements ReportService {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Autowired
	private ReportMapper reportMapper; 
	@Autowired
	private DailyReplyMapper replyMapper;
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private ReportMarkReadMapper reportMarkMapper;
	
	/**
	 * 查询日报信息(分页方法)
	 * @param signInfo
	 * @return
	 */
    public Page<Report> findReportList(Page<Report> page, Report report) throws Exception{
    	// 设置分页参数
    	report.setPage(page);
    	String userId = report.getUserId();
		//该属性标识是否清空消息队列【0：正常刷新（清除）;1:发布刷新（不清除）】
		String queryType = report.getQueryType();
		
		//根据用户ID粗略校验，如果在管理映射表中有用户可以查询具体的数据信息，否则则能看到自己
		int num = userDeptMapper.checkIsLeader(userId);
    	if(num>0){
    		//1.如果检查管理者的映射有值，则查询部门所有数据，userId必须为空
    		report.setUserId(null);
    		
    		//2.此处逻辑是把不是一个部门，但是评论过的也并到有权限的用户查询的数据列表中
    		List<String> reportList = replyMapper.findReportIdListByUserId(userId);
    		if(!reportList.isEmpty()){
    			boolean bool = false;
    			int size = reportList.size();
    			StringBuffer sbStr = new StringBuffer();
    			for(int i=0;i<size;i++){
    				sbStr.append("'");
    				sbStr.append(reportList.get(i));
    				sbStr.append("'");
    				if(i<size-1){
    					sbStr.append(",");
    				}
    				bool = true;
    			}
    			if(bool){
    				//3.把阔部门评论过的日报ID做为条件传到查询sql中
    				report.setReportId(sbStr.toString());
    			}
    		}
    	}
		
    	// 执行分页查询
		List<Report> list = reportMapper.findReportList(report);
		List<Field> fieldList = null;
		
		for(Report reportVo : list){
			//准备前面3个字段的显示
			String content = reportVo.getContent();
        	
        	//如果内容不为空则根据规则组装报告详情供展示
        	if(StringUtils.isNotBlank(content)){
        		//清空列表中的内容信息，展示的时候不需要此字段
        		reportVo.setContent(null);
        		
        		//今日拜访路线@输入内容;本线路便利店总数@2;本线路便利店总数@[:no]；
        		String[] keyValStr = content.split(Constants.SEMICOLON);
        		fieldList = new ArrayList<Field>();
        		
        		int total = 0;//统计有效字段属性
        		for(int i=0;i<keyValStr.length;i++){
        			//只取前面3个字段供界面展示
        			if(total>2){
        				break;
        			}
        			String[] keyValSet = keyValStr[i].split(Constants.DIVISION);
        			String key = null;
        			String val = null;
        			if(keyValSet.length>1){
            			key = keyValSet[0];
            			val = keyValSet[1];
        			}
        			if(StringUtils.isNotBlank(val) && !"[:no]".equals(val)){
        				fieldList.add(new Field(key,val));
        				total+=1;
        			}
        		}
        	}
			
			//回填评论信息
			String reportId = reportVo.getReportId();
			List<DailyReply> replyList = replyMapper.findDailyReplyListById(reportId);
			reportVo.setReplyList(replyList);
			reportVo.setFieldList(fieldList);
			
			//回填关注人
			String participant = reportMarkMapper.findReportMarkRecordById(reportId);
			reportVo.setParticipant(participant);
		}
		page.setList(list);
		
		//如果app端请求查询类型为0，则需要清空redis对应记录key,为1则只查询不清空
		if(!"1".equals(queryType)){
			//清空个人日报更新redis记录数
			JedisUtils.set(Constants.NEW_DAILY_TOTAL_KEY+userId, "0", 0);
			//删除reids中存储的最新消息列表
			JedisUtils.delObject(Constants.NEWS_LIST_PREFIX_KEY+userId);
		}
		return page;
    }
	
	
    
	/**
	 * 查询我的报告信息(分页方法)
	 * @param signInfo
	 * @return
	 */
    @Override
    public Page<Report> findMyReportList(Page<Report> page, Report report) throws Exception{
    	// 设置分页参数
    	report.setPage(page);
    	
    	//根据用户ID粗略校验，如果在管理映射表中有用户可以查询具体的数据信息，否则则能看到自己
		int num = userDeptMapper.checkIsLeader(report.getUserId());
    	if(num==0){
    		report.setDeptId(null);
    	}
		// 执行分页查询
		List<Report> list = reportMapper.findMyReportList(report);
		
		List<Field> fieldList = null;
		
		for(Report reportVo : list){
			//准备前面3个字段的显示
			String content = reportVo.getContent();
			String picUrl = reportVo.getPicUrl();
        	
        	//如果内容不为空则根据规则组装报告详情供展示
        	if(StringUtils.isNotBlank(content)){
        		//清空列表中的内容信息，展示的时候不需要此字段
        		reportVo.setContent(null);
        		
        		//今日拜访路线@输入内容;本线路便利店总数@2;本线路便利店总数@[:no]；
        		String[] keyValStr = content.split(Constants.SEMICOLON);
        		fieldList = new ArrayList<Field>();
        		
        		int total = 0;//统计有效字段属性
        		for(int i=0;i<keyValStr.length;i++){
        			//只取前面3个字段供界面展示
        			if(total>2){
        				break;
        			}
        			String[] keyValSet = keyValStr[i].split(Constants.DIVISION);
        			String key = null;
        			String val = null;
        			if(keyValSet.length>1){
            			key = keyValSet[0];
            			val = keyValSet[1];
        			}
        			if(StringUtils.isNotBlank(val) && !"[:no]".equals(val)){
        				fieldList.add(new Field(key,val));
        				total+=1;
        			}
        		}
        		reportVo.setFieldList(fieldList);
        		
        	}
        	
			//===========将图片切割放于list中=======start==========
			if(StringUtils.isNotBlank(picUrl)){
				List<String> picList = Lists.newArrayList();
				String[] picItem = picUrl.split(",");
				for (int i = 0; i < picItem.length; i++) {
					//如果图片url不为空则拼接完整的url地址
					picList.add(picItem[i]);
				}
				//将图片ID置空
				reportVo.setPicUrl(null);
				reportVo.setPicList(picList);
			}
			//===========将图片切割放于list中=======end==========
			
			//回填评论信息
			String reportId = reportVo.getReportId();
			List<DailyReply> replyList = replyMapper.findDailyReplyListById(reportId);
			reportVo.setReplyList(replyList);
			//回填关注人
			String participant = reportMarkMapper.findReportMarkRecordById(reportId);
			reportVo.setParticipant(participant);
		}
		
		page.setList(list);
		//清空个人日报更新redis记录数
		JedisUtils.set(Constants.NEW_DAILY_TOTAL_KEY+report.getUserId(), "0", 0);
		//删除reids中存储的最新消息列表
		JedisUtils.delObject(Constants.NEWS_LIST_PREFIX_KEY+report.getUserId());
		//清空个人日报更新redis记录数
		//JedisUtils.set(Constants.DAILY_COMMENT_KEY+report.getUserId(), "0", 0);
		return page;
    }
	


    /**
     * 根据报告ID查询报告信息
     * @param reportId
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public AppReport findReportDetailById(Report report) throws Exception{
    	List picList = null;
    	List<Field> fieldList = null;
    	AppReport appReport = null;
    	String reportId = report.getReportId();
    	String userId = report.getUserId();
    	Report reportVo = reportMapper.findReportById(reportId);
    	
    	if(null != reportVo){
    		appReport = new AppReport();
        	BeanUtils.copyProperties(reportVo, appReport);
        	
        	String picUrl = reportVo.getPicUrl();
        	String content = reportVo.getContent();
        	
        	//如果内容不为空则根据规则组装报告详情供展示
        	if(StringUtils.isNotBlank(content)){
        		//今日拜访路线@输入内容;本线路便利店总数@2;本线路便利店总数@[:no]；
        		String[] keyValStr = content.split(Constants.SEMICOLON);
        		fieldList = new ArrayList<Field>();
        		for(String keyVal : keyValStr){
        			String[] keyValSet = keyVal.split(Constants.DIVISION);
        			String key = null;
        			String val = null;
        			if(keyValSet.length>1){
            			key = keyValSet[0];
            			val = keyValSet[1];
        			}
        			if(StringUtils.isNotBlank(val) && !"[:no]".equals(val)){
        				fieldList.add(new Field(key,val));
        			}
        		}
        	}
        	
    		//将图片切割放于list中
    		if(StringUtils.isNotBlank(picUrl)){
    			picList = new ArrayList();
    			String[] picItem = picUrl.split(",");
    			for (int i = 0; i < picItem.length; i++) {
    				//如果图片url不为空则拼接完整的url地址
    				picList.add(picItem[i]);
    			}
    		}
    		
        	appReport.setFieldList(fieldList);
        	appReport.setPicList(picList);
    	}
    	
    	//记录谁查阅过该日志（如果存在则删除，保存最新的）
    	String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
    	ReportMarkRead record = new ReportMarkRead();
    	record.setReportId(reportId);
    	record.setUserId(userId);
    	record.setCreateTime(createTime);
    	reportMarkMapper.deleteReportMarkById(record);
    	reportMarkMapper.insertSelective(record);
    	//回填关注人
		String participant = reportMarkMapper.findReportMarkRecordById(reportId);
		appReport.setParticipant(participant);
		return appReport;
    }
	
    /**
     * 添加报告信息
     * @param reportId
     * @return
     */
	@Override
	public void addReportInfo(Report report) throws Exception{
		String userId = report.getUserId();
		String userName = report.getUserName();
		Date date = new Date();
		String createTime = DateUtils.dateToString(date, DateUtils.DATETIME_FORMAT);
		String reportId = IdGen.uuid();
		report.setReportId(reportId);
		report.setCreateBy(userId);
		report.setUpdateBy(userId);
		report.setCreateTime(createTime);
		report.setUpdateTime(createTime);
		report.setWeek(DateUtils.getChineseWeekday());
		int num = reportMapper.insertSelective(report);
		
		StringBuffer sbAlias =  new StringBuffer();
		//如果大约0表示保存成功，则可以通知用户
		if(num>0){
			//添加redis中添加知会对应管理者的标识
			List<UserDept> leaderList = userDeptMapper.findLeaderListByDeptId(report.getDeptId());
			for(UserDept ld : leaderList){
				String leaderId = ld.getUserId();
				if(!userId.equals(leaderId)){
					
					sbAlias.append(leaderId).append(",");
					//新日志的redis统计
					JedisUtils.incr(Constants.NEW_DAILY_TOTAL_KEY+leaderId);
				}
			}
			
			//调用推送提醒消息
			String alias = sbAlias.toString();
			if(StringUtils.isNotBlank(alias)){
				logger.info("==============================提交日报addReportInfo方法,知会领导人有：{}",alias);
				String ticker = userName+"提交新的日志！";
				UMengPushTools.getInstance().sendReportCustomizedcast(alias,ticker);
			}
		}

	}



	@Override
	@Transactional(readOnly = false)
	public void delDailyCommnent(DailyReply record) throws Exception{
			replyMapper.delDailyCommnent(record);
		
	}
}
