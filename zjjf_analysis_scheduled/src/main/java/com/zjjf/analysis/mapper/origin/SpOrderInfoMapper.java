package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.origin.orders.SpOrderInfo;

public interface SpOrderInfoMapper {

	List<AnalysisSpOrderInfo> getSpSupportDataToday(HashMap<String, Object> paramMap);

	SpOrderInfo getParentOrderNoByPid(String pid);

	List<AnalysisSpOrderInfo> getSpSupportDataBefore(HashMap<String, Object> paramMap);

	SpOrderInfo getByOrderId(@Param("orderId") String orderId);
}