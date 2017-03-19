package com.corner.kefu.beans.ro.sp;

import com.corner.core.beans.SpVoucherActive;
import com.corner.kefu.config.CommonPageConfig;

import java.util.Date;

public class SpVoucherActiveRo extends SpVoucherActive {
	private Integer pageIndex = CommonPageConfig.commonPageIndex;
	private Integer pageSize = CommonPageConfig.commonPageSize;
	private Integer spGropId;
	private String acGroupId;
	private String sortOrder;
	private String sortName;
	private Date todayNow;
	private String ruleStartPriceStr;// 单笔订单满多少元字符串
	private String ruleStartStr;
	private String ruleEndStr;
	private String searchKey;// 查询关键字
	private Boolean isVoluntary = false;// 批发商是否资源参与活动
	private String keyStr;

	public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	public Integer getSpGropId() {
		return spGropId;
	}

	public void setSpGropId(Integer spGropId) {
		this.spGropId = spGropId;
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

	public String getAcGroupId() {
		return acGroupId;
	}

	public void setAcGroupId(String acGroupId) {
		this.acGroupId = acGroupId;
	}

	public Date getTodayNow() {
		return todayNow;
	}

	public void setTodayNow(Date todayNow) {
		this.todayNow = todayNow;
	}

	public String getRuleStartPriceStr() {
		return ruleStartPriceStr;
	}

	public void setRuleStartPriceStr(String ruleStartPriceStr) {
		this.ruleStartPriceStr = ruleStartPriceStr;
	}

	public String getRuleStartStr() {
		return ruleStartStr;
	}

	public void setRuleStartStr(String ruleStartStr) {
		this.ruleStartStr = ruleStartStr;
	}

	public String getRuleEndStr() {
		return ruleEndStr;
	}

	public void setRuleEndStr(String ruleEndStr) {
		this.ruleEndStr = ruleEndStr;
	}

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

	public Boolean getIsVoluntary() {
		return isVoluntary;
	}

	public void setIsVoluntary(Boolean isVoluntary) {
		this.isVoluntary = isVoluntary;
	}
}
