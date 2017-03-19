package com.zjjf.analysis.mapper.analysis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.saleman.SalemanDaily;
import com.zjjf.analysis.beans.analysis.store.StoreDaily;

public interface StoreDailyMapper {

	int insert(StoreDaily record);

	StoreDaily getExitRecord(@Param("storeId") Integer storeId, @Param("dayTime") String dayTime, @Param("spGroupId") Integer spGroupId, @Param("isZj") Integer isZj);

	void update_daily(StoreDaily v);
	
	SalemanDaily getSumStoresInfo(HashMap<String, Object> paramMap);
	
	List<StoreDaily> getStoreList(@Param("offset") Integer offset, @Param("dayTime") String dayTime);
	
	void updateTurnover(@Param("id") Integer id, @Param("turnover") BigDecimal turnover);
}