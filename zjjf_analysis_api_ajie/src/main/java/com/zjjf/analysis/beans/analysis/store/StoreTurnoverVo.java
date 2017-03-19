package com.zjjf.analysis.beans.analysis.store;

import java.math.BigDecimal;

public class StoreTurnoverVo extends StoreTurnover {

	private BigDecimal orderPrice = new BigDecimal(0);
	
	private BigDecimal itemPrice = new BigDecimal(0);

	private String orderNo;

	private String mobile;

	private Integer status;

	private String addTime;

	private String storeName;
	
	private String dayTimeStr;
	
	public String getDayTimeStr() {
		return dayTimeStr;
	}

	public void setDayTimeStr(String dayTimeStr) {
		this.dayTimeStr = dayTimeStr;
	}
	
	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}