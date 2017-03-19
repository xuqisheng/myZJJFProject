package com.corner.scms.dao.erp;

import java.util.List;
import java.util.Map;

import com.corner.scms.beans.ro.erp.ERPLogicStockRo;
import com.corner.scms.beans.ro.erp.ERPPhysicsStockLogRo;
import com.corner.scms.beans.ro.erp.ERPPhysicsStockRo;
import com.corner.scms.beans.vo.erp.ERPLogicStockVo;
import com.corner.scms.beans.vo.erp.ERPPhysicsStockLogVo;
import com.corner.scms.beans.vo.erp.ERPPhysicsStockVo;

public interface ERPStockManagerMapper {


	List<ERPLogicStockVo> getSupplierStock(ERPLogicStockRo logicStock);
	int getSupplierStockCount(ERPLogicStockRo logicStock);
	
	String getlogicStockId(ERPPhysicsStockRo physicsStock);
	List<ERPPhysicsStockVo> getSupplierStockDetail(ERPPhysicsStockRo physicsStock);
	
	List<ERPPhysicsStockLogVo> getPhysicsStockLog(ERPPhysicsStockLogRo physicsStockLog);
	int getPhysicsStockLogCount(ERPPhysicsStockLogRo physicsStockLog);
	String executionStockProcedure(Map<String, Object> map);

}
