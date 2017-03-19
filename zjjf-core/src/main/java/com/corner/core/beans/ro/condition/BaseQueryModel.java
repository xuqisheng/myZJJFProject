package com.corner.core.beans.ro.condition;

import java.text.MessageFormat;

public abstract class BaseQueryModel {

	private String sortOrder;

	private String sortName;

	private int pageIndex;

	private int pageSize;
	
	private int recordStart;

	public abstract String toHqlString();

	public abstract Object[] toHqlObjects();

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

	public int getRecordStart() {
		return recordStart;
	}

	public void setRecordStart(int recordStart) {
		this.recordStart = recordStart;
	}

}
