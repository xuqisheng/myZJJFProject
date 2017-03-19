package com.zjjf.analysis.producer.ajie;

import com.zjjf.analysis.producer.base.ITimeData;

import java.util.HashMap;
import java.util.List;

public interface ISpOrderDetailService<T> extends ITimeData<T>{
	
	public List<HashMap<String, Object>> getOrderDetail(String orderNo);
}
