package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.supplier.SpGroupDailyReport;
import com.zjjf.analysis.beans.analysis.supplier.StoreAnalysis;
import com.zjjf.analysis.mapper.IMapper;

public interface SpGroupDailyReportMapper extends IMapper<StoreAnalysis> {

	List<SpGroupDailyReport> getReportData(HashMap<String, Object> map);

	Integer getReportDataCount(HashMap<String, Object> paramMap);

	List<SpGroupDailyReport> getReportDataExcel(HashMap<String, Object> map);

	SpGroupDailyReport getYesTodayData(HashMap<String, Object> map);

	SpGroupDailyReport getMonthData(HashMap<String, Object> paramMap);

	SpGroupDailyReport getDataSum(HashMap<String, Object> paramMap);
}