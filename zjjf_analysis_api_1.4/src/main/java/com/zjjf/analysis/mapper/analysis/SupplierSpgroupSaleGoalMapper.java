package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjjf.analysis.beans.analysis.supplier.SupplierSpgroupSaleGoal;

public interface SupplierSpgroupSaleGoalMapper {

	SupplierSpgroupSaleGoal getGoal(HashMap<String, Object> map);
	
	public List<SupplierSpgroupSaleGoal> querySaleGoalSet(Map<String, Object> reqMap);
	
	Integer batchUpdate(HashMap<String, Object> hashMap);
}