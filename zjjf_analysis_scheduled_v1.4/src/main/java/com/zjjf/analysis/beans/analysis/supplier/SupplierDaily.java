package com.zjjf.analysis.beans.analysis.supplier;

import com.zjjf.analysis.beans.origin.ParamMapVo;

import java.math.BigDecimal;
import java.util.Date;

public class SupplierDaily extends ParamMapVo {

	private Integer id = 0;

	private String supplierId = "0";

	private Integer spGroupId = 0;

	private String dayTime;

	private BigDecimal turnover = new BigDecimal(0);

	private BigDecimal totalRebate = new BigDecimal(0);

	private BigDecimal totalCoupon = new BigDecimal(0);

	private BigDecimal totalFreight = new BigDecimal(0);

	private BigDecimal orderCount = new BigDecimal(0);

	private Integer sku = 0;

	private Integer quantity = 0;

	private Integer newRegistered = 0;

	private Integer deliveryTimes = 0;

	private Date updateTime;

	private Integer createTime;

	private BigDecimal totalFree = new BigDecimal(0);

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

	public Integer getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}

	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
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

	public BigDecimal getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(BigDecimal orderCount) {
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

	public Integer getNewRegistered() {
		return newRegistered;
	}

	public void setNewRegistered(Integer newRegistered) {
		this.newRegistered = newRegistered;
	}

	public Integer getDeliveryTimes() {
		return deliveryTimes;
	}

	public void setDeliveryTimes(Integer deliveryTimes) {
		this.deliveryTimes = deliveryTimes;
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

	public BigDecimal getTotalFree() {
		return totalFree;
	}

	public void setTotalFree(BigDecimal totalFree) {
		this.totalFree = totalFree;
	}

}