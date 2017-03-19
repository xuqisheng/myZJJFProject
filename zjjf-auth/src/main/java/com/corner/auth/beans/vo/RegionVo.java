package com.corner.auth.beans.vo;

import java.io.Serializable;

import com.corner.auth.beans.Region;

public class RegionVo extends Region implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pName;
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		 this.pName = pName == null ? null : pName.trim();
	}
	
}
