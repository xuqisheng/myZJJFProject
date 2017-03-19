package com.zjjf.analysis.producer.ajie;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.producer.base.ITimeData;

public interface IStoreTurnoverService <T> extends ITimeData<T>  {

	public Integer updateIsVisitByStoreId(Integer dayTIme, String storeCode, Integer isVisit);
	
	public List<HashMap<String, Object>> getTurnoverBySpGoupAndStore(HashMap<String, Object> map);
}
