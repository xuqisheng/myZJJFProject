package com.zjjf.analysis.mapper.corner;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;

public interface ERPWarehouseMapper {

	List<AnaDictionary> getWhLevelOne(@Param("supplierList") List<String> supplierList);

}