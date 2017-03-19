package com.corner.kefu.beans.ro.sp;

import com.corner.core.beans.ro.AmazeUIListRo;

public class SalesmanRo extends AmazeUIListRo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer areaId;
    private String mobile;
    private String keywords;
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
