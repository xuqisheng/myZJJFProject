package com.corner.salesman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.DailyReply;
/**
 * 日报回复接口
 * @author yuanbao
 * 
 */
@MyBatisDao
public interface DailyReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DailyReply record);

    int insertSelective(DailyReply record);

    DailyReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DailyReply record);

    int updateByPrimaryKey(DailyReply record);
    
    void delDailyCommnent(DailyReply record);
    /**
     * 该接口是在评论日报时，修改对应日报的修改时间，目的是为了接口评论排序问题
     * @param record
     * @return
     */
    int updateDailyRepyTimeById(DailyReply record);
    
    /**
     * 根据日报ID查询日报回复列表信息
     * @param reportId
     * @return
     */
    List<DailyReply> findDailyReplyListById(String reportId);
    
    /**
     * 查询日报相关人ID（参与人包括，日报创建人、日报评论人及日报回复人）
     * @param record
     * @return
     */
    List<String> findDailyRelatedUser(DailyReply record);
    
    /**
     * 根据日报ID查询日报创建人ID(v1.1版本使用)
     * @param record
     * @return
     */
    String findDailyCreateById(DailyReply record);
    
    /**
     * 根据日报ID查询日报创建人ID(v1.2版本使用)
     * @param record
     * @return
     */
    String findReportCreateById(DailyReply record);
    
    /**
     * 查询用户ID查询评论过的报告ID列表
     * @param record
     * @return
     */
    List<String> findReportIdListByUserId(String userId);
    
    
    /**
     * 根据日报ID查询日报历史回复列表信息
     * @param reportIds
     * @return
     */
    List<DailyReply> getMyHisComment(DailyReply replyVO);
    
	/**
	 * 获取与用户相关的最新评论消息(v1.6.0版本启用)
	 * @param paramMap
	 * @return
	 */
	public HashMap<String,Object> getNewComment2One(Map<String,Object> paramMap);
    
}