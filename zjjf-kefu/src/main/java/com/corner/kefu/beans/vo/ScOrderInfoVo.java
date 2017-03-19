package com.corner.kefu.beans.vo;



import java.io.Serializable;

import com.corner.core.beans.ScOrderInfo;

public class ScOrderInfoVo extends ScOrderInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer totQuantity;
	public Integer getTotQuantity() {
		return totQuantity;
	}
	public void setTotQuantity(Integer totQuantity) {
		this.totQuantity = totQuantity;
	}
	
}
