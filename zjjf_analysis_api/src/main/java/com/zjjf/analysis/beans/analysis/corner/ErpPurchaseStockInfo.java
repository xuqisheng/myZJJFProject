package com.zjjf.analysis.beans.analysis.corner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErpPurchaseStockInfo {
	private String id;

	private String pId;

	private String pOrderId;

	private String orderId;

	private BigDecimal itemPrice = new BigDecimal(0);

	private Short itemQuantity = 0;

	private String whId;

	private String whName;

	private String addUser;

	private String addUserName;

	private Date addTime;

	private Date taskTime;

	private String managerId;

	private String managerName;

	private String managerCode;

	private String supplierId;

	private String supplierName;

	private String checkUser;

	private String checkUserName;

	private Date checkTime;

	private Byte checkStatus;

	private Byte level;

	private String remark;

	private Byte settleStatus;

	private Byte status;

	private Boolean isDelete;

	private String settleStatusName;

	private String dayTimeBegin;

	private String dayTimeEnd;

	private List<String> orderIdList = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId == null ? null : pId.trim();
	}

	public String getpOrderId() {
		return pOrderId;
	}

	public void setpOrderId(String pOrderId) {
		this.pOrderId = pOrderId == null ? null : pOrderId.trim();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Short getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Short itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId == null ? null : whId.trim();
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName == null ? null : whName.trim();
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser == null ? null : addUser.trim();
	}

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName == null ? null : addUserName.trim();
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId == null ? null : managerId.trim();
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName == null ? null : managerName.trim();
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode == null ? null : managerCode.trim();
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId == null ? null : supplierId.trim();
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName == null ? null : supplierName.trim();
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser == null ? null : checkUser.trim();
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName == null ? null : checkUserName.trim();
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Byte getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Byte checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Byte getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(Byte settleStatus) {
		this.settleStatus = settleStatus;
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

	public String getDayTimeBegin() {
		return dayTimeBegin;
	}

	public void setDayTimeBegin(String dayTimeBegin) {
		this.dayTimeBegin = dayTimeBegin;
	}

	public String getDayTimeEnd() {
		return dayTimeEnd;
	}

	public void setDayTimeEnd(String dayTimeEnd) {
		this.dayTimeEnd = dayTimeEnd;
	}

	public List<String> getOrderIdList() {
		return orderIdList;
	}

	public void setOrderIdList(List<String> orderIdList) {
		this.orderIdList = orderIdList;
	}

	public String getSettleStatusName() {
		return settleStatusName;
	}

	public void setSettleStatusName(String settleStatusName) {
		this.settleStatusName = settleStatusName;
	}

}