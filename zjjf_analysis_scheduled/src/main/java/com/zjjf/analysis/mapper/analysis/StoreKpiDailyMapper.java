package com.zjjf.analysis.mapper.analysis;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.store.StoreKpiDaily;

public interface StoreKpiDailyMapper {

	int insert(StoreKpiDaily record);

	StoreKpiDaily getExitRecord(@Param("storeId") Integer storeId, @Param("dayTime") String dayTime, @Param("spGroupId") Integer spGroupId, @Param("isZj") Integer isZj);

	void updateKpiTurnover(@Param("id") Integer id, @Param("kpiTurnover") BigDecimal kpiTurnover);
}