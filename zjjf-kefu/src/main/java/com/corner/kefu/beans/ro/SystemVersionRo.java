package com.corner.kefu.beans.ro;

import java.io.Serializable;
import java.util.Date;

import com.corner.core.beans.SystemVersion;

public class SystemVersionRo extends SystemVersion implements Serializable {
	//当前页
	private int pageIndex ;
	//总数
	private int pageSize = 10;
	
	private Date beginTime;
	private Date endTime;

	
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

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
