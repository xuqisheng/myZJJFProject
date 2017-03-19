package com.corner.salesman.service;

import java.util.List;

import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.AppDaily;
import com.corner.salesman.model.Daily;
import com.corner.salesman.model.DailyReply;

/**
 * @描述： 日报业务层接口
 * @author Longx
 * @创建时间  2016-01-26
 */
public interface DailyService {

    /**
     * 根据条件查询日报信息
     * @param daily
     * @return
     */
	public List<Daily> findDailyList(Daily daily) throws Exception;
	
	/**
	 * 查询日报信息(分页方法)
	 * @param signInfo
	 * @return
	 */
    public Page<Daily> findDailyList(Page<Daily> page, Daily daily) throws Exception;
    
	/**
	 * 查询我的日报信息(分页方法)
	 * @param signInfo
	 * @return
	 */
    public Page<Daily> findMyDailyList(Page<Daily> page, Daily daily) throws Exception;
	
    /**
     * 根据日报ID查询日报信息
     * @param reportId
     * @return
     */
	public Daily findDailyById(String reportId) throws Exception;
	
	public AppDaily findDailyDetailById(String reportId) throws Exception;
	
    /**
     * 添加日报信息
     * @param reportId
     * @return
     */
	public void addDailyInfo(Daily daily) throws Exception;
	
	/**
	 * 添加日报评论
	 * @param replyVo
	 * @throws Exception
	 */
	public DailyReply addDailyReply(DailyReply replyVo) throws Exception;
	
    /**
     * 修改日报信息
     * @param reportId
     * @return
     */
	public void updateDailyInfo(Daily daily) throws Exception;
	
    /**
     * 根据日报ID查询评论信息列表
     * @param reportId
     * @return
     */
	public List<DailyReply> findCommentList(String reportId) throws Exception;
	
	/**
     * 根据日报ID查询评论信息列表
     * @param reportId
     * @return
     */
//	public List<DailyReply> getHisCommentList(DailyReply replyVO) throws Exception;
	public Page<DailyReply> getHisCommentList(Page<DailyReply> page, DailyReply replyVO) throws Exception;
	
	/**
	 * 获取未读评论
	 * @param replyVO
	 * @return
	 * @throws Exception
	 */
	public List<DailyReply> getUnreadCommentList(DailyReply replyVO) throws Exception;
}
