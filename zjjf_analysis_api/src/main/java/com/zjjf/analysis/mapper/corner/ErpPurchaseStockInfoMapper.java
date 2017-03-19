package com.zjjf.analysis.mapper.corner;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockInfo;

public interface ErpPurchaseStockInfoMapper {

	List<ErpPurchaseStockInfo> getByMap(HashMap<String, Object> paramMap);

	ErpPurchaseStockInfo getByManagerIdSupplierId(@Param("orderIdList") List<String> orderIdList, @Param("managerId") String managerId, @Param("supplierId") String supplierId);
	
	ErpPurchaseStockInfo getByOrderId(@Param("orderId") String orderId);
}