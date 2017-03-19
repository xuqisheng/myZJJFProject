package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.vo.supplier.SupplierFreeVo;

public interface SupplierDailyFinancialMapper {

	List<SupplierFreeVo> getFinancialData(HashMap<String, Object> paramMap);

	Integer getFinancialDataCount(HashMap<String, Object> paramMap);

	List<SupplierFreeVo> getFinancialExcelData(HashMap<String, Object> paramMap);

	SupplierFreeVo getDataBySupplierIdAndTime(HashMap<String, Object> paramMap);

}