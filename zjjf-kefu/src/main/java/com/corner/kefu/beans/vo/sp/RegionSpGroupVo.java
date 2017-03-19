package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;

public class RegionSpGroupVo extends RegionVo implements Serializable {

	/**
	 * @desc  
	 * @date 2016年9月7日  上午10:19:47
	 * @author 小武
	 * @version  飓风
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer spGroupId;
	
	private String spGroupName;
	
	private String regionSpGroupMapId;
	
	private String provinceName;
	
	private String cityName;
	
	private String areaName;

	public Integer getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}

	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}

	public String getRegionSpGroupMapId() {
		return regionSpGroupMapId;
	}

	public void setRegionSpGroupMapId(String regionSpGroupMapId) {
		this.regionSpGroupMapId = regionSpGroupMapId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	

}
