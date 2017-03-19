package com.zjjf.analysis.beans.analysis.supplier;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.zjjf.analysis.beans.vo.ParamMapVo;

public class SpGroupDaily extends ParamMapVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer spGroupId = 0;

	private Integer dayTime;

	private BigDecimal turnover = new BigDecimal(0);

	private BigDecimal totalRebate = new BigDecimal(0);

	private BigDecimal totalCoupon = new BigDecimal(0);

	private BigDecimal totalFreight = new BigDecimal(0);

	private Integer orderCount = 0;

	private Integer sku = 0;

	private Integer quantity = 0;

	private Integer storeOrderCount = 0;

	private Integer newRegStoreOrdercount = 0;

	private Integer cityId;

	private Integer areaId;

	private Integer createTime;

	private Date updateTime;

	private Integer unorderStore = 0;

	private Integer totalStore = 0;

	private BigDecimal monthReg = new BigDecimal(0);

	private String monthRegIncrease = "";
	private BigDecimal monthActive = new BigDecimal(0);

	private String monthActiveIncrease = "";

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

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUnorderStore() {
		return unorderStore;
	}

	public void setUnorderStore(Integer unorderStore) {
		this.unorderStore = unorderStore;
	}

	public Integer getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(Integer totalStore) {
		this.totalStore = totalStore;
	}

	public BigDecimal getMonthReg() {
		return monthReg;
	}

	public void setMonthReg(BigDecimal monthReg) {
		this.monthReg = monthReg;
	}

	public BigDecimal getMonthActive() {
		return monthActive;
	}

	public void setMonthActive(BigDecimal monthActive) {
		this.monthActive = monthActive;
	}

	public String getMonthRegIncrease() {
		return monthRegIncrease;
	}

	public void setMonthRegIncrease(String monthRegIncrease) {
		this.monthRegIncrease = monthRegIncrease;
	}

	public String getMonthActiveIncrease() {
		return monthActiveIncrease;
	}

	public void setMonthActiveIncrease(String monthActiveIncrease) {
		this.monthActiveIncrease = monthActiveIncrease;
	}

}