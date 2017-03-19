package com.zjjf.analysis.mapper.corner;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.corner.ERPManagerOrderDetail;

public interface ERPManagerOrderDetailMapper {
	
	List<ERPManagerOrderDetail> get(HashMap<String, Object> paramMap);
	
	List<ERPManagerOrderDetail> getAll(@Param("dayTime") String dayTime, @Param("offset") Integer offset);

    List<ERPManagerOrderDetail> getByOrderInfo(@Param("managerId") String managerId, @Param("orderId") String orderId, @Param("itemBaseId") Integer itemBaseId, @Param("itemId") String itemId);
}