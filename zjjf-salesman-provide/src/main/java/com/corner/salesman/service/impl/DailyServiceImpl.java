package com.corner.salesman.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.proxy.jdbc.ResultSetMetaDataProxyImpl;
import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.common.utils.IdGen;
import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.commons.push.UMengPushTools;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.dao.DailyMapper;
import com.corner.salesman.dao.DailyReplyMapper;
import com.corner.salesman.dao.ReportMapper;
import com.corner.salesman.dao.ReportTemplateMapper;
import com.corner.salesman.dao.SequenceMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.dao.UserMapper;
import com.corner.salesman.model.AppDaily;
import com.corner.salesman.model.Daily;
import com.corner.salesman.model.DailyReply;
import com.corner.salesman.model.Report;
import com.corner.salesman.model.ReportTemplate;
import com.corner.salesman.model.User;
import com.corner.salesman.model.UserDept;
import com.corner.salesman.service.DailyService;

/**
 * @描述： 日报业务层实现类
 * @author Longx
 * @创建时间  2016-01-26
 */
@Service
public class DailyServiceImpl implements DailyService {

	@Autowired
	private DailyMapper dailyMapper;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ReportTemplateMapper tmplMapper;
	@Autowired
	private DailyReplyMapper replyMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private ReportMapper reportMapper;
//	@Autowired
//	private StringRedisTemplate redisTemplate;
	@Autowired
	private SequenceMapper sequenceMapper;
	
    /**
     * 根据条件查询日报信息
     * @param daily
     * @return
     */
	@Override
	public List<Daily> findDailyList(Daily daily) throws Exception {
		List<Daily> dailyList = dailyMapper.findDailyList(daily);
		//回填评论信息
		for(Daily dailyVo : dailyList){
			String reportId = dailyVo.getReportId();
			List<DailyReply> replyList = replyMapper.findDailyReplyListById(reportId);
			dailyVo.setReplyList(replyList);
		}
		
		return dailyList;
	}
	
