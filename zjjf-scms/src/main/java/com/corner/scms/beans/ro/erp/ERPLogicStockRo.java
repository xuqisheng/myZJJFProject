package com.corner.scms.beans.ro.erp;

import java.io.Serializable;

import com.corner.core.beans.ro.ABaseRo;
import com.corner.core.utils.StringUtil;

public class ERPLogicStockRo extends ABaseRo implements Serializable {
	private String id = StringUtil.getUUID();

    private String supplierId;

    private String warehouseId;

    private Integer itemBaseId;

    private String skuId;

    private Byte typeMg;

    private Byte typeSale;
	    
	private Integer beginStock;
	
	private Integer endStock;
	
	private String nameAndMdseId;

	private String managerId;

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

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

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Byte getTypeMg() {
		return typeMg;
	}

	public void setTypeMg(Byte typeMg) {
		this.typeMg = typeMg;
	}

	public Byte getTypeSale() {
		return typeSale;
	}

	public void setTypeSale(Byte typeSale) {
		this.typeSale = typeSale;
	}

	public Integer getBeginStock() {
		return beginStock;
	}

	public void setBeginStock(Integer beginStock) {
		this.beginStock = beginStock;
	}

	public Integer getEndStock() {
		return endStock;
	}

	public void setEndStock(Integer endStock) {
		this.endStock = endStock;
	}

	public String getNameAndMdseId() {
		return nameAndMdseId;
	}

	public void setNameAndMdseId(String nameAndMdseId) {
		this.nameAndMdseId = nameAndMdseId;
	}

}
