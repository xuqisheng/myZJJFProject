package com.corner.account.beans.ro;

import java.util.Date;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

public class CheckBillCondition extends EasyUIQueryModel {

	//for all supplier list
	private Integer areaId;
	private Date beginTime;
	private Date endTime;
	private String spKeyword;
	
	//for supplier detail list
	private String 	supplierId;
	private String 	acId;
	private Integer acStatus;
	
	//订单审核入参
	private String spOrderIds;
	private String[] spOrderIdArray;
	private String acRemark;
	
	
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

	public String getAcId() {
		return acId;
	}

	public void setAcId(String acId) {
		this.acId = acId;
	}

	public String getAcRemark() {
		return acRemark;
	}

	public void setAcRemark(String acRemark) {
		this.acRemark = acRemark;
	}

}
