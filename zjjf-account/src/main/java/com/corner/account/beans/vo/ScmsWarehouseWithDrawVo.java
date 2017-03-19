package com.corner.account.beans.vo;

import com.corner.core.beans.ScmsWarehouse;

public class ScmsWarehouseWithDrawVo extends ScmsWarehouse{
	private static final long serialVersionUID = 1L;
	private Integer inCount;
    private Integer alreadyCount;
    private Integer denyCount;
    private Integer inPay;
    private Integer payCount;
    
	public Integer getInCount() {
		return inCount;
	}
	public void setInCount(Integer inCount) {
		this.inCount = inCount;
	}
	public Integer getAlreadyCount() {
		return alreadyCount;
	}
	public void setAlreadyCount(Integer alreadyCount) {
		this.alreadyCount = alreadyCount;
	}
	public Integer getDenyCount() {
		return denyCount;
	}
	public void setDenyCount(Integer denyCount) {
		this.denyCount = denyCount;
	}
	public Integer getPayCount() {
		return payCount;
	}
	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}
	public Integer getInPay() {
		return inPay;
	}
	public void setInPay(Integer inPay) {
		this.inPay = inPay;
	}
}
