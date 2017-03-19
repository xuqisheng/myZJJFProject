package com.corner.kefu.beans.vo;



import java.io.Serializable;
import java.util.List;

import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.ScOrderDetail;

public class MaOrderInfoVo extends MaOrderInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer totQuantity;
	private List<ScOrderDetail> scOrderDetails;
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
