package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.items.PlantItemDaily;
import com.zjjf.analysis.beans.analysis.items.PlantItemDailyVo;
import com.zjjf.analysis.mapper.IMapper;

public interface PlantItemDailyMapper extends IMapper<PlantItemDailyVo> {
	
	List<PlantItemDailyVo> getDataGraph(HashMap<String, Object> paramMap);
	
	List<PlantItemDaily> getByMap(HashMap<String, Object> paramMap);
}