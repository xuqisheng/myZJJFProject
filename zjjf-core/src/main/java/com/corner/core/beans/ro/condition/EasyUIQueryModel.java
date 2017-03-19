package com.corner.core.beans.ro.condition;


/**
 * JSUI pagination parameters map to java 
 * @author luke
 *
 */
public abstract class EasyUIQueryModel extends BaseQueryModel {

	public void setOrder(String order) {
		this.setSortOrder(order);
	}

	public void setSort(String sort) {
		this.setSortName(sort);
	}

	public void setRows(int rows) {
		this.setPageSize(rows);
	}

	public void setPage(int page) {
		this.setPageIndex(page);
	}

}
