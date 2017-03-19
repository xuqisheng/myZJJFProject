package com.zjjf.analysis.mapper.analysis;

import com.zjjf.analysis.beans.analysis.supplier.SpgroupDailyReport;
import org.apache.ibatis.annotations.Param;

public interface SpgroupDailyReportMapper {

	int insert(SpgroupDailyReport record);
	
	void update(SpgroupDailyReport record);

	SpgroupDailyReport getExitRecord(@Param("spGroupId") Integer spGroupId, @Param("dayTime") String dayTime);
}