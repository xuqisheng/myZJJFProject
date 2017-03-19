package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.origin.supplier.Supplier;

public interface SupplierMapper {
    
    List<Supplier> getTodayData(HashMap<String, Object> map);
    
    Supplier getById(String id);
    
    Supplier getBySupplierId(HashMap<String, Object> map);
    
    List<Supplier> getSupplier(HashMap<String, Object> paramMap);
}