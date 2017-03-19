package com.corner.account.beans.ro;

import java.util.Date;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

public class BillPayCondition extends EasyUIQueryModel {
	
	//type for page
	private String type;

	//for all supplier list
	private Integer areaId;
	private Date beginTime;
	private Date endTime;
	private String spKeyword;
	
	//for supplier detail list
	private String supplierId;
	private Integer acStatus;
	private Integer spStatus;
	private Integer kfStatus;
	private String sheetNum;
	private String payBankNum;
	
	//订单审核入参
	private String spOrderIds;
	private String[] spOrderIdArray;
	private String userRemark;
	
	//创建结算单入参
	//private String spOrderIds;
	//private String[] spOrderIdArray;
	
	@Override
	public String toHqlString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toHqlObjects() {
		// TODO Auto-generated method stub
		return null;
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

	public String getSpKeyword() {
		return spKeyword;
	}

	public void setSpKeyword(String spKeyword) {
		this.spKeyword = spKeyword;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSpOrderIds() {
		return spOrderIds;
	}

	public void setSpOrderIds(String spOrderIds) {
		this.spOrderIds = spOrderIds;
	}
	
	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String[] getSpOrderIdArray() {
		return spOrderIdArray;
	}

	public void setSpOrderIdArray(String[] spOrderIdArray) {
		this.spOrderIdArray = spOrderIdArray;
	}

	public Integer getAcStatus() {
		return acStatus;
	}

	public void setAcStatus(Integer acStatus) {
		this.acStatus = acStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSpStatus() {
		return spStatus;
	}

	public void setSpStatus(Integer spStatus) {
		this.spStatus = spStatus;
	}

	public Integer getKfStatus() {
		return kfStatus;
	}

	public void setKfStatus(Integer kfStatus) {
		this.kfStatus = kfStatus;
	}

	public String getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(String sheetNum) {
		this.sheetNum = sheetNum;
	}

	public String getPayBankNum() {
		return payBankNum;
	}

	public void setPayBankNum(String payBankNum) {
		this.payBankNum = payBankNum;
	}
}
