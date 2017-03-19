package com.zjjf.analysis.producer.graph;

import java.util.HashMap;
import java.util.Map;

public interface IGraphService {

	public Map<String,Object> getGmv(HashMap<String, Object> paramMap, String userName, Integer menuId, Integer preNumDay);
	
	public Map<String, Object> getStoreMaintain(HashMap<String, Object> paramMap, Integer preNumDay);
	
	public Map<String, Object> getGoodsAna(HashMap<String, Object> paramMap, Integer top, Integer order_by_column);
	
	public Map<String, Object> getActiveStoreByOrderCountAndTurnover(String userName, Integer menuId, HashMap<String, Object> paramMap);
	
	public Map<String, Object> getErpManagerAnalysis(HashMap<String, Object> paramMap);
}
