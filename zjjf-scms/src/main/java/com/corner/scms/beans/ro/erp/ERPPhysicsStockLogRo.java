package com.corner.scms.beans.ro.erp;

import java.io.Serializable;
import java.util.Date;

import com.corner.core.beans.ro.ABaseRo;

public class ERPPhysicsStockLogRo extends ABaseRo implements Serializable {
	private Date beginTime;
	
	private Date endTime;
	
	private Byte operationType;//操作类型
	
	private String supplierId;

	private String warehouseId;

	private Integer itemBaseId;

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Byte getOperationType() {
		return operationType;
	}

	public void setOperationType(Byte operationType) {
		this.operationType = operationType;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}
}
