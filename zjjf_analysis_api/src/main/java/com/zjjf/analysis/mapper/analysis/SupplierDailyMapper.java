package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;

import com.zjjf.analysis.beans.analysis.supplier.SaleDaily;
import com.zjjf.analysis.beans.analysis.supplier.SupplierDaily;
import com.zjjf.analysis.mapper.IMapper;

public interface SupplierDailyMapper extends IMapper<SaleDaily> {

	SupplierDaily getSupplierData(Integer id);
	
	SaleDaily getYesTodayData(HashMap<String, Object> paramMap);
	
	SaleDaily getMonthData(HashMap<String, Object> paramMap);
}