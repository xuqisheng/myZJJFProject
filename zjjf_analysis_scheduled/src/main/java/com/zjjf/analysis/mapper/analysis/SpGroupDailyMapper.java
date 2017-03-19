package com.zjjf.analysis.mapper.analysis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.supplier.SpGroupDaily;

public interface SpGroupDailyMapper {

	int insert(SpGroupDaily record);
	
	SpGroupDaily getExitRecord(HashMap<String, Object> paramMap);
	
	void update_daily(SpGroupDaily v);
	
	Integer getBySpGroupIdAndTime(HashMap<String, Object> paramMap);
	
	SpGroupDaily getBySpGroupIdAndDayTime(@Param("spGroupId") Integer spGroupId, @Param("dayTime") String dayTime);
	
	SpGroupDaily getDataSum(HashMap<String, Object> paramMap);
	
	SpGroupDaily getYesTodayData(@Param("dayTime") String dayTime, @Param("spGroupId") Integer spGroupId);
	
	List<SpGroupDaily> getSpGroupList(@Param("offset") Integer offset, @Param("dayTime") String dayTime);
	
	void updateTurnover( @Param("id") Integer id, @Param("turnover") BigDecimal turnover);
}