package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.store.StoreSpgroupSaleGoal;

public interface StoreSpgroupSaleGoalMapper {

	StoreSpgroupSaleGoal getGoal(HashMap<String, Object> map);
	
	List<StoreSpgroupSaleGoal> query(HashMap<String, Object> paramMap);
	
	Integer batchUpdate(HashMap<String, Object> paramMap);
}