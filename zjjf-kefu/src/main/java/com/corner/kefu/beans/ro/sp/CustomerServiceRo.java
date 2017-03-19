package com.corner.kefu.beans.ro.sp;

import java.io.Serializable;

import com.corner.core.beans.ro.ABaseRo;

public class CustomerServiceRo extends ABaseRo implements Serializable {
	
	private String allName;
	
	private Byte status;
	
	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
}
