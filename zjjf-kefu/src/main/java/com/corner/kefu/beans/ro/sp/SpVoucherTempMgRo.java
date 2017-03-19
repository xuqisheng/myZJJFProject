package com.corner.kefu.beans.ro.sp;

import java.util.Date;

import com.corner.core.beans.SpVoucherTemp;
import com.corner.kefu.config.CommonPageConfig;

public class SpVoucherTempMgRo extends SpVoucherTemp {

	private Integer pageIndex = CommonPageConfig.commonPageIndex;
	private Integer pageSize = CommonPageConfig.commonPageSize;
	private String searchKey;
	private Date startDate;
	private Date endDate;
	private String useMoneyStr;

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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUseMoneyStr() {
		return useMoneyStr;
	}

	public void setUseMoneyStr(String useMoneyStr) {
		this.useMoneyStr = useMoneyStr;
	}

}
