package com.corner.scms.beans.vo.sc;

import java.util.List;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScOrderInfo;

public class ScOrderInfoVo extends ScOrderInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String address;

	private List<ScOrderDetail> scorders;

	public List<ScOrderDetail> getScorders() {
		return scorders;
	}

	public void setScorders(List<ScOrderDetail> scorders) {
		this.scorders = scorders;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
