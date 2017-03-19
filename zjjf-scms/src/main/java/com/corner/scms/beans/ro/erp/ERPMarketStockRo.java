package com.corner.scms.beans.ro.erp;

import com.corner.core.beans.ro.ABaseRo;

import java.io.Serializable;
import java.util.Date;

public class ERPMarketStockRo extends ABaseRo implements Serializable {
	private String[] ids;
	private String managerName;
	private Byte checkStatus;
	private Byte status;
	private Date beginTime;
	private Date endTime;
	private String supplierId;
	private String supplierName;
	private String whId;
	private String[] whIds;
	private Byte level;

	private String remark;
	private String[] remarks;
	private String id;
	private String orderId;
	private String pId;
	private String pOrderId;
	private String[] detailPIds;
	private Short[] quantitys;

	public String[] getWhIds() {
		return whIds;
	}

	public void setWhIds(String[] whIds) {
		this.whIds = whIds;
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

	public Short[] getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(Short[] quantitys) {
		this.quantitys = quantitys;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String[] getDetailPIds() {
		return detailPIds;
	}

	public void setDetailPIds(String[] detailPIds) {
		this.detailPIds = detailPIds;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getpOrderId() {
		return pOrderId;
	}

	public void setpOrderId(String pOrderId) {
		this.pOrderId = pOrderId;
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
