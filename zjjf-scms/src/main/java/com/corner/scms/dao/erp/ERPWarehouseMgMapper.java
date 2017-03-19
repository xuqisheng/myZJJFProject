package com.corner.scms.dao.erp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ERPPhysicsStock;
import com.corner.core.beans.ERPWarehouse;
import com.corner.scms.beans.ro.erp.ERPWarehouseRo;
import com.corner.scms.beans.vo.erp.ERPWarehouseVo;

public interface ERPWarehouseMgMapper {
	
	List<ERPWarehouseVo> getWarehouseBySupplierId(ERPWarehouseRo warehouseRo);

	int getWarehouseBySupplierIdCount(ERPWarehouseRo warehouseRo);

	List<ERPWarehouseVo> getWhareaBySupplierId(ERPWarehouseRo warehouseRo);

	int getWhareaBySupplierIdCount(ERPWarehouseRo warehouseRo);

	List<ERPWarehouseVo> getWhpositionBySupplierId(ERPWarehouseRo warehouseRo);

	int getWhpositionBySupplierIdCount(ERPWarehouseRo warehouseRo);
	
	List<ERPWarehouseVo> getWarehouseByUpId(Map<String, Object> map);

	String getMaxCode(Byte whLevel);

	ERPWarehouseVo getWarehouseById(Map<String, Object> map);

	int delWarehouse(Map<String, Object> map);

	List<ERPPhysicsStock> getPhysicsStockByUpId(Map<String, Object> map);

	List<ERPWarehouse> getWarehouseLevel3(String whId);

	List<ERPWarehouseVo> getWarehouseLevel3ByLevel1Id(String wareHouseId);
}
