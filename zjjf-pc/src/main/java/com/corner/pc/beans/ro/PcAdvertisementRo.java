package com.corner.pc.beans.ro;

import java.io.Serializable;

import com.corner.pc.beans.PcAdvertisement;

public class PcAdvertisementRo extends PcAdvertisement implements Serializable {
	private int pageIndex;

	private int pageSize;
	
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
