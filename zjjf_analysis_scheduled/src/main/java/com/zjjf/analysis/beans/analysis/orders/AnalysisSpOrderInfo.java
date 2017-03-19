package com.zjjf.analysis.beans.analysis.orders;

import java.math.BigDecimal;
import java.util.Date;

import com.zjjf.analysis.beans.origin.ParamMapVo;

public class AnalysisSpOrderInfo extends ParamMapVo {

	private Integer id;

	private String pid;

	private String org_pk_id;

	private Integer cityId;

	private Integer areaId;

	private Integer storeId;

	private Integer spGroupId;

	private String addTime;

	private String gaveTime;

	private String orderNo;

	private String chirdOrderNo;

	private Integer quantity;

	private Integer sku;

	private BigDecimal goodsPrice;

	private BigDecimal freight;

	private BigDecimal coupon;

	private BigDecimal rebate;

	private BigDecimal orderPrice;

	private BigDecimal itemPrice;

	private BigDecimal fee;

	private Integer supportmetho;

	private Integer supportStatus;

	private String supplierId;

	private Integer status;

	private Date updateTime;

	private String ackTime;

	private Integer createTime;

	private BigDecimal payMoney;

	private Integer isZj;

	private Integer dayTime;

	private String storeCode;

	private String supplierCode;

	private String printTime;
	
	private Integer isKpi = 1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrg_pk_id() {
		return org_pk_id;
	}

	public void setOrg_pk_id(String org_pk_id) {
		this.org_pk_id = org_pk_id;
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

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime == null ? null : addTime.trim();
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public String getChirdOrderNo() {
		return chirdOrderNo;
	}

	public void setChirdOrderNo(String chirdOrderNo) {
		this.chirdOrderNo = chirdOrderNo == null ? null : chirdOrderNo.trim();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getCoupon() {
		return coupon;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getSupportmetho() {
		return supportmetho;
	}

	public void setSupportmetho(Integer supportmetho) {
		this.supportmetho = supportmetho;
	}

	public Integer getSupportStatus() {
		return supportStatus;
	}

	public void setSupportStatus(Integer supportStatus) {
		this.supportStatus = supportStatus;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId == null ? null : supplierId.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAckTime() {
		return ackTime;
	}

	public void setAckTime(String ackTime) {
		this.ackTime = ackTime;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getIsZj() {
		return isZj;
	}

	public void setIsZj(Integer isZj) {
		this.isZj = isZj;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Integer getDayTime() {
		return dayTime;
	}

	public void setDayTime(Integer dayTime) {
		this.dayTime = dayTime;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getGaveTime() {
		return gaveTime;
	}

	public void setGaveTime(String gaveTime) {
		this.gaveTime = gaveTime;
	}

	public String getPrintTime() {
		return printTime;
	}

	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}

	public Integer getIsKpi() {
		return isKpi;
	}

	public void setIsKpi(Integer isKpi) {
		this.isKpi = isKpi;
	}
}