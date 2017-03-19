package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;

import com.zjjf.analysis.beans.analysis.items.PlantItemDaily;

public interface PlantItemDailyMapper {

	int insert(PlantItemDaily record);

	PlantItemDaily getExitRecord(HashMap<String, Object> map);

	void update_daily(PlantItemDaily v);
}