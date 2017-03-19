package com.corner.scms.beans.ro.sc;

import java.io.Serializable;
import java.math.BigDecimal;

import com.corner.core.beans.ScOrderInfo;

public class ScOrderInfoMgRo extends ScOrderInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1284697009164468103L;
	
	private String orderId;//订单 Id
	
	private String id;
	
	//订单入库管理页面的收索条件
	private String orderIdAndSupply;
	//配送单页面的收索条件
	private String param;
	
	private String supplierId;   //批发商id  海灵子
	
	private Integer state;//订单状态
	
	private String is;//用于查看删除订单
	
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	private String orderId2;
	//总数量
	private Integer countNum;
	//总计
	private BigDecimal zongJi;
	public String getOrderId2() {
		return orderId2;
	}

	public void setOrderId2(String orderId2) {
		this.orderId2 = orderId2;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public BigDecimal getZongJi() {
		return zongJi;
	}

	public void setZongJi(BigDecimal zongJi) {
		this.zongJi = zongJi;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderIdAndSupply() {
		return orderIdAndSupply;
	}

	public void setOrderIdAndSupply(String orderIdAndSupply) {
		this.orderIdAndSupply = orderIdAndSupply;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getIs() {
		return is;
	}

	public void setIs(String is) {
		this.is = is;
	}
}