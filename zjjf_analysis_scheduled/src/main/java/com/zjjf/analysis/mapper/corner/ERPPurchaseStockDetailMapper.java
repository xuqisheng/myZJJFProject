package com.zjjf.analysis.mapper.corner;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.corner.ERPPurchaseStockDetail;

public interface ERPPurchaseStockDetailMapper {
	
	List<ERPPurchaseStockDetail> getByMap(HashMap<String, Object> paramMap);
	//
	
    int deleteByPrimaryKey(String id);

    int insert(ERPPurchaseStockDetail record);

    int insertSelective(ERPPurchaseStockDetail record);

    ERPPurchaseStockDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPPurchaseStockDetail record);

    int updateByPrimaryKey(ERPPurchaseStockDetail record);
}