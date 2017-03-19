package com.zjjf.analysis.beans.analysis.supplier;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.zjjf.analysis.beans.vo.ParamMapVo;

public class SaleDailyReport extends ParamMapVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer cityId;

	private Integer areaId;

	private Integer spGroupId;

	private String supplierId;

	private Integer dayTime;

	private BigDecimal yesTodayturnover = new BigDecimal(0);

	private BigDecimal turnover = new BigDecimal(0);

	private BigDecimal todayGoal = new BigDecimal(0);

	private String rateDailyComp = "0";

	private BigDecimal fee = new BigDecimal(0);

	private String rateFee = "";

	private BigDecimal sumMonth = new BigDecimal(0);

	private BigDecimal goalMonth = new BigDecimal(0);

	private String rateMonthComp = "0";

	private BigDecimal totalFee = new BigDecimal(0);

	private String rateFeeMonth = "0";

	private Date updateTime;

	private Integer createTime;

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

	public BigDecimal getTodayGoal() {
		return todayGoal;
	}

	public void setTodayGoal(BigDecimal todayGoal) {
		this.todayGoal = todayGoal;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getRateFee() {
		return rateFee;
	}

	public void setRateFee(String rateFee) {
		this.rateFee = rateFee;
	}

	public BigDecimal getSumMonth() {
		return sumMonth;
	}

	public void setSumMonth(BigDecimal sumMonth) {
		this.sumMonth = sumMonth;
	}

	public BigDecimal getGoalMonth() {
		return goalMonth;
	}

	public void setGoalMonth(BigDecimal goalMonth) {
		this.goalMonth = goalMonth;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getRateMonthComp() {
		return rateMonthComp;
	}

	public void setRateMonthComp(String rateMonthComp) {
		this.rateMonthComp = rateMonthComp;
	}

	public String getRateDailyComp() {
		return rateDailyComp;
	}

	public void setRateDailyComp(String rateDailyComp) {
		this.rateDailyComp = rateDailyComp;
	}

	public String getRateFeeMonth() {
		return rateFeeMonth;
	}

	public void setRateFeeMonth(String rateFeeMonth) {
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

}