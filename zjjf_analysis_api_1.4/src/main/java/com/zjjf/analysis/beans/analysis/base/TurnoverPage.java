package com.zjjf.analysis.beans.analysis.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TurnoverPage implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String supplierId;

	private String addTime;

	private Integer cityId;

	private Integer areaId;

	private Integer spGroupId;

	private BigDecimal todayTurnover = new BigDecimal(0);

	private BigDecimal yesTodayTurnover = new BigDecimal(0);

	private BigDecimal dailyIncrease = new BigDecimal(0);

	private BigDecimal thisMonthTurnover = new BigDecimal(0);

	private BigDecimal lastMonthTurnover = new BigDecimal(0);

	private BigDecimal monthIncrease = new BigDecimal(0);

	private Date updateTime;

	private Integer createTime;

	private String cityName;

	private String areaName;

	private String supplierName;

	private String spGroupName;

	private BigDecimal turnover = new BigDecimal(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId == null ? null : supplierId.trim();
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime == null ? null : addTime.trim();
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
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

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}

}