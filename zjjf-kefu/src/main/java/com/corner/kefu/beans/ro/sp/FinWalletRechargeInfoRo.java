package com.corner.kefu.beans.ro.sp;

import java.io.Serializable;
import java.util.Date;

import com.corner.core.beans.FinWalletRechargeInfo;

public class FinWalletRechargeInfoRo extends FinWalletRechargeInfo implements Serializable {
	private int pageIndex;
	// 默认加载5条
	private int pageSize = 10;

	private Date beginTime;
	private Date endTime;
	private String mobile;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
