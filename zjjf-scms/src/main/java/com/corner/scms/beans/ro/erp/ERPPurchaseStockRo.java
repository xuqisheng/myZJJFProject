package com.corner.scms.beans.ro.erp;

import com.corner.core.beans.ro.ABaseRo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ERPPurchaseStockRo extends ABaseRo implements Serializable {
	private String id;
	private String[] ids;
	private String managerId;
	private String managerName;
	private Byte checkStatus;
	private Byte status;
	private Date beginTime;
	private Date endTime;
	private String supplierId;
	private String supplierName;
	private Byte level;	//1、采购入库单	2、采购退货单
	private Date taskTime;	//业务发生时间
	private Short[] quantitys;	//购买数量
	private String remark;	//单子备注
	private String[] remarks;	//商品备注
	private Integer[] itemBaseIds;	//商品编号
	private String whId;	//仓库ID
	private String[] whIds;	//库位ID	不传取默认
	private String[] itemCodes;
	private String[] itemIds;
	private BigDecimal[] areaPrices;

	public String[] getItemIds() {
		return itemIds;
	}

	public void setItemIds(String[] itemIds) {
		this.itemIds = itemIds;
	}

	public String[] getItemCodes() {
		return itemCodes;
	}

	public void setItemCodes(String[] itemCodes) {
		this.itemCodes = itemCodes;
	}

	public BigDecimal[] getAreaPrices() {
		return areaPrices;
	}

	public void setAreaPrices(BigDecimal[] areaPrices) {
		this.areaPrices = areaPrices;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public Date getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}

	public Short[] getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(Short[] quantitys) {
		this.quantitys = quantitys;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String[] getRemarks() {
		return remarks;
	}

	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}

	public Integer[] getItemBaseIds() {
		return itemBaseIds;
	}

	public void setItemBaseIds(Integer[] itemBaseIds) {
		this.itemBaseIds = itemBaseIds;
	}

	public String[] getWhIds() {
		return whIds;
	}

	public void setWhIds(String[] whIds) {
		this.whIds = whIds;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName == null ? null : managerName.trim();
	}

	public Byte getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Byte checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

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
}
