package com.corner.scms.beans.ro.erp;

import java.io.Serializable;

import com.corner.core.utils.StringUtil;

public class ERPPhysicsStockRo implements Serializable {
	private String id = StringUtil.getUUID();
 
	private String supplierId;

	private String warehouseId;

	private Integer itemBaseId;
	
	private Byte typeMg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Byte getTypeMg() {
		return typeMg;
	}

	public void setTypeMg(Byte typeMg) {
		this.typeMg = typeMg;
	}

}
