package com.corner.kefu.beans.ro.scms;

import com.corner.core.beans.ro.AmazeUIListRo;

public class ScmsWarehouseRo  extends AmazeUIListRo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Integer province;
	
	private Integer city;
	
	private Integer county;
	
	
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getProvince() {
		return province;
	}

	
	public void setProvince(Integer province) {
		this.province = province;
	}

	
	public Integer getCity() {
		return city;
	}

	
	public void setCity(Integer city) {
		this.city = city;
	}

	
	public Integer getCounty() {
		return county;
	}

	
	public void setCounty(Integer county) {
		this.county = county;
	}
}
