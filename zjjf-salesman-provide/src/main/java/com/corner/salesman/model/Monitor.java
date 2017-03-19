package com.corner.salesman.model;

import org.apache.commons.lang3.StringUtils;

import com.corner.salesman.common.persistence.BaseEntity;

public class Monitor extends BaseEntity<Monitor> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private boolean isUpdate = false;

    private String total;
    
    private String msg;

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getTotal() {
		return StringUtils.isBlank(total)?"0":total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    

}