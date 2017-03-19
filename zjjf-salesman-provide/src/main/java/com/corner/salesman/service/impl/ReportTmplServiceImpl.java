package com.corner.salesman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.salesman.dao.ReportTemplateMapper;
import com.corner.salesman.model.ReportTemplate;
import com.corner.salesman.service.ReportTmplService;

/**
 * @描述： 报告业务层实现类
 * @author Longx
 * @创建时间  2016-01-26
 */
@Service
public class ReportTmplServiceImpl implements ReportTmplService {

	@Autowired
	private ReportTemplateMapper tmplMapper;
	
    /**
     * 根据模板类型获取对应模板字段列表
     * @param type
     * @return
     */
	@Override
	public List<ReportTemplate> findReportTemplateByType(String type) throws Exception {
		return tmplMapper.findReportTemplateByType(type);
	}

}
