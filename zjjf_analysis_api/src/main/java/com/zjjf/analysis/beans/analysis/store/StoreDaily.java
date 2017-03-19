package com.zjjf.analysis.beans.analysis.store;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StoreDaily implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer storeId;

	private Integer spGroupId;

	private String storeCode;

	private Integer dayTime;

	private BigDecimal turnover;

	private BigDecimal totalRebate;

	private BigDecimal totalCoupon;

	private BigDecimal totalFreight;

	private Integer orderCount;

	private Integer sku;

	private Integer quantity;

	private Date updateTime;

	private Integer createTime;

	private Integer isVisit;

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

	private BigDecimal orderCountDaily = new BigDecimal(0);

	private BigDecimal turnoverDaily = new BigDecimal(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode == null ? null : storeCode.trim();
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

	public BigDecimal getTotalFreight() {
		return totalFreight;
	}

	public void setTotalFreight(BigDecimal totalFreight) {
		this.totalFreight = totalFreight;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public Integer getIsVisit() {
		return isVisit;
	}

	public void setIsVisit(Integer isVisit) {
		this.isVisit = isVisit;
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