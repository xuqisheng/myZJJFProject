package com.zjjf.analysis.beans.vo.orders.scorder;

import java.io.Serializable;

public class ScOrderVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String authkey;
	private String cityName;
	private String areaName;
	private String addTime;
	private String scSupportmetho;
	private String orderNo;
	private String orderPrice;
	private String sku;
	private String increase;
	private String quantity;
	private String scStatus;
	private String supplierName;
	private String mobile;
	private String supplierAddress;
	private String managerName;
	private String ackTime;

	private String orderPriceSum;

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getScSupportmetho() {
		return scSupportmetho;
	}

	public void setScSupportmetho(String scSupportmetho) {
		this.scSupportmetho = scSupportmetho;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getScStatus() {
		return scStatus;
	}

	public void setScStatus(String scStatus) {
		this.scStatus = scStatus;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIncrease() {
		return increase;
	}

	public void setIncrease(String increase) {
		this.increase = increase;
	}

	public String getOrderPriceSum() {
		return orderPriceSum;
	}

	public void setOrderPriceSum(String orderPriceSum) {
		this.orderPriceSum = orderPriceSum;
	}

	public String getAckTime() {
		return ackTime;
	}

	public void setAckTime(String ackTime) {
		this.ackTime = ackTime;
	}

}
