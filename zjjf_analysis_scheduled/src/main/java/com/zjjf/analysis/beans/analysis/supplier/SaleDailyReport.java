package com.zjjf.analysis.beans.analysis.supplier;

import java.math.BigDecimal;
import java.util.Date;

public class SaleDailyReport {

	private Integer id;

	private Integer cityId;

	private Integer areaId;

	private Integer spGroupId;

	private String supplierId;

	private Integer dayTime;

	private BigDecimal yesTodayturnover = new BigDecimal(0);

	private BigDecimal turnover = new BigDecimal(0);

	private Integer todayGoal = 0;

	private BigDecimal rateDailyComp = new BigDecimal(0);

	private BigDecimal fee = new BigDecimal(0);

	private BigDecimal rateFee = new BigDecimal(0);

	private BigDecimal sumMonth = new BigDecimal(0);

	private Integer goalMonth = 0;

	private BigDecimal rateMonthComp = new BigDecimal(0);

	private BigDecimal totalFee = new BigDecimal(0);

	private BigDecimal rateFeeMonth = new BigDecimal(0);

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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId == null ? null : supplierId.trim();
	}

	public Integer getDayTime() {
		return dayTime;
	}

	public void setDayTime(Integer dayTime) {
		this.dayTime = dayTime;
	}

	public BigDecimal getYesTodayturnover() {
		return yesTodayturnover;
	}

	public void setYesTodayturnover(BigDecimal yesTodayturnover) {
		this.yesTodayturnover = yesTodayturnover;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public BigDecimal getRateDailyComp() {
		return rateDailyComp;
	}

	public void setRateDailyComp(BigDecimal rateDailyComp) {
		this.rateDailyComp = rateDailyComp;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getRateFee() {
		return rateFee;
	}

	public void setRateFee(BigDecimal rateFee) {
		this.rateFee = rateFee;
	}

	public BigDecimal getSumMonth() {
		return sumMonth;
	}

	public void setSumMonth(BigDecimal sumMonth) {
		this.sumMonth = sumMonth;
	}

	public Integer getTodayGoal() {
		return todayGoal;
	}

	public void setTodayGoal(Integer todayGoal) {
		this.todayGoal = todayGoal;
	}

	public Integer getGoalMonth() {
		return goalMonth;
	}

	public void setGoalMonth(Integer goalMonth) {
		this.goalMonth = goalMonth;
	}

	public BigDecimal getRateMonthComp() {
		return rateMonthComp;
	}

	public void setRateMonthComp(BigDecimal rateMonthComp) {
		this.rateMonthComp = rateMonthComp;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public BigDecimal getRateFeeMonth() {
		return rateFeeMonth;
	}

	public void setRateFeeMonth(BigDecimal rateFeeMonth) {
		this.rateFeeMonth = rateFeeMonth;
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