package com.zjjf.analysis.mapper.origin;

import java.util.List;

import com.zjjf.analysis.beans.analysis.supplier.Supplier;

public interface SupplierMapper {

	Supplier getById(String id);
	
	List<Supplier> getByName(String name);
}