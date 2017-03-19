package com.corner.scms.beans.vo.erp;

import com.corner.core.beans.ERPManagerOrderDetail;
import com.corner.core.beans.ERPManagerOrderInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ERPOrderInfoVo extends ERPManagerOrderInfo implements Serializable {
	private List<ERPManagerOrderDetail> details;
	private String managerName;
	private String managerCode;
	private String supplierName;
	private Integer total1;
	private Integer total2;
	private String whName;
	private String whAddress;
	private String whMobile;
	private Byte cooperation;
	private String actionUserName;
	private BigDecimal totalPrice;

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Byte getCooperation() {
		return cooperation;
	}

	public void setCooperation(Byte cooperation) {
		this.cooperation = cooperation;
	}

	public String getWhMobile() {
		return whMobile;
	}

	public void setWhMobile(String whMobile) {
		this.whMobile = whMobile;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getWhAddress() {
		return whAddress;
	}

	public void setWhAddress(String whAddress) {
		this.whAddress = whAddress;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getTotal1() {
		return total1;
	}

	public void setTotal1(Integer total1) {
		this.total1 = total1;
	}

	public Integer getTotal2() {
		return total2;
	}

	public void setTotal2(Integer total2) {
		this.total2 = total2;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getActionUserName() {
		return actionUserName;
	}

	public void setActionUserName(String actionUserName) {
		this.actionUserName = actionUserName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public List<ERPManagerOrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ERPManagerOrderDetail> details) {
		this.details = details;
	}
}
