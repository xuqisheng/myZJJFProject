package com.zjjf.analysis.beans.analysis.corner;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ManagerItemVo extends ErpManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//ERPManagerItem
    private String id;

    private String itemCode;

    private Integer serialNum;

    private Integer itemBaseId;

    private String supplierId;

    private String managerId;

    private BigDecimal areaPrice;

    private BigDecimal taxRate;

    private Date addTime;

    private Date updateTime;

    private Byte status;

    private Boolean isDelete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Integer getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public BigDecimal getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(BigDecimal areaPrice) {
		this.areaPrice = areaPrice;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
    
}