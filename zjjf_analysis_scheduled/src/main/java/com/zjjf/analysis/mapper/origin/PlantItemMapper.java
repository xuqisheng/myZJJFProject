package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.origin.items.PlantItem;

public interface PlantItemMapper {

	List<PlantItem> getTodayData(HashMap<String, Object> paramMap);
	
	PlantItem getById(String id);
	
	PlantItem getDipolarPrice(HashMap<String, Object> paramMap);
	
	List<PlantItem> getDataList(HashMap<String, Object> paramMap);
	
	PlantItem getItemInfo(HashMap<String, Object> paramMap);
}