package com.corner.scms.beans.vo.erp;

import java.io.Serializable;

import com.corner.core.beans.ERPPhysicsStockLog;

public class ERPPhysicsStockLogVo extends ERPPhysicsStockLog implements Serializable {
	
	private String whName;//仓库名
	
	private String waName;//库区名
	
	private String wpName;//库位名
	
	private String typeName;//库存操作名称

	private String orderId;
	
	private String orderNo;
	
	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getWaName() {
		return waName;
	}

	public void setWaName(String waName) {
		this.waName = waName;
	}

	public String getWpName() {
		return wpName;
	}

	public void setWpName(String wpName) {
		this.wpName = wpName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
