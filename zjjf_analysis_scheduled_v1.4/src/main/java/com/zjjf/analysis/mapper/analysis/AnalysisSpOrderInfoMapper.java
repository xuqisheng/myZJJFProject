package com.zjjf.analysis.mapper.analysis;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.analysis.store.StoreDaily;
import com.zjjf.analysis.beans.analysis.supplier.SpGroupDaily;
import com.zjjf.analysis.beans.analysis.supplier.SupplierDaily;

import java.util.HashMap;
import java.util.List;

public interface AnalysisSpOrderInfoMapper {

	void batchAdd(List<AnalysisSpOrderInfo> _sList);

	void insert(AnalysisSpOrderInfo bean);
	
	void updateBean(AnalysisSpOrderInfo bean);

	SupplierDaily getBySupplierIdSpGroupIdAndAddTime(HashMap<String, Object> paramMap);
	
	StoreDaily getByStoreIdAndAddTime(HashMap<String, Object> paramMap);
	
	Integer getOrderCount(HashMap<String, Object> paramMap);

	AnalysisSpOrderInfo getByChirdOrderNo(String orderNo);

	List<AnalysisSpOrderInfo> getSupplierIdList(HashMap<String, Object> paramMap);
	
	List<AnalysisSpOrderInfo> getStoreIdList(HashMap<String, Object> paramMap);

	List<AnalysisSpOrderInfo> getSpGroupIdIdList(HashMap<String, Object> paramMap);
	
	List<AnalysisSpOrderInfo> getSpGroupBySupplierId(HashMap<String, Object> paramMap);
	
	List<AnalysisSpOrderInfo> getSupplierSpGroupLog(HashMap<String, Object> paramMap);
	
	List<Integer> getSpGroupLogByDayTime(HashMap<String, Object> paramMap);
	
	SpGroupDaily getBySpGroupIdAndAddTime(HashMap<String, Object> paramMap);
	
	Integer getNewRegStoreOrdercount(HashMap<String, Object> paramMap);
	
	SpGroupDaily getMonthActiveBySpGroupId(HashMap<String, Object> paramMap);
	
	Integer getByStoreIdBeforeTime(HashMap<String, Object> paramMap);
}