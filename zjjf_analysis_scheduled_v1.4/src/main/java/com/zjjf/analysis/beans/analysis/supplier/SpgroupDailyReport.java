package com.zjjf.analysis.beans.analysis.supplier;

import java.math.BigDecimal;
import java.util.Date;

public class SpgroupDailyReport {
	private Integer id;

	private Integer cityId = 0;

	private Integer areaId = 0;

	private Integer spGroupId = 0;

	private Integer dayTime = 0;

	private Integer yestodayAddReg = 0;

	private Integer todayAddReg = 0;

	private Integer monthReg = 0;

	private Integer monthGoalReg = 0;

	private BigDecimal monthRegIncrease = new BigDecimal(0);

	private Integer yestodayAddActive = 0;

	private Integer todayAddActive = 0;

	private Integer yestodayMaintain = 0;

	private Integer todayMaintain = 0;

	private Integer yestodayStoreOrderCount = 0;

	private Integer storeOrderCount = 0;

	private Integer monthActive = 0;

	private Integer monthGoalActive = 0;

	private BigDecimal monthActiveIncrease= new BigDecimal(0);

	private Integer totalStore = 0;

	private Integer unorderStore = 0;

	private Integer monthAddActive = 0;

	private Date updateTime;

	private Integer createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getDayTime() {
		return dayTime;
	}

	public void setDayTime(Integer dayTime) {
		this.dayTime = dayTime;
	}

	public Integer getYestodayAddReg() {
		return yestodayAddReg;
	}

	public void setYestodayAddReg(Integer yestodayAddReg) {
		this.yestodayAddReg = yestodayAddReg;
	}

	public Integer getTodayAddReg() {
		return todayAddReg;
	}

	public void setTodayAddReg(Integer todayAddReg) {
		this.todayAddReg = todayAddReg;
	}

	public Integer getMonthReg() {
		return monthReg;
	}

	public void setMonthReg(Integer monthReg) {
		this.monthReg = monthReg;
	}

	public Integer getMonthGoalReg() {
		return monthGoalReg;
	}

	public void setMonthGoalReg(Integer monthGoalReg) {
		this.monthGoalReg = monthGoalReg;
	}

	public BigDecimal getMonthRegIncrease() {
		return monthRegIncrease;
	}

	public void setMonthRegIncrease(BigDecimal monthRegIncrease) {
		this.monthRegIncrease = monthRegIncrease;
	}

	public Integer getYestodayAddActive() {
		return yestodayAddActive;
	}

	public void setYestodayAddActive(Integer yestodayAddActive) {
		this.yestodayAddActive = yestodayAddActive;
	}

	public Integer getTodayAddActive() {
		return todayAddActive;
	}

	public void setTodayAddActive(Integer todayAddActive) {
		this.todayAddActive = todayAddActive;
	}

	public Integer getYestodayMaintain() {
		return yestodayMaintain;
	}

	public void setYestodayMaintain(Integer yestodayMaintain) {
		this.yestodayMaintain = yestodayMaintain;
	}

	public Integer getTodayMaintain() {
		return todayMaintain;
	}

	public void setTodayMaintain(Integer todayMaintain) {
		this.todayMaintain = todayMaintain;
	}

	public Integer getYestodayStoreOrderCount() {
		return yestodayStoreOrderCount;
	}

	public void setYestodayStoreOrderCount(Integer yestodayStoreOrderCount) {
		this.yestodayStoreOrderCount = yestodayStoreOrderCount;
	}

	public Integer getStoreOrderCount() {
		return storeOrderCount;
	}

	public void setStoreOrderCount(Integer storeOrderCount) {
		this.storeOrderCount = storeOrderCount;
	}

	public Integer getMonthActive() {
		return monthActive;
	}

	public void setMonthActive(Integer monthActive) {
		this.monthActive = monthActive;
	}

	public Integer getMonthGoalActive() {
		return monthGoalActive;
	}

	public void setMonthGoalActive(Integer monthGoalActive) {
		this.monthGoalActive = monthGoalActive;
	}

	public BigDecimal getMonthActiveIncrease() {
		return monthActiveIncrease;
	}

	public void setMonthActiveIncrease(BigDecimal monthActiveIncrease) {
		this.monthActiveIncrease = monthActiveIncrease;
	}

	public Integer getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(Integer totalStore) {
		this.totalStore = totalStore;
	}

	public Integer getUnorderStore() {
		return unorderStore;
	}

	public void setUnorderStore(Integer unorderStore) {
		this.unorderStore = unorderStore;
	}

	public Integer getMonthAddActive() {
		return monthAddActive;
	}

	public void setMonthAddActive(Integer monthAddActive) {
		this.monthAddActive = monthAddActive;
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
}