package com.zjjf.analysis.beans.analysis.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SupplierFreePage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String supplierId;

	private Integer spGroupId;

	private Integer cityId;

	private Integer areaId;

	private String addTime;

	private Integer dayTime;

	private BigDecimal todayTurnover = new BigDecimal(0);

	private BigDecimal totalRebate = new BigDecimal(0);

	private BigDecimal totalCoupon = new BigDecimal(0);

	private BigDecimal totalFree = new BigDecimal(0);

	private BigDecimal increase = new BigDecimal(0);

	private BigDecimal monthTurnover = new BigDecimal(0);

	private BigDecimal monthRebate = new BigDecimal(0);

	private BigDecimal monthCoupon = new BigDecimal(0);

	private BigDecimal monthTotalFree = new BigDecimal(0);

	private BigDecimal monthIncrease = new BigDecimal(0);

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

	public BigDecimal getMonthCoupon() {
		return monthCoupon;
	}

	public void setMonthCoupon(BigDecimal monthCoupon) {
		this.monthCoupon = monthCoupon;
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

}