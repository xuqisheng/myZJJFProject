package com.corner.kefu.beans.ro.sp;

import java.io.Serializable;
import java.util.Date;

import com.corner.core.beans.SKUActive;
import com.corner.kefu.config.CommonPageConfig;

public class SKUActiveRo extends SKUActive implements Serializable {
	
	private static final long serialVersionUID = -8285948622671937122L;
	
	private int pageIndex = CommonPageConfig.commonPageIndex;
	private int pageSize = CommonPageConfig.commonPageSize;
	
	private Integer buyLimitNum;
	
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
	
	private Date beginTime;
	private Date endTime;
	
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

	public Integer getBuyLimitNum() {
		return buyLimitNum;
	}

	public void setBuyLimitNum(Integer buyLimitNum) {
		this.buyLimitNum = buyLimitNum;
	}
}
