package com.zjjf.analysis.producer.orders;

import java.util.HashMap;
import java.util.List;

public interface IScOrdersItemService {

	public List<Object[]> getOrderItemList(String userName, String orderId, Integer menuId, HashMap<String, Object> paramMap);

	public Integer getItemTotalCount(String userName, String orderId, Integer menuId, HashMap<String, Object> paramMap);

	public List<Object[]> getOrderItemListExcel(String userName, String orderId, Integer menuId, HashMap<String, Object> paramMap);
}
