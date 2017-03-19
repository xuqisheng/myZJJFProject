package com.corner.scms.beans.vo.erp;

import com.corner.core.beans.ERPWarehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ERPWarehouseVo extends ERPWarehouse implements Serializable {

	private String provinceName;
	private String cityName;
	private String areaName;
	
	private String whName;
	private String whAreaName;
	
	private List<ERPWarehouseVo> treeList = new ArrayList<ERPWarehouseVo>();
	
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
	public String getWhAreaName() {
		return whAreaName;
	}
	public void setWhAreaName(String whAreaName) {
		this.whAreaName = whAreaName;
	}
	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
	}
	public List<ERPWarehouseVo> getTreeList() {
		return treeList;
	}
	public void setTreeList(List<ERPWarehouseVo> treeList) {
		this.treeList = treeList;
	}
}
