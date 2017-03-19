package com.corner.kefu.beans.vo.sp;

import com.corner.core.beans.Store;

import java.math.BigDecimal;

public class StoreMgVo extends Store {

	private String storeGroupName;// 所在批发商组名

	private int totalOrder;// 下单次数

	private BigDecimal totalOrderPrice;

	private BigDecimal totalSendPrice;


	public BigDecimal getTotalSendPrice() {
		return totalSendPrice;
	}

	public void setTotalSendPrice(BigDecimal totalSendPrice) {
		this.totalSendPrice = totalSendPrice;
	}

	public BigDecimal getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(BigDecimal totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	public String getStoreGroupName() {
		return storeGroupName;
	}

	public void setStoreGroupName(String storeGroupName) {
		this.storeGroupName = storeGroupName;
	}

	public int getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}

}
