package com.corner.account.beans.ro;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

public class MaOrderMgCondition extends EasyUIQueryModel {

	private String orderId;
	private byte queryStatus;
	private boolean whPayStatus;
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public byte getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(byte queryStatus) {
		this.queryStatus = queryStatus;
	}

	public boolean isWhPayStatus() {
		return whPayStatus;
	}

	public void setWhPayStatus(boolean whPayStatus) {
		this.whPayStatus = whPayStatus;
	}
	@Override
	public String toHqlString() {
		return null;
	}

	@Override
	public Object[] toHqlObjects() {
		return null;
	}
}
