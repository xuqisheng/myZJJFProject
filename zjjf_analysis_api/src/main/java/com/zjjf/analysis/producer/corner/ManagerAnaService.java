package com.zjjf.analysis.producer.corner;

import java.util.HashMap;
import java.util.List;

public interface ManagerAnaService {
	
	/**
	 * 供应商分析列表
	 * @param paramMap
	 * @return
	 */
	public List<Object[]> getList(String userName, Integer menuId, HashMap<String, Object> paramMap);
	
	public Integer getCount(String userName, Integer menuId, HashMap<String, Object> paramMap);
	
}
