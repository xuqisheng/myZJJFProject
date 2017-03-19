package com.corner.scms.service.erp;

import java.util.List;

import com.corner.core.beans.ERPPlantItemLog;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.ERPLogicStockRo;
import com.corner.scms.beans.ro.erp.ERPPhysicsStockLogRo;
import com.corner.scms.beans.ro.erp.ERPPhysicsStockRo;
import com.corner.scms.beans.vo.erp.ERPLogicStockVo;
import com.corner.scms.beans.vo.erp.ERPPhysicsStockLogVo;
import com.corner.scms.beans.vo.erp.ERPPhysicsStockVo;


public interface ERPStockManagerService {

	public Pager<ERPLogicStockVo> getSupplierStock(ERPLogicStockRo logicStock);

	public List<ERPPhysicsStockVo> getSupplierStockDetail(ERPPhysicsStockRo physicsStock);

	public Pager<ERPPhysicsStockLogVo> getPhysicsStockLog(ERPPhysicsStockLogRo physicsStockLog);

	void updateStock(List<ERPPlantItemLog> erpPlantItemLoglist,Supplier supplier) throws Exception;
	
}
