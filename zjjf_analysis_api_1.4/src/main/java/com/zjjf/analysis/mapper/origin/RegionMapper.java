package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.Region;

public interface RegionMapper {
	
    //获取城市列表代码
    List<AnaDictionary> getRegionCodeList(HashMap<String, Object> cityMap);
    
    //获取城市列表代码
    List<AnaDictionary> getAreaByCityId(HashMap<String, Object> cityMap);
    
    List<AnaDictionary> getAreaListByareaId(HashMap<String, Object> cityMap);
    
    List<AnaDictionary> getCityByareaId(HashMap<String, Object> cityMap);
    
    Region getById(Integer id);
}