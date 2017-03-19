package com.zjjf.analysis.mapper.corner;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.erp.order.ErpManagerOrderInfo;

public interface ErpManagerOrderInfoMapper {

	ErpManagerOrderInfo getByOrderId(@Param("orderId") String orderId);

	List<ErpManagerOrderInfo> getByManagerIdSupplierIdTime(@Param("managerId") String managerId, @Param("supplierId") String supplierId,
			@Param("dayTimeBegin") String dayTimeBegin, @Param("dayTimeEnd") String dayTimeEnd);

}