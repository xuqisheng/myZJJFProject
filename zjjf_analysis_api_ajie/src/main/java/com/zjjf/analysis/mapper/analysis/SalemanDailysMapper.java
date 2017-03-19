package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SalemanDailysMapper {
	
	List<Map<String, Object>> getTopList(HashMap<String, Object> paramMap);
	
	List<Map<String, Object>> getLastTopList(HashMap<String, Object> paramMap);
	
	List<Map<String, Object>> getDailyList(HashMap<String, Object> paramMap);
	
	Map<String, Object> getSummrization(HashMap<String, Object> paramMap);
	
}