package com.zjjf.analysis.mapper.analysis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.base.SupplierFreePage;
import com.zjjf.analysis.beans.analysis.supplier.SupplierDaily;

public interface SupplierDailyMapper {

	int insert(SupplierDaily record);

	List<SupplierDaily> getDataByTime(HashMap<String, Object> paramMap);
	
	public SupplierDaily getExitRecord(@Param("spGroupId") Integer spGroupId, @Param("supplierId") String supplierId, @Param("dayTime") String dayTime);

	public SupplierDaily getBeforeDay(HashMap<String, Object> paramMap);

	public SupplierDaily getTurnoverByThisMonth(HashMap<String, Object> paramMap);
	
	public SupplierDaily getTurnoverByLastMonth(HashMap<String, Object> paramMap);
	
	public SupplierFreePage getThisMonthTurnover(HashMap<String, Object> paramMap);
	
	void updateBean(SupplierDaily record);
	
	SupplierDaily getMonthData(@Param("spGroupId") Integer spGroupId, @Param("supplierId") String supplierId, @Param("dayTimeBegin") String dayTimeBegin, @Param("dayTimeEnd") String dayTimeEnd);
	
	SupplierDaily getBySpGroupId_supplierId_dayTime(@Param("spGroupId") Integer spGroupId, @Param("supplierId") String supplierId, @Param("dayTime") String dayTime);
	
	List<SupplierDaily> getSupplierList(@Param("offset") Integer offset, @Param("dayTime") String dayTime);
	
	void updateTurnover(@Param("id") Integer id, @Param("turnover") BigDecimal turnover);
}