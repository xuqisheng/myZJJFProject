package com.zjjf.analysis.mapper.corner;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.corner.ERPManagerOrderInfo;

public interface ERPManagerOrderInfoMapper {
	
	List<ERPManagerOrderInfo> getByMap(HashMap<String, Object> paramMap);
	//
    int deleteByPrimaryKey(String id);

    int insert(ERPManagerOrderInfo record);

    int insertSelective(ERPManagerOrderInfo record);

    ERPManagerOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPManagerOrderInfo record);

    int updateByPrimaryKey(ERPManagerOrderInfo record);
    
    List<ERPManagerOrderInfo> getByManagerIdAndSupplierId(@Param("managerId") String managerId, @Param("supplierId") String supplierId);
    
    ERPManagerOrderInfo getByOrderId(@Param("orderId") String orderId);
}