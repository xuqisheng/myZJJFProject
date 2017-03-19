package com.zjjf.analysis.producer.store;

import java.util.HashMap;
import java.util.List;

public interface IGoalSetService {

	public List<Object[]> getList(String userName, Integer menuId, HashMap<String, Object> paramMap);
	
	public int insert(HashMap<String, Object> paramMap);
	
	public List<HashMap<String, Object>> getByMap(HashMap<String, Object> paramMap);
	
	public Integer getCount(HashMap<String, Object> paramMap);
}
