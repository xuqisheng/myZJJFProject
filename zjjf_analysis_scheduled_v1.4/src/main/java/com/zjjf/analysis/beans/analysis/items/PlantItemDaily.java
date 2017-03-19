package com.zjjf.analysis.beans.analysis.items;

import java.math.BigDecimal;
import java.util.Date;

import com.zjjf.analysis.beans.origin.ParamMapVo;

public class PlantItemDaily extends ParamMapVo {

	private Integer id;

	private Integer itemBaseId;

	private String itemId;

	private Integer spGroupId;

	private String supplierId;

	private Integer dayTime;

	private BigDecimal turnover = new BigDecimal(0);

	private BigDecimal maoli = new BigDecimal(0);

	private BigDecimal fee = new BigDecimal(0);

	private Integer orderCount = 0;

	private Integer quantity = 0;

	private Date updateTime;

	private Integer createTime;

	private BigDecimal maxPrice = new BigDecimal(0);

	private BigDecimal minPrice = new BigDecimal(0);

	private BigDecimal averagePrice = new BigDecimal(0);

	private Integer cityId;

	private String cityName;

	private Integer areaId;

	private String areaName;

	private String supplierName;

	private String spGroupName;

	private String classOne;

	private String classTwo;

	private Integer classOneId;

	private Integer classTwoId;

	private String mdseId;

	private String itemName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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

	public BigDecimal getMaoli() {
		return maoli;
	}

	public void setMaoli(BigDecimal maoli) {
		this.maoli = maoli;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
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

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getClassOne() {
		return classOne;
	}

	public void setClassOne(String classOne) {
		this.classOne = classOne;
	}

	public String getClassTwo() {
		return classTwo;
	}

	public void setClassTwo(String classTwo) {
		this.classTwo = classTwo;
	}

	public Integer getClassOneId() {
		return classOneId;
	}

	public void setClassOneId(Integer classOneId) {
		this.classOneId = classOneId;
	}

	public Integer getClassTwoId() {
		return classTwoId;
	}

	public void setClassTwoId(Integer classTwoId) {
		this.classTwoId = classTwoId;
	}

	public String getMdseId() {
		return mdseId;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}