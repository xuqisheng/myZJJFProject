package com.corner.kefu.beans.ro;

import java.io.Serializable;

import com.corner.core.beans.Reminder;

public class ReminderRo extends Reminder implements Serializable {
	
	private int pageIndex;
	private int pageSize = 10;
	
	private Integer spGroupId;
	private String supplierNameOrTel;
	private String storeNameOrTel;
	

	public int getPageIndex() {
		return pageSize * ((pageIndex - 1) > 0 ? (pageIndex - 1) : 0);
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}

	public String getSupplierNameOrTel() {
		return supplierNameOrTel;
	}

	public void setSupplierNameOrTel(String supplierNameOrTel) {
		this.supplierNameOrTel = supplierNameOrTel;
	}

	public String getStoreNameOrTel() {
		return storeNameOrTel;
	}

	public void setStoreNameOrTel(String storeNameOrTel) {
		this.storeNameOrTel = storeNameOrTel;
	}
}
