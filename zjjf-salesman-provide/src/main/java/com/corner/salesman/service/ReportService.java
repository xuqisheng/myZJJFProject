package com.corner.salesman.service;

import javax.servlet.http.HttpServletRequest;

import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.AppReport;
import com.corner.salesman.model.DailyReply;
import com.corner.salesman.model.Report;

/**
 * @描述： 报告业务层接口
 * @author Longx
 * @创建时间  2016-01-26
 */
public interface ReportService {

	/**
	 * 查询报告信息(分页方法)
	 * @param signInfo
	 * @return
	 */
    public Page<Report> findReportList(Page<Report> page, Report report) throws Exception;
    
	/**
	 * 查询我的报告信息(分页方法)
	 * @param signInfo
	 * @return
	 */
    public Page<Report> findMyReportList(Page<Report> page, Report report) throws Exception;
	
    /**
     * 根据报告ID查询报告信息
     * @param reportId
     * @return
     */
	public AppReport findReportDetailById(Report report) throws Exception;
	
    /**
     * 添加报告信息
     * @param reportId
     * @return
     */
	public void addReportInfo(Report report) throws Exception;
	
	/**
	 * 添加报告评论
	 * @param replyVo
	 * @throws Exception
	 */
	//public void addReportComment(ReportReply replyVo) throws Exception;
	/**
	 * 删除日志
	 * @param record
	 * @return
	 * @throws Exception 
	 */
	public  void delDailyCommnent(DailyReply record) throws Exception;

	
	
}
