package com.zjjf.analysis.beans.vo.orders.sporder;

import java.io.Serializable;
import java.math.BigDecimal;

public class SpOrderTurnoverVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String addTime;
	private String supplierId;
	private Integer cityId;
	private Integer areaId;
	private Integer spGroupId;
	private BigDecimal todayTurnover;
	private BigDecimal yesTodayTurnover;
	private BigDecimal dailyIncrease;
	private BigDecimal thisMonthTurnover;
	private BigDecimal lastMonthTurnover;
	private BigDecimal monthIncrease;
	private String supplierName;
	private String cityName;
	private String areaName;

	private BigDecimal thisMonthTurnoverSum;
	private BigDecimal lastMonthTurnoverSum;
	private BigDecimal todayTurnoverSum;
	private BigDecimal yesTodayTurnoverSum;
	private BigDecimal turnover = new BigDecimal(0);

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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

	public BigDecimal getTodayTurnover() {
		return todayTurnover;
	}

	public void setTodayTurnover(BigDecimal todayTurnover) {
		this.todayTurnover = todayTurnover;
	}

	public BigDecimal getYesTodayTurnover() {
		return yesTodayTurnover;
	}

	public void setYesTodayTurnover(BigDecimal yesTodayTurnover) {
		this.yesTodayTurnover = yesTodayTurnover;
	}

	public BigDecimal getDailyIncrease() {
		return dailyIncrease;
	}

	public void setDailyIncrease(BigDecimal dailyIncrease) {
		this.dailyIncrease = dailyIncrease;
	}

	public BigDecimal getThisMonthTurnover() {
		return thisMonthTurnover;
	}

	public void setThisMonthTurnover(BigDecimal thisMonthTurnover) {
		this.thisMonthTurnover = thisMonthTurnover;
	}

	public BigDecimal getLastMonthTurnover() {
		return lastMonthTurnover;
	}

	public void setLastMonthTurnover(BigDecimal lastMonthTurnover) {
		this.lastMonthTurnover = lastMonthTurnover;
	}

	public BigDecimal getMonthIncrease() {
		return monthIncrease;
	}

	public void setMonthIncrease(BigDecimal monthIncrease) {
		this.monthIncrease = monthIncrease;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public BigDecimal getThisMonthTurnoverSum() {
		return thisMonthTurnoverSum;
	}

	public void setThisMonthTurnoverSum(BigDecimal thisMonthTurnoverSum) {
		this.thisMonthTurnoverSum = thisMonthTurnoverSum;
	}

	public BigDecimal getLastMonthTurnoverSum() {
		return lastMonthTurnoverSum;
	}

	public void setLastMonthTurnoverSum(BigDecimal lastMonthTurnoverSum) {
		this.lastMonthTurnoverSum = lastMonthTurnoverSum;
	}

	public BigDecimal getTodayTurnoverSum() {
		return todayTurnoverSum;
	}

	public void setTodayTurnoverSum(BigDecimal todayTurnoverSum) {
		this.todayTurnoverSum = todayTurnoverSum;
	}

	public BigDecimal getYesTodayTurnoverSum() {
		return yesTodayTurnoverSum;
	}

	public void setYesTodayTurnoverSum(BigDecimal yesTodayTurnoverSum) {
		this.yesTodayTurnoverSum = yesTodayTurnoverSum;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

}
