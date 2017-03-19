package com.corner.kefu.beans.ro;

import com.corner.core.beans.ConfigPay;

public class ConfigPayRo extends ConfigPay {

	//当前页
	private int pageIndex ;
	//总数
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
}
