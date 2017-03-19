package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.origin.orders.SpOrderDetail;
import com.zjjf.analysis.beans.origin.orders.SpOrderDetailVo;

public interface SpOrderDetailMapper {

	List<SpOrderDetail> getTodayData(HashMap<String, Object> paramMap);
	
	SpOrderDetail getDetaiGatherByOrderId(String orderId);
	
	SpOrderDetailVo getTurnOverByItemId(HashMap<String, Object> paramMap);
	
	List<SpOrderDetailVo> getPlantItemList(HashMap<String, Object> paramMap);
	
	SpOrderDetail getByOrderId2(String chirdOrderNo);
}