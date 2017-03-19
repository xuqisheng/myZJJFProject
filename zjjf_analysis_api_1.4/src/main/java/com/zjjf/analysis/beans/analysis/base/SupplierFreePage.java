package com.zjjf.analysis.beans.analysis.base;

import java.math.BigDecimal;
import java.util.Date;

public class SupplierFreePage {
	private Integer id;

	private String supplierId;

	private Integer cityId;

	private Integer areaId;

	private Integer spGroupId;

	private String addTime;

	private BigDecimal todayTurnover;

	private BigDecimal totalRebate;

	private BigDecimal totalCoupon;

	private BigDecimal totalFree;

	private BigDecimal increase;

	private BigDecimal monthTurnover;

	private BigDecimal monthRebate;

	private BigDecimal monthCoupon;

	private BigDecimal monthTotalFree;

	private BigDecimal monthIncrease;

	private Date updateTime;

	private Integer createTime;

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

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime == null ? null : addTime.trim();
	}

	public BigDecimal getTodayTurnover() {
		return todayTurnover;
	}

	public void setTodayTurnover(BigDecimal todayTurnover) {
		this.todayTurnover = todayTurnover;
	}

	public BigDecimal getTotalRebate() {
		return totalRebate;
	}

	public void setTotalRebate(BigDecimal totalRebate) {
		this.totalRebate = totalRebate;
	}

	public BigDecimal getTotalCoupon() {
		return totalCoupon;
	}

	public void setTotalCoupon(BigDecimal totalCoupon) {
		this.totalCoupon = totalCoupon;
	}

	public BigDecimal getTotalFree() {
		return totalFree;
	}

	public void setTotalFree(BigDecimal totalFree) {
		this.totalFree = totalFree;
	}

	public BigDecimal getIncrease() {
		return increase;
	}

	public void setIncrease(BigDecimal increase) {
		this.increase = increase;
	}

	public BigDecimal getMonthTurnover() {
		return monthTurnover;
	}

	public void setMonthTurnover(BigDecimal monthTurnover) {
		this.monthTurnover = monthTurnover;
	}

	public BigDecimal getMonthRebate() {
		return monthRebate;
	}

	public void setMonthRebate(BigDecimal monthRebate) {
		this.monthRebate = monthRebate;
	}

	public BigDecimal getMonthTotalFree() {
		return monthTotalFree;
	}

	public void setMonthTotalFree(BigDecimal monthTotalFree) {
		this.monthTotalFree = monthTotalFree;
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

	public BigDecimal getMonthCoupon() {
		return monthCoupon;
	}

	public void setMonthCoupon(BigDecimal monthCoupon) {
		this.monthCoupon = monthCoupon;
	}

}