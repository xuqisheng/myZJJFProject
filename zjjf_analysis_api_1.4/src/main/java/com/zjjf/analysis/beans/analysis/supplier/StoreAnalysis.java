package com.zjjf.analysis.beans.analysis.supplier;

import java.io.Serializable;
import java.math.BigDecimal;

public class StoreAnalysis extends SaleDailyReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer spGroupId = 0;

	private Integer dayTime;

	private BigDecimal turnover = new BigDecimal(0);

	private Integer orderCount = 0;

	private Integer storeOrderCount = 0;

	private Integer newRegStoreOrdercount = 0;

	private Integer col1OrderCountTimes = 0;

	private Integer col2OrderCountTimes = 0;

	private Integer col3OrderCountTimes = 0;

	private Integer col4OrderCountTimes = 0;

	private Integer totalCountTimes = 0;

	private Integer col1TurnoverTimes = 0;

	private Integer col2TurnoverTimes = 0;

	private Integer col3TurnoverTimes = 0;

	private Integer col4TurnoverTimes = 0;

	private Integer col5TurnoverTimes = 0;

	private Integer col6TurnoverTimes = 0;

	private Integer totalTurnoverTimes = 0;

	private BigDecimal avgOrderCount = new BigDecimal(0);

	private BigDecimal avgTurnover = new BigDecimal(0);

	private BigDecimal orderCountDaily = new BigDecimal(0);

	private BigDecimal turnoverDaily = new BigDecimal(0);

	private Integer monthGoal = 0;

	private Integer monthTotal = 0;

	private BigDecimal monthCompletion = new BigDecimal(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getStoreOrderCount() {
		return storeOrderCount;
	}

	public void setStoreOrderCount(Integer storeOrderCount) {
		this.storeOrderCount = storeOrderCount;
	}

	public Integer getNewRegStoreOrdercount() {
		return newRegStoreOrdercount;
	}

	public void setNewRegStoreOrdercount(Integer newRegStoreOrdercount) {
		this.newRegStoreOrdercount = newRegStoreOrdercount;
	}

	public Integer getCol1OrderCountTimes() {
		return col1OrderCountTimes;
	}

	public void setCol1OrderCountTimes(Integer col1OrderCountTimes) {
		this.col1OrderCountTimes = col1OrderCountTimes;
	}

	public Integer getCol2OrderCountTimes() {
		return col2OrderCountTimes;
	}

	public void setCol2OrderCountTimes(Integer col2OrderCountTimes) {
		this.col2OrderCountTimes = col2OrderCountTimes;
	}

	public Integer getCol3OrderCountTimes() {
		return col3OrderCountTimes;
	}

	public void setCol3OrderCountTimes(Integer col3OrderCountTimes) {
		this.col3OrderCountTimes = col3OrderCountTimes;
	}

	public Integer getCol4OrderCountTimes() {
		return col4OrderCountTimes;
	}

	public void setCol4OrderCountTimes(Integer col4OrderCountTimes) {
		this.col4OrderCountTimes = col4OrderCountTimes;
	}

	public Integer getTotalCountTimes() {
		return totalCountTimes;
	}

	public void setTotalCountTimes(Integer totalCountTimes) {
		this.totalCountTimes = totalCountTimes;
	}

	public Integer getCol1TurnoverTimes() {
		return col1TurnoverTimes;
	}

	public void setCol1TurnoverTimes(Integer col1TurnoverTimes) {
		this.col1TurnoverTimes = col1TurnoverTimes;
	}

	public Integer getCol2TurnoverTimes() {
		return col2TurnoverTimes;
	}

	public void setCol2TurnoverTimes(Integer col2TurnoverTimes) {
		this.col2TurnoverTimes = col2TurnoverTimes;
	}

	public Integer getCol3TurnoverTimes() {
		return col3TurnoverTimes;
	}

	public void setCol3TurnoverTimes(Integer col3TurnoverTimes) {
		this.col3TurnoverTimes = col3TurnoverTimes;
	}

	public Integer getCol4TurnoverTimes() {
		return col4TurnoverTimes;
	}

	public void setCol4TurnoverTimes(Integer col4TurnoverTimes) {
		this.col4TurnoverTimes = col4TurnoverTimes;
	}

	public Integer getCol5TurnoverTimes() {
		return col5TurnoverTimes;
	}

	public void setCol5TurnoverTimes(Integer col5TurnoverTimes) {
		this.col5TurnoverTimes = col5TurnoverTimes;
	}

	public Integer getCol6TurnoverTimes() {
		return col6TurnoverTimes;
	}

	public void setCol6TurnoverTimes(Integer col6TurnoverTimes) {
		this.col6TurnoverTimes = col6TurnoverTimes;
	}

	public Integer getTotalTurnoverTimes() {
		return totalTurnoverTimes;
	}

	public void setTotalTurnoverTimes(Integer totalTurnoverTimes) {
		this.totalTurnoverTimes = totalTurnoverTimes;
	}

	public BigDecimal getAvgOrderCount() {
		return avgOrderCount;
	}

	public void setAvgOrderCount(BigDecimal avgOrderCount) {
		this.avgOrderCount = avgOrderCount;
	}

	public BigDecimal getAvgTurnover() {
		return avgTurnover;
	}

	public void setAvgTurnover(BigDecimal avgTurnover) {
		this.avgTurnover = avgTurnover;
	}

	public BigDecimal getOrderCountDaily() {
		return orderCountDaily;
	}

	public void setOrderCountDaily(BigDecimal orderCountDaily) {
		this.orderCountDaily = orderCountDaily;
	}

	public BigDecimal getTurnoverDaily() {
		return turnoverDaily;
	}

	public void setTurnoverDaily(BigDecimal turnoverDaily) {
		this.turnoverDaily = turnoverDaily;
	}

	public Integer getMonthGoal() {
		return monthGoal;
	}

	public void setMonthGoal(Integer monthGoal) {
		this.monthGoal = monthGoal;
	}

	public Integer getMonthTotal() {
		return monthTotal;
	}

	public void setMonthTotal(Integer monthTotal) {
		this.monthTotal = monthTotal;
	}

	public BigDecimal getMonthCompletion() {
		return monthCompletion;
	}

	public void setMonthCompletion(BigDecimal monthCompletion) {
		this.monthCompletion = monthCompletion;
	}

}