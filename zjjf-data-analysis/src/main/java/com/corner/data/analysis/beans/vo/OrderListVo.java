package com.corner.data.analysis.beans.vo;

import com.corner.core.beans.ro.ABaseRo;

public class OrderListVo extends ABaseRo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String areaName;//区域名称
	private String addTime;//下单时间
	private String orderId;//父订单号
	private String orderId2;//订单号
	private String storeName;//商铺名称
	private String consignee;//商铺联系人
	private String mobile;//商铺电话
	private String address;//商铺地址
	
	private String goodsPrice;//商品价格
	private String orderPrice;//订单价格
	private String barCode;//商品编码
	private String brand;//品牌
	private String productId;//商品ID
	private String productName;//商品名称
	private String spec;//商品规格
	
	private String price;//商品单价
	private String quantity;//商品数量
	private String totalPrice;//商品金额
	private String zmaoli;//总毛利
	private String supplierId;//批发商ID
	private String spGroupId;//商铺ID
	private String spGroupName;//商铺定格
	
	private String smCategory;//小分类
	private String bgCategory;//大分类
	private String status;//订单状态
	private String supportmetho;//支付状态
	private String coupon;//优惠券金额
	private String rebate;//满减
	
	private String startTime;//查询开始时间
	private String endTime;//查询结束时间
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderId2() {
		return orderId2;
	}
	public void setOrderId2(String orderId2) {
		this.orderId2 = orderId2;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getZmaoli() {
		return zmaoli;
	}
	public void setZmaoli(String zmaoli) {
		this.zmaoli = zmaoli;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSpGroupId() {
		return spGroupId;
	}
	public void setSpGroupId(String spGroupId) {
		this.spGroupId = spGroupId;
	}
	public String getSpGroupName() {
		return spGroupName;
	}
	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}
	public String getSmCategory() {
		return smCategory;
	}
	public void setSmCategory(String smCategory) {
		this.smCategory = smCategory;
	}
	public String getBgCategory() {
		return bgCategory;
	}
	public void setBgCategory(String bgCategory) {
		this.bgCategory = bgCategory;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSupportmetho() {
		return supportmetho;
	}
	public void setSupportmetho(String supportmetho) {
		this.supportmetho = supportmetho;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	
}
