package com.corner.salesman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.Report;

@MyBatisDao
public interface ReportMapper {
    int deleteByPrimaryKey(String reportId);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(String reportId);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKeyWithBLOBs(Report record);

    int updateByPrimaryKey(Report record);
    
    Report findReportById(String reportId);
    
    List<Report> findReportList(Report record);
    
    List<Report> findMyReportList(Report record);
    
    /**
     * 根据报告ID获取报告创建的用户名称
     * @param reportId
     * @return
     */
    String getReportUserById(String reportId);
    
    /**
     * 根据用户ID获取自己创建的报告ID列表
     * @param reportId
     * @return
     */
    List<String> getReportIdByUserId(String userId);
    
    /**
     * 根据部门ID及用户ID 获取领导者所在部门日志及关注过的日志
     * @param record
     * @return
     */
    List<String> getLeaderReportIdList(Report record);
    
	/**
	 * 查询同自己相关的一条最新日志消息(v1.6.0版本启用)
	 * @param notice
	 * @return
	 */
	public HashMap<String,Object> getNewReport2One(Map<String,Object> paramMap);
}