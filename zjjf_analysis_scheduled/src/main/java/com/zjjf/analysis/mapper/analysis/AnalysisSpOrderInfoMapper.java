package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.analysis.store.StoreDaily;
import com.zjjf.analysis.beans.analysis.store.StoreKpiDaily;
import com.zjjf.analysis.beans.analysis.supplier.SpGroupDaily;
import com.zjjf.analysis.beans.analysis.supplier.SupplierDaily;

public interface AnalysisSpOrderInfoMapper {

	void batchAdd(List<AnalysisSpOrderInfo> _sList);

	void insert(AnalysisSpOrderInfo bean);
	
	void updateBean(AnalysisSpOrderInfo bean);

	SupplierDaily getBySupplierIdSpGroupIdAndAddTime(@Param("spGroupId") Integer spGroupId, @Param("supplierId") String supplierId, @Param("dayTime") String dayTime);
	
	SupplierDaily getBySupplierIdKpi(@Param("supplierId") String supplierId, @Param("dayTime") String dayTime, @Param("spGroupId") Integer spGroupId);
	
	StoreDaily getValidTurnover(@Param("storeId") Integer storeId, @Param("dayTime") String dayTime, @Param("spGroupId") Integer spGroupId, @Param("isZj") Integer isZj);
	
	StoreKpiDaily getByStoreKpi(@Param("storeId") Integer storeId, @Param("dayTime") String dayTime, @Param("spGroupId") Integer spGroupId, @Param("isZj") Integer isZj);
	
	Integer getOrderCount(@Param("spGroupId") Integer spGroupId, @Param("supplierId") String supplierId, @Param("dayTime") String dayTime);

	AnalysisSpOrderInfo getByChirdOrderNo(String orderNo);

	List<AnalysisSpOrderInfo> getSupplierIdList(@Param("offset") Integer offset, @Param("dayTime") String dayTime);
	
	List<AnalysisSpOrderInfo> getStoreIdKpiList(@Param("offset") Integer offset, @Param("dayTime") String dayTime);
	
	List<AnalysisSpOrderInfo> getStoreIdList(@Param("offset") Integer offset, @Param("dayTime") String dayTime);

	List<AnalysisSpOrderInfo> getSpGroupIdIdList(HashMap<String, Object> paramMap);
	
	List<AnalysisSpOrderInfo> getSpGroupBySupplierId(HashMap<String, Object> paramMap);
	
	List<AnalysisSpOrderInfo> getSupplierSpGroupLog(HashMap<String, Object> paramMap);
	
	List<Integer> getSpGroupLogByDayTime(HashMap<String, Object> paramMap);
	
	SpGroupDaily getBySpGroupIdAndAddTime(@Param("spGroupId") Integer spGroupId, @Param("dayTime") String dayTime);
	
	Integer getNewRegStoreOrdercount(HashMap<String, Object> paramMap);
	
	SpGroupDaily getMonthActiveBySpGroupId(HashMap<String, Object> paramMap);
	
	Integer getByStoreIdBeforeTime(HashMap<String, Object> paramMap);
	
	List<AnalysisSpOrderInfo> getStatusList(@Param("offset") Integer offset, @Param("dayTime") String dayTime);
	
	List<AnalysisSpOrderInfo> getAllStatusList(@Param("offset") Integer offset, @Param("dayTime") String dayTime);
	
	void updateStatus(@Param("id") Integer id, @Param("printTime") String printTime, @Param("status") Integer status);
	
	void updateKpiUsed(@Param("storeId") Integer storeId, @Param("printTime") String printTime, @Param("spGroupId") Integer spGroupId, @Param("isZj") Integer isZj);
}