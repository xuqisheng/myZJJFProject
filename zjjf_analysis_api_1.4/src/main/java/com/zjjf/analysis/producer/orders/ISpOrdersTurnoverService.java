package com.zjjf.analysis.producer.orders;

import java.util.HashMap;
import java.util.List;

public interface ISpOrdersTurnoverService {

	public List<Object[]> getOrderData(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public Integer getTotalCount(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public List<Object[]> getExcelData(String userName, Integer menuId, HashMap<String, Object> paramMap);
	
	public HashMap<String, Object> getMonthMummary(HashMap<String, Object> paramMap, String userName, Integer menuId);
	
	public HashMap<String, Object> getDailyMummary(HashMap<String, Object> paramMap, String userName, Integer menuId);
}
