package com.corner.salesman.service;

import java.util.List;

import com.corner.salesman.model.ReportTemplate;

/**
 * @描述： 报告模板业务层接口
 * @author Longx
 * @创建时间  2016-01-26
 */
public interface ReportTmplService {

    /**
     * 根据模板类型获取对应模板字段列表
     * @param type
     * @return
     */
	public List<ReportTemplate> findReportTemplateByType(String type) throws Exception;
	
}