	/**
	 * 查询日报信息(分页方法)
	 * @param signInfo
	 * @return
	 */
    public Page<Daily> findDailyList(Page<Daily> page, Daily daily) throws Exception{
    	// 设置分页参数
    	daily.setPage(page);
    	String userId = daily.getUserId();
		//根据用户ID粗略校验，如果在管理映射表中有用户可以查询具体的数据信息，否则则能看到自己
		int num = userDeptMapper.checkIsLeader(userId);
    	if(num>0){
    		//1.如果检查管理者的映射有值，则查询部门所有数据，userId必须为空
    		daily.setUserId(null);
    		
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
    				daily.setReportId(sbStr.toString());
    			}
    		}
    	}
		
		// 执行分页查询
		List<Daily> list = dailyMapper.findDailyList(daily);
		
		//如果当前部门没有任何数据则查询自己的数据（此场景防止用户从一个部门切换到另外一个部门的情况下，如果一条日报都没有则取自己之前部门创建的日报；如果现在所在部门有日报就不会进入该代码块）
		if(list.isEmpty()){
			daily.setDeptId(null);
			daily.setUserId(userId);
			list = dailyMapper.findMyDailyList(daily);
		}
		
		//回填评论信息
		for(Daily dailyVo : list){
			String reportId = dailyVo.getReportId();
			List<DailyReply> replyList = replyMapper.findDailyReplyListById(reportId);
			dailyVo.setReplyList(replyList);
		}
		page.setList(list);
		
		//清空个人日报更新redis记录数
		JedisUtils.set(Constants.NEW_NOTICE_TOTAL_KEY+userId, "0", 0);
		
		return page;
    }
    
	/**
	 * 查询我的日报信息(分页方法)
	 * @param signInfo
	 * @return
	 */
    @Override
    public Page<Daily> findMyDailyList(Page<Daily> page, Daily daily) throws Exception{
    	// 设置分页参数
    	daily.setPage(page);
    	String userId = daily.getUserId();
    	//如果当前用户是领导则小于0，将部门ID置空，仅仅使用用户ID查询自己的日报
    	int num = userDeptMapper.checkIsLeader(userId);
    	if(num<=0){
    		daily.setDeptId(null);
    	}
    	
		// 执行分页查询
		List<Daily> list = dailyMapper.findMyDailyList(daily);
		//回填评论信息
		for(Daily dailyVo : list){
			String reportId = dailyVo.getReportId();
			List<DailyReply> replyList = replyMapper.findDailyReplyListById(reportId);
			dailyVo.setReplyList(replyList);
		}
		page.setList(list);
		
		//清空个人日报更新redis记录数
		JedisUtils.set(Constants.DAILY_COMMENT_TOTAL_KEY+userId, "0", 0);
		return page;
    }
    
    /**
     * 根据日报ID查询日报信息
     * @param reportId
     * @return
     */
	@Override
	public Daily findDailyById(String reportId) throws Exception {
	
		return dailyMapper.selectByPrimaryKey(reportId);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public AppDaily findDailyDetailById(String reportId) throws Exception{
		//jdbcTemplate.
		StringBuffer querySql = new StringBuffer();
		querySql.append(" SELECT ");
//		querySql.append(" t.reportId, ");
		querySql.append(" t.visitLine, ");
		querySql.append(" t.distributionCenter, ");
		querySql.append(" t.lineStoreNum, ");
		querySql.append(" t.lineYetStoreHFNum, ");
		querySql.append(" t.lineStoreHFRatio, ");
		querySql.append(" t.visitStoreNum, ");
		querySql.append(" t.visitStoreReapNum, ");
		querySql.append(" t.yetDevelopStoreNum, ");
		querySql.append(" t.registerlStoreRatio, ");
		querySql.append(" t.planRegisterNum, ");
		querySql.append(" t.actualRegisterlNum, ");
		querySql.append(" t.planActiveStoreNum, ");
		querySql.append(" t.actualActiveStoreNum, ");
		
		querySql.append(" t.ownActiveStoreNum, ");
		querySql.append(" t.lineActiveStoreRatio, ");
		querySql.append(" t.todayPlanWhActiveNum, ");
		querySql.append(" t.todayActualWhActiveNum, ");
		querySql.append(" t.todayPlanTurnover, ");
		querySql.append(" t.todayLineTurnover, ");
		querySql.append(" t.todayPlanLineTurnover, ");
		
		querySql.append(" t.relativeYesterOrderNum, ");
		querySql.append(" t.todayVisitPartners, ");
		querySql.append(" t.relativeYesterTurnover, ");
		querySql.append(" t.relativeYesterBuyTurnover, ");
		querySql.append(" t.planDevelopHF, ");
		querySql.append(" t.actualDevelopHF, ");
		querySql.append(" t.extraStoreRegisterNum, ");
		querySql.append(" t.extraStoreActiveNum, ");
		querySql.append(" t.extraStoreHFNum, ");
		querySql.append(" t.planOrderNum, ");
		querySql.append(" t.actualOrderNum, ");
		querySql.append(" t.turnover, ");
		querySql.append(" t.buyTurnover, ");
		querySql.append(" t.salesTurnover, ");
		querySql.append(" t.dgWeekRegisterStoreNum, ");
		querySql.append(" t.dgUpWeekRegisterStoreNum, ");
		querySql.append(" t.dgWeekDevelopActiveNum, ");
		querySql.append(" t.dgUpWeekDevelopActiveNum, ");
		querySql.append(" t.dgWeekDevelopStoreHFNum, ");
		querySql.append(" t.dgUpWeekOwnStoreHFNum, ");
		querySql.append(" t.dgWeekPlanBuyNum, ");
		querySql.append(" t.dgWeekActualBuyNum, ");
		querySql.append(" t.dgWeekPlanTurnover, ");
		querySql.append(" t.dgWeekActualTurnover, ");
		querySql.append(" t.nextDayPlanRegisterNum, ");
		querySql.append(" t.nextDayPlanAddActiveNum, ");
		querySql.append(" t.nextDayPlanWhActiveNum, ");
		querySql.append(" t.nextDayPlanAddHFNum, ");
		querySql.append(" t.nextDayPlanWhHFNum, ");
		querySql.append(" t.nextDayOrderStoreNum, ");
		querySql.append(" t.nextDayPlanTurnover, ");
		querySql.append(" t.nextDaySalesTurnover, ");
		querySql.append(" t.nextDayBuyTurnover, ");
		querySql.append(" t.nextDayPlanVisitPartners, ");
		querySql.append(" t.nextDayVisitLine, ");
		querySql.append(" t.feedback, ");
		querySql.append(" t.strategyPlan, ");
		querySql.append(" t.support, ");
		querySql.append(" t.remark, ");
		querySql.append(" t.picUrl, ");
//		querySql.append(" t.createBy, ");
		querySql.append(" DATE_FORMAT(t.createTime, '%Y-%m-%d %T') createTime, ");
//		querySql.append(" DATE_FORMAT(t.createTime, '%T') timePoint, ");
//		querySql.append(" t.updateBy, ");
//		querySql.append(" DATE_FORMAT(t.updateTime, '%Y-%m-%d') updateTime, ");
//		querySql.append(" t.deptId, ");
		querySql.append(" t. week, ");
		querySql.append(" u.userName ");
		querySql.append(" FROM ");
		querySql.append(" tbl_user_daily_t t ");
		querySql.append(" LEFT JOIN salesman u ON u.id = t.createBy ");
		querySql.append(" WHERE t.reportId = ? ");
		PreparedStatement ps = null;
        ResultSet  rs = null;
        DruidPooledConnection conn = null;
		List picList = null;
        List<ReportTemplate> newTmplList = new ArrayList<ReportTemplate>();
        String remark = null, picUrl = null, userName = null , createTime = null;
		try {
			
			Map<String, ReportTemplate> tmplMap = (Map<String, ReportTemplate>)JedisUtils.getObject("daily_template_salasman");
			if(null == tmplMap){
				tmplMap = new HashMap<String, ReportTemplate>();
				List<ReportTemplate> tmplList = tmplMapper.findReportTemplateByType("salesman_report");
				for (ReportTemplate entry : tmplList) {  
					tmplMap.put(entry.getFieldEnName().toLowerCase(), entry);
				    //System.out.println("Key = " + entry.getFieldEnName().toLowerCase()+", value = "+entry.getFieldCnName());  
				    //System.err.println("=========================");
				} 
				JedisUtils.setObject("daily_template_salasman", tmplMap, 0);
			}
			
			
			
			conn = (DruidPooledConnection)jdbcTemplate.getDataSource().getConnection();
			//ps = (PreparedStatement)conn.prepareStatement(querySql.toString());
			ps = conn.prepareStatement(querySql.toString());
			ps.setString(1, reportId);
			rs = ps.executeQuery();
 
			ResultSetMetaDataProxyImpl rsmd = (ResultSetMetaDataProxyImpl) rs.getMetaData();
			int count=rsmd.getColumnCount();
			List<String> dbColName = new ArrayList<String>(count);
			for(int i=1;i<=count;i++){
				dbColName.add(rsmd.getColumnName(i).toLowerCase());
			}
			
			while (rs.next()) {
				for(int i=0;i<count;i++){
					String key = dbColName.get(i);
					String value = rs.getString(dbColName.get(i));
					if("remark".equals(key)){
						remark = value;
						continue;
					}else if("picurl".equals(key)){
						picUrl = value;
						continue;
					}else if("username".equals(key)){
						userName = value;
						continue;
					}else if("createtime".equals(key)){
						createTime = value;
						continue;
					}
					//System.err.println(dbColName.get(i)+","+ value);
					ReportTemplate tmplVo = tmplMap.get(key);
					if(null != tmplVo && StringUtils.isNotBlank(value)){
						tmplVo.setFieldVal(value);
						newTmplList.add(tmplVo);
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				rs.close();
			}
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		AppDaily appDaily = new AppDaily();
		appDaily.setTmplList(newTmplList);
		appDaily.setUserName(userName);
		appDaily.setRemark(remark);
		appDaily.setCreateTime(createTime);
		appDaily.setPicList(picList);
		return appDaily;
	}

    /**
     * 添加日报信息
     * @param reportId
     * @return
     */
	@Override
	public void addDailyInfo(Daily daily) throws Exception {
		String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
		daily.setReportId(IdGen.uuid());
		daily.setCreateBy(daily.getUserId());
		daily.setUpdateBy(daily.getUserId());
		daily.setCreateTime(createTime);
		daily.setUpdateTime(createTime);
		daily.setWeek(DateUtils.getChineseWeekday());
		dailyMapper.insertSelective(daily);
		
		//添加redis中添加知会对应管理者的标识
		List<UserDept> leaderList = userDeptMapper.findLeaderListByDeptId(daily.getDeptId());
		for(UserDept ud : leaderList){
			JedisUtils.incr(Constants.NEW_NOTICE_TOTAL_KEY+ud.getUserId());
		}
	}
	
	/**
	 * 添加日报评论
	 * @param replyVo
	 * @throws Exception
	 */
	@Override
	public DailyReply addDailyReply(DailyReply replyVo) throws Exception{
		Date date = new Date();
		String commentId = sequenceMapper.getNextValSeqByName("commentId");
		String createTime = DateUtils.dateToString(date, DateUtils.DATETIME_FORMAT);
		
		String userId = replyVo.getUserId();
		replyVo.setCreateBy(userId);
		replyVo.setCreateTime(createTime);
		replyVo.setCommentId(commentId);
		replyMapper.insertSelective(replyVo);
		//该接口是在评论日报时，修改对应日报的修改时间，目的是为了接口评论排序问题
		replyMapper.updateDailyRepyTimeById(replyVo);
		
		StringBuffer sbAlias =  new StringBuffer();
		//需要实现评论这条日报相关人的知会（参与人包括，日报创建人、日报评论人及日报回复人）
		List<String> userList = replyMapper.findDailyRelatedUser(replyVo);
		
		//for(String userIds : userList){
		for(int i=0;i<userList.size();i++){
			String userIds = userList.get(i);
			
			//当前评论人有可能包含的涉及用户中，故需要将自己排除
			if(!userIds.equals(userId)){
				sbAlias.append(userIds).append(",");
				//v1.6.0版本使用的统计key
				JedisUtils.incr(Constants.DAILY_COMMENT_TOTAL_KEY+userIds);
			}
		}
		
		//================我的日报中提醒（new） 逻辑开始==========================
		/*String createBy = replyMapper.findReportCreateById(replyVo);
		if(StringUtils.isNotBlank(createBy) && !createBy.equals(userId)){
			sbAlias.append(createBy).append(",");
		   //有新评论的时候知会创建人（在我的日报中提醒（new））
		   JedisUtils.incr(Constants.DAILY_COMMENT_TOTAL_KEY+createBy);
		}*/
		//================我的日报中提醒（new） 逻辑结束==========================
		
		//调用推送提醒消息
		String alias = sbAlias.toString();
		if(StringUtils.isNotBlank(alias)){
			UMengPushTools.getInstance().sendCommentCustomizedcast(sbAlias.toString());
		}
		
		return replyVo;
	}
	
	@Deprecated
	public void addDailyReply222(DailyReply replyVo) throws Exception{
		String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
		String userId = replyVo.getUserId();
		replyVo.setCreateBy(userId);
		replyVo.setCreateTime(createTime);
		replyMapper.insertSelective(replyVo);
		//该接口是在评论日报时，修改对应日报的修改时间，目的是为了接口评论排序问题
		replyMapper.updateDailyRepyTimeById(replyVo);
		
		//需要实现评论这条日报相关人的知会（参与人包括，日报创建人、日报评论人及日报回复人）
		List<String> userList = replyMapper.findDailyRelatedUser(replyVo);
		for(String userIds : userList){
			//当前评论人有可能包含的涉及用户中，故需要将自己排除
			if(!userIds.equals(userId)){
				JedisUtils.incr(Constants.NEW_NOTICE_TOTAL_KEY+userIds);
				//JedisUtils.incr(Constants.DAILY_COMMENT_KEY+userIds);//此处逻辑放到下方else中
			}
		}
		
		String createBy = replyMapper.findDailyCreateById(replyVo);
		if(StringUtils.isNotBlank(createBy)){
		   //有新评论的时候知会创建人（在我的日报中提醒（new））
		   JedisUtils.incr(Constants.DAILY_COMMENT_TOTAL_KEY+createBy);
		}
	}

    /**
     * 修改日报信息
     * @param reportId
     * @return
     */
	@Override
	public void updateDailyInfo(Daily daily) throws Exception {
		dailyMapper.updateByPrimaryKeySelective(daily);
	}
	
	
    /**
     * 根据日报ID查询评论信息列表
     * @param reportId
     * @return
     */
	@Override
	public List<DailyReply> findCommentList(String reportId) throws Exception{
		return replyMapper.findDailyReplyListById(reportId);
	}
	
	/**
	 * 获取未读评论
	 * @param replyVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DailyReply> getUnreadCommentList(DailyReply replyVO) throws Exception{
		String userId = replyVO.getUserId();
		//如果类型为1：表示查询最新消息数据，因此在历史的方法上获取最新消息，通过查询个人所有评论且倒序获取redis中最新的记录数来解决
		String commentTotal = JedisUtils.get(Constants.DAILY_COMMENT_TOTAL_KEY+userId);
		if(StringUtils.isNoneBlank(commentTotal)){
			replyVO.setCommentTotal(commentTotal);
		}else{
			replyVO.setCommentTotal("0");
		}

		List<DailyReply> commentList = null;
		List<String> reportIdList = null;
		
		//根据用户ID粗略校验，如果在管理映射表中有用户可以查询具体的数据信息，否则则能看到自己
		int num = userDeptMapper.checkIsLeader(userId);
    	if(num>0){
    		//0、根据token获取当前用户所在部门ID
    		String token = replyVO.getToken();
    		User user = (User)JedisUtils.getObject(Constants.TOKEN_PREFIX_KEY+token);
    		if(null == user){
    			user = userMapper.findUserInfoById(userId);
    		}
    		String deptId = user.getDeptId();
    		Report report = new Report();
    		report.setDeptId(deptId);
    		report.setUserId(userId);
    		//根据部门ID及用户ID 获取领导者所在部门日志及关注过的日志
    		reportIdList = reportMapper.getLeaderReportIdList(report);
    	}else{
    		//获取普通用户的日报ID
    		reportIdList = reportMapper.getReportIdByUserId(replyVO.getUserId());
    	}
		
		if(!reportIdList.isEmpty()){
			boolean bool = false;
			int size = reportIdList.size();
			StringBuffer sbStr = new StringBuffer();
			for(int i=0;i<size;i++){
				sbStr.append("'");
				sbStr.append(reportIdList.get(i));
				sbStr.append("'");
				if(i<size-1){
					sbStr.append(",");
				}
				bool = true;
			}
			if(bool){
				replyVO.setReportId(sbStr.toString());
				//3.把阔部门评论过的日报ID做为条件传到查询sql中
				commentList = replyMapper.getMyHisComment(replyVO);
			}
		}
		
		//4.如果消息列表为空则构建一个空的集合返回给app(目的方便app端判断处理)
		if(null == commentList){
			commentList =  new ArrayList<DailyReply>();
		}
		
		//清除个人评论记录数
		JedisUtils.set(Constants.DAILY_COMMENT_TOTAL_KEY+userId, "0", 0);
		
		return commentList;
	}
	
	/**
     * 根据日报ID查询评论信息列表
     * @param reportId
     * @return
     */
	@Override
	public Page<DailyReply> getHisCommentList(Page<DailyReply> page, DailyReply replyVO) throws Exception{
		
		replyVO.setPage(page);
		List<DailyReply> commentList = null;
		List<String> reportIdList = null;
		String userId = replyVO.getUserId();
    	
		//根据用户ID粗略校验，如果在管理映射表中有用户可以查询具体的数据信息，否则则能看到自己
		int num = userDeptMapper.checkIsLeader(userId);
    	if(num>0){
    		//0、根据token获取当前用户所在部门ID
    		String token = replyVO.getToken();
    		User user = (User)JedisUtils.getObject(Constants.TOKEN_PREFIX_KEY+token);
    		if(null == user){
    			user = userMapper.findUserInfoById(userId);
    		}
    		String deptId = user.getDeptId();
    		Report report = new Report();
    		report.setDeptId(deptId);
    		report.setUserId(userId);
    		//根据部门ID及用户ID 获取领导者所在部门日志及关注过的日志
    		reportIdList = reportMapper.getLeaderReportIdList(report);
    	}else{
    		//获取普通用户的日报ID
    		reportIdList = reportMapper.getReportIdByUserId(replyVO.getUserId());
    	}
		
		if(!reportIdList.isEmpty()){
			boolean bool = false;
			int size = reportIdList.size();
			StringBuffer sbStr = new StringBuffer();
			for(int i=0;i<size;i++){
				sbStr.append("'");
				sbStr.append(reportIdList.get(i));
				sbStr.append("'");
				if(i<size-1){
					sbStr.append(",");
				}
				bool = true;
			}
			if(bool){
				replyVO.setReportId(sbStr.toString());
				//3.把阔部门评论过的日报ID做为条件传到查询sql中
				commentList = replyMapper.getMyHisComment(replyVO);
			}
		}
		
		//4.如果消息列表为空则构建一个空的集合返回给app(目的方便app端判断处理)
		if(null == commentList){
			commentList =  new ArrayList<DailyReply>();
		}
		page.setList(commentList);
		
		//TODO (下方代码可以考虑删除)清空个人日报更新redis记录数
		JedisUtils.set(Constants.NEW_NOTICE_TOTAL_KEY+replyVO.getUserId(), "0", 0);
		//删除reids中存储的最新消息列表
		JedisUtils.delObject(Constants.NEWS_LIST_PREFIX_KEY+replyVO.getUserId());
		return page;
	}

}
