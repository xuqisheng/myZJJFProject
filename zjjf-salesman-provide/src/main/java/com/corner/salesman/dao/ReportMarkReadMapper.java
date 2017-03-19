package com.corner.salesman.dao;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.ReportMarkRead;
@MyBatisDao
public interface ReportMarkReadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportMarkRead record);

    int insertSelective(ReportMarkRead record);

    ReportMarkRead selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportMarkRead record);

    int updateByPrimaryKey(ReportMarkRead record);
    
    int deleteReportMarkById(ReportMarkRead record);
    
    String findReportMarkRecordById(String reportId);
}