package com.zjjf.analysis.producer.orders;

import java.util.HashMap;
import java.util.List;

public interface IScOrdersService {

	public List<Object[]> getOrderData(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public Integer getTotalCount(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public List<Object[]> getExcelData(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public HashMap<String, Object> getOrderByOrderId(String orderId);
	
	public HashMap<String, Object> getOrderMummary(String userName, Integer menuId, HashMap<String, Object> paramMap);
}
