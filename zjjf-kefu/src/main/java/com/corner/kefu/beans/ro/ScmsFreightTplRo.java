package com.corner.kefu.beans.ro;

import java.math.BigDecimal;

import com.corner.core.beans.ScmsFreightTpl;

public class ScmsFreightTplRo extends ScmsFreightTpl{

	
	private static final long serialVersionUID = 1L;
	private BigDecimal [] orderPrice;
	private Short [] orderNum;
	private BigDecimal [] freightPrice;
	private Float [] freight;
	private BigDecimal oneFreightPrice;
	public BigDecimal[] getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal[] orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Short[] getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Short[] orderNum) {
		this.orderNum = orderNum;
	}
	public BigDecimal[] getFreightPrice() {
		return freightPrice;
	}
	public void setFreightPrice(BigDecimal[] freightPrice) {
		this.freightPrice = freightPrice;
	}
	public Float[] getFreight() {
		return freight;
	}
	public void setFreight(Float[] freight) {
		this.freight = freight;
	}
	public BigDecimal getOneFreightPrice() {
		return oneFreightPrice;
	}
	public void setOneFreightPrice(BigDecimal oneFreightPrice) {
		this.oneFreightPrice = oneFreightPrice;
	}
}
