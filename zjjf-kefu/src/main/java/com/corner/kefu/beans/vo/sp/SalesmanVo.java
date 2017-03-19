package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;

import com.corner.core.beans.Salesman;


public class SalesmanVo extends Salesman implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String provincename;//省名
	private String cityname;//市名
	private String areaname;//区名
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	
}
