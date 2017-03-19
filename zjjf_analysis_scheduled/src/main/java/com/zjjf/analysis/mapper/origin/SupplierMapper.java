package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.origin.supplier.Supplier;

public interface SupplierMapper {
    
    List<Supplier> getTodayData(HashMap<String, Object> map);
    
    Supplier getById(String id);
    
    Supplier getBySupplierId(@Param("supplierId") String supplierId);
    
    List<Supplier> getSupplier(HashMap<String, Object> paramMap);
}