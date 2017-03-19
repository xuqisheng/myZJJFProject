package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.vo.orders.scorder.ScOrderVo;

public interface ScOrderMapper {

	List<ScOrderVo> getOrderData(HashMap<String, Object> paramMap);

	List<ScOrderVo> getExcelData(HashMap<String, Object> paramMap);

	Integer getTotalCount(HashMap<String, Object> paramMap);

	HashMap<String, Object> getOrderByOrderId(String orderId);

	ScOrderVo getOrderMummary(HashMap<String, Object> paramMap);
}