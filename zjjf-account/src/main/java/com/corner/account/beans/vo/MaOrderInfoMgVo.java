package com.corner.account.beans.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.ScOrderDetail;

public class MaOrderInfoMgVo extends MaOrderInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//打印订单总计
	private BigDecimal printPrice;
	private Integer totQuantity;
	//订单详情list
	private List<ScOrderDetail> scOrderDetails = new ArrayList<ScOrderDetail>();

	public BigDecimal getPrintPrice() {
		return printPrice;
	}

	public void setPrintPrice(BigDecimal printPrice) {
		this.printPrice = printPrice;
	}
	public Integer getTotQuantity() {
		return totQuantity;
	}

	public void setTotQuantity(Integer totQuantity) {
		this.totQuantity = totQuantity;
	}
	public List<ScOrderDetail> getScOrderDetails() {
		return scOrderDetails;
	}

	public void setScOrderDetails(List<ScOrderDetail> scOrderDetails) {
		this.scOrderDetails = scOrderDetails;
	}
}
