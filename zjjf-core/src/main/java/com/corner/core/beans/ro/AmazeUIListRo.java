package com.corner.core.beans.ro;


public abstract  class AmazeUIListRo extends ABaseRo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
