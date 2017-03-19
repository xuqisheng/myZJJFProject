package com.corner.salesman.dao;

import java.util.HashMap;
import java.util.List;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.ReportTemplate;

@MyBatisDao
public interface ReportTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportTemplate record);

    int insertSelective(ReportTemplate record);

    ReportTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportTemplate record);

    int updateByPrimaryKey(ReportTemplate record);
    
    /**
     * 根据模板类型获取对应模板字段列表
     * @param type
     * @return
     */
    List<ReportTemplate> findReportTemplateByType(String type);
    
    HashMap<String,Object> findTemplateByType(String type);
}