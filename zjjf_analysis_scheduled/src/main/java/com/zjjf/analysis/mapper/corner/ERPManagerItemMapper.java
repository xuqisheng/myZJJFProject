package com.zjjf.analysis.mapper.corner;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.corner.ERPManagerItem;
import com.zjjf.analysis.beans.corner.ERPManagerItemVo;

public interface ERPManagerItemMapper {
	
	List<ERPManagerItemVo> getAll(HashMap<String, Object> paramMap);
	
	ERPManagerItemVo getRecentAreaPriceAndTaxRate(HashMap<String, Object> paramMap);
	//
    int insert(ERPManagerItem record);
    
    ERPManagerItem getByItemId(@Param("id") String id);

}