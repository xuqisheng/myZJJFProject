package com.corner.scms.beans.ro;

import java.util.Date;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

public class SpOrderMgCondition extends EasyUIQueryModel {

	private String spOrderIds;
	private String[] spOrderIdArray;
	private String id;
    private String orderId;
    private Byte status;
    private Boolean isDelete;
    private String acId;
    private Integer acStatus;
    private Integer kfStatus;
    private Integer spStatus;
    private String acRemark;
    private Date acCheckTime;
    private Date acSettleTime;
    private Date acPayTime;
   
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getAcId() {
		return acId;
	}

	public void setAcId(String acId) {
		this.acId = acId;
	}

	public Integer getAcStatus() {
		return acStatus;
	}

	public void setAcStatus(Integer acStatus) {
		this.acStatus = acStatus;
	}

	public String getAcRemark() {
		return acRemark;
	}

	public void setAcRemark(String acRemark) {
		this.acRemark = acRemark;
	}

	public Date getAcCheckTime() {
		return acCheckTime;
	}

	public void setAcCheckTime(Date acCheckTime) {
		this.acCheckTime = acCheckTime;
	}

	public Date getAcSettleTime() {
		return acSettleTime;
	}

	public void setAcSettleTime(Date acSettleTime) {
		this.acSettleTime = acSettleTime;
	}

	public Date getAcPayTime() {
		return acPayTime;
	}

	public void setAcPayTime(Date acPayTime) {
		this.acPayTime = acPayTime;
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

	public Integer getKfStatus() {
		return kfStatus;
	}

	public void setKfStatus(Integer kfStatus) {
		this.kfStatus = kfStatus;
	}

	public Integer getSpStatus() {
		return spStatus;
	}

	public void setSpStatus(Integer spStatus) {
		this.spStatus = spStatus;
	}
}
