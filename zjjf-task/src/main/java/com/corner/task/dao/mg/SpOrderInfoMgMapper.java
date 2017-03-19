package com.corner.task.dao.mg;

import java.util.List;
import java.util.Map;

import com.corner.task.beans.SpOrderInfo;
import com.corner.task.beans.ro.OrderRo;

public interface SpOrderInfoMgMapper { 
	@SuppressWarnings("rawtypes")
	public List<SpOrderInfo> getAllNoPayOrderInfoList(Map map);
	
	public List<Map<String, Object>> selectSpOrderSales(OrderRo orderRo);
}