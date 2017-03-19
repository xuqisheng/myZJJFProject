package com.zjjf.analysis.beans.vo.supplier;

import java.io.Serializable;
import java.math.BigDecimal;

public class SupplierFreeVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String supplierId;
	private String authkey;
	private Integer cityId;
	private Integer areaId;
	private Integer spGroupId;
	private String cityName;
	private String areaName;
	private String supplierName;
	private String todayTurnover;
	private String totalRebate;
	private String totalCoupon;
	private String totalFree;
	private String increase;
	private String monthTurnover;
	private String monthRebate;
	private String monthCoupon;
	private String monthTotalFree;
	private String monthIncrease;
	private String timeType;
	private String spGroupName;

	private BigDecimal weekTurnover;

	private String supplierCode;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getTodayTurnover() {
		return todayTurnover;
	}

	public void setTodayTurnover(String todayTurnover) {
		this.todayTurnover = todayTurnover;
	}

	public String getTotalRebate() {
		return totalRebate;
	}

	public void setTotalRebate(String totalRebate) {
		this.totalRebate = totalRebate;
	}

	public String getTotalCoupon() {
		return totalCoupon;
	}

	public void setTotalCoupon(String totalCoupon) {
		this.totalCoupon = totalCoupon;
	}

	public void setMonthCoupon(String monthCoupon) {
		this.monthCoupon = monthCoupon;
	}

	public String getIncrease() {
		return increase;
	}

	public void setIncrease(String increase) {
		this.increase = increase;
	}

	public String getMonthTurnover() {
		return monthTurnover;
	}

	public void setMonthTurnover(String monthTurnover) {
		this.monthTurnover = monthTurnover;
	}

	public String getMonthCoupon() {
		return monthCoupon;
	}

	public String getTotalFree() {
		return totalFree;
	}

	public void setTotalFree(String totalFree) {
		this.totalFree = totalFree;
	}

	public String getMonthTotalFree() {
		return monthTotalFree;
	}

	public void setMonthTotalFree(String monthTotalFree) {
		this.monthTotalFree = monthTotalFree;
	}

	public String getMonthRebate() {
		return monthRebate;
	}

	public void setMonthRebate(String monthRebate) {
		this.monthRebate = monthRebate;
	}

	public String getMonthIncrease() {
		return monthIncrease;
	}

	public void setMonthIncrease(String monthIncrease) {
		this.monthIncrease = monthIncrease;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public BigDecimal getWeekTurnover() {
		return weekTurnover;
	}

	public void setWeekTurnover(BigDecimal weekTurnover) {
		this.weekTurnover = weekTurnover;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}

	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}

}
