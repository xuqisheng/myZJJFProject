package com.corner.auth.beans.ro;

import java.io.Serializable;
import java.text.MessageFormat;

public abstract class ABaseRo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// token
	private String token;

	private String sortOrder;

	private String sortName;
    
	private int pageIndex ;
	//默认加载10条
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

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getOrderByStr() {
		return MessageFormat.format("{0} {1}", sortName, sortOrder);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
