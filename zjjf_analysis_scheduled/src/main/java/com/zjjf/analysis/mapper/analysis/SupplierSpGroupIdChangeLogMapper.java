package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.supplier.SupplierSpGroupIdChangeLog;

public interface SupplierSpGroupIdChangeLogMapper {

    int insert(SupplierSpGroupIdChangeLog record);
    
    List<SupplierSpGroupIdChangeLog> queryByIndex(Integer id);
    
    int updateDayTime(HashMap<String, Object> paramMap);
    
    List<SupplierSpGroupIdChangeLog> query(HashMap<String, Object> paramMap);
    
	List<SupplierSpGroupIdChangeLog> getAll(@Param("dayTime") String dayTime, @Param("offset") Integer offset);
}