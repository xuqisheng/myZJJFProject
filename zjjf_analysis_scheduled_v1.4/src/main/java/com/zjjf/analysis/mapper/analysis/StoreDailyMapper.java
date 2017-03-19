package com.zjjf.analysis.mapper.analysis;

import com.zjjf.analysis.beans.analysis.store.StoreDaily;

import java.util.HashMap;

public interface StoreDailyMapper {

	int insert(StoreDaily record);

	StoreDaily getExitRecord(HashMap<String, Object> map);

	void update_daily(StoreDaily v);
}