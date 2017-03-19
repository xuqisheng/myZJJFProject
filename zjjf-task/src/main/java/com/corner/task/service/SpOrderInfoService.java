package com.corner.task.service;

import java.util.List;
import java.util.Map;

import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.beans.ro.OrderRo;



public interface SpOrderInfoService {
	/**
	 * 订单清理
	 * @param taskParams
	 * @return
     */
	ModelMsg clearOrder(String taskParams);

	List<Map<String, Object>> selectSpOrderSales(OrderRo orderRo);
}
