package com.corner.data.analysis.beans.vo;

public class OrderStatisVo {

	private String areaName;//区域名称
	private String areaId;//区域ID 
	private Double productPrice; //商品价格
	private Double orderPrice; //订单价格
	private Double productCode;//商品编码
	private String productName;//商品名称
	private String productSpec;//商品规格
	private Double productUnitPrice;//商品单价
	private Integer monthTotalQuantity;//本月商品总数量
	private Double monthTotalMoney;//本月商品总金额
	private String wholesalerId;//批发商ID
	private String shopRatedName;//商铺定格
	
	private String shopRatedId;//商铺定格ID
	private String category;//大分类
	private String categoryId;//大分类ID
	private Integer dateTotalQuantity;//本月商品总数量
	private Double dateTotalMoney;//本月商品总金额
	private int status;//订单状态
	private String startTime; //起始时间
	private String endTime;//结束时间
	private String firstDay;//本月的第一天
	private String lastDay;//本月最后一天
	
	
	public String getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}
	public String getLastDay() {
		return lastDay;
	}
	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Double getProductCode() {
		return productCode;
	}
	public void setProductCode(Double productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public Double getProductUnitPrice() {
		return productUnitPrice;
	}
	public void setProductUnitPrice(Double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	public Integer getMonthTotalQuantity() {
		return monthTotalQuantity;
	}
	public void setMonthTotalQuantity(Integer monthTotalQuantity) {
		this.monthTotalQuantity = monthTotalQuantity;
	}
	public Double getMonthTotalMoney() {
		return monthTotalMoney;
	}
	public void setMonthTotalMoney(Double monthTotalMoney) {
		this.monthTotalMoney = monthTotalMoney;
	}
	public String getWholesalerId() {
		return wholesalerId;
	}
	public void setWholesalerId(String wholesalerId) {
		this.wholesalerId = wholesalerId;
	}
	public String getShopRatedName() {
		return shopRatedName;
	}
	public void setShopRatedName(String shopRatedName) {
		this.shopRatedName = shopRatedName;
	}
	public String getShopRatedId() {
		return shopRatedId;
	}
	public void setShopRatedId(String shopRatedId) {
		this.shopRatedId = shopRatedId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getDateTotalQuantity() {
		return dateTotalQuantity;
	}
	public void setDateTotalQuantity(Integer dateTotalQuantity) {
		this.dateTotalQuantity = dateTotalQuantity;
	}
	public Double getDateTotalMoney() {
		return dateTotalMoney;
	}
	public void setDateTotalMoney(Double dateTotalMoney) {
		this.dateTotalMoney = dateTotalMoney;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
