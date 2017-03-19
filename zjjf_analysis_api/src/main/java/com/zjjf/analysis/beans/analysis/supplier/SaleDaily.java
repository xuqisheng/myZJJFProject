package com.zjjf.analysis.beans.analysis.supplier;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleDaily extends SupplierDaily implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cityName;
	private String areaName;
	private String spGroupName;
	private String supplierName;
	private BigDecimal yesTodayturnover = new BigDecimal(0);
	private BigDecimal turnover = new BigDecimal(0);
	private Integer todayGoal = 0;
	private String rateDailyComp = "0";
	private BigDecimal fee = new BigDecimal(0);
	private String rateFee = "0";
	private BigDecimal sumMonth = new BigDecimal(0);
	private Integer goalMonth = 0;
	private String rateMonthComp = "0";
	private BigDecimal totalFee = new BigDecimal(0);
	private String rateFeeMonth = "0";

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

	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
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

	public String getRateDailyComp() {
		return rateDailyComp;
	}

	public void setRateDailyComp(String rateDailyComp) {
		this.rateDailyComp = rateDailyComp;
	}

	public String getRateMonthComp() {
		return rateMonthComp;
	}

	public void setRateMonthComp(String rateMonthComp) {
		this.rateMonthComp = rateMonthComp;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getRateFee() {
		return rateFee;
	}

	public void setRateFee(String rateFee) {
		this.rateFee = rateFee;
	}

	public String getRateFeeMonth() {
		return rateFeeMonth;
	}

	public void setRateFeeMonth(String rateFeeMonth) {
		this.rateFeeMonth = rateFeeMonth;
	}

}