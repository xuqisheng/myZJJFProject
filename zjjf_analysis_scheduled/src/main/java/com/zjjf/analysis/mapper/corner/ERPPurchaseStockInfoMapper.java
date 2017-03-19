package com.zjjf.analysis.mapper.corner;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.corner.ERPPurchaseStockInfo;

public interface ERPPurchaseStockInfoMapper {
	
	List<ERPPurchaseStockInfo> getByMap(HashMap<String, Object> paramMap);
}