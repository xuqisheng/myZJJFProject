package com.corner.kefu.beans.ro;

import com.corner.core.beans.Store;
import com.corner.kefu.config.CommonPageConfig;

public class StoreMgRo extends Store {
	private Integer pageIndex = CommonPageConfig.commonPageIndex;
	private Integer pageSize = CommonPageConfig.commonPageSize;

	private String userId;

	private String userName;
	
	private String saleManMobile;

	public Integer getPageIndex() {
		return (pageIndex - 1) * pageSize;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSaleManMobile() {
		return saleManMobile;
	}

	public void setSaleManMobile(String saleManMobile) {
		this.saleManMobile = saleManMobile;
	}
}
