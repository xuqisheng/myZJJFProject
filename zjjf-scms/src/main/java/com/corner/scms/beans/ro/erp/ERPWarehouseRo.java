package com.corner.scms.beans.ro.erp;

import com.corner.core.beans.ERPWarehouse;

import java.io.Serializable;

public class ERPWarehouseRo extends ERPWarehouse implements Serializable {
	//分页
	private int pageIndex;
	private int pageSize = 10;
	
	public int getPageIndex() {
		return pageSize * ((pageIndex - 1) > 0 ? (pageIndex - 1) : 0);
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	private String numAndName;
	
	private Integer spGroupId;

	public String getNumAndName() {
		return numAndName;
	}

	public void setNumAndName(String numAndName) {
		this.numAndName = numAndName;
	}

	public Integer getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}
}
