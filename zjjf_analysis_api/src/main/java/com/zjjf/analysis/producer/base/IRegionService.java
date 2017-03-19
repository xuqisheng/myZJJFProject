package com.zjjf.analysis.producer.base;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;

public interface IRegionService {
	
	public HashMap<String, Object> getAreaByCityId(Integer cityId, Integer roleUserId);
	
	List<AnaDictionary> getCityByareaId(HashMap<String, Object> paramMap);
	
    //获取城市列表代码
    List<AnaDictionary> getRegionList(HashMap<String, Object> cityMap);
}
