package com.zjjf.analysis.producer.ajie;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.producer.base.ITimeData;

public interface ISpOrderDetailService<T> extends ITimeData<T>{
	
	public List<HashMap<String, Object>> getOrderDetail(String orderNo);
}
