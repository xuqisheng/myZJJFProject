package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.beans.SpOrderInfo;


public class SpOrderInfoVo extends SpOrderInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int noStatus;//未审核单数
	private int yStatus;//已审核单数
	private BigDecimal feiyonl;//费用率
	private Integer areaid; //地区
	private String supplierName;
	private String supplierCode;
	
	/*********从会计系统引入字段********/
	//for all supplier info
	private Integer province;
	private Integer city;
	private Integer areaId;
	private String supplierId;
	//private String supplierCode;
	//private String supplierName;
	private BigDecimal spOrderPrice;
	private BigDecimal spZfee;
	private BigDecimal spZmaoli;
	private String zfeeRate;
	//private Integer noStatus;
	//private Integer yStatus;
	private Integer optAllStatus;
	
	// for supplier detail 
	private String storeId;
	private String storeName;
	private String spOrderInfoId;
	private String spOrderId;
	private BigDecimal spGoodsPrice;
	private Integer spAcStatus;
	private Integer optAcStatus;
	//private Integer kfStatus;
	//private Integer spStatus;
	private  Date ackTime;
	
	
	public int getNoStatus() {
		return noStatus;
	}
	public void setNoStatus(int noStatus) {
		this.noStatus = noStatus;
	}
	public int getyStatus() {
		return yStatus;
	}
	public void setyStatus(int yStatus) {
		this.yStatus = yStatus;
	}
	public BigDecimal getFeiyonl() {
		return feiyonl;
	}
	public void setFeiyonl(BigDecimal feiyonl) {
		this.feiyonl = feiyonl;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public BigDecimal getSpOrderPrice() {
		return spOrderPrice;
	}
	public void setSpOrderPrice(BigDecimal spOrderPrice) {
		this.spOrderPrice = spOrderPrice;
	}
	public BigDecimal getSpZfee() {
		return spZfee;
	}
	public void setSpZfee(BigDecimal spZfee) {
		this.spZfee = spZfee;
	}
	public BigDecimal getSpZmaoli() {
		return spZmaoli;
	}
	public void setSpZmaoli(BigDecimal spZmaoli) {
		this.spZmaoli = spZmaoli;
	}
	public String getZfeeRate() {
		return zfeeRate;
	}
	public void setZfeeRate(String zfeeRate) {
		this.zfeeRate = zfeeRate;
	}
	public Integer getOptAllStatus() {
		return optAllStatus;
	}
	public void setOptAllStatus(Integer optAllStatus) {
		this.optAllStatus = optAllStatus;
	}
	
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getSpOrderId() {
		return spOrderId;
	}
	public void setSpOrderId(String spOrderId) {
		this.spOrderId = spOrderId;
	}
	public BigDecimal getSpGoodsPrice() {
		return spGoodsPrice;
	}
	public void setSpGoodsPrice(BigDecimal spGoodsPrice) {
		this.spGoodsPrice = spGoodsPrice;
	}
	public Integer getSpAcStatus() {
		return spAcStatus;
	}
	public void setSpAcStatus(Integer spAcStatus) {
		this.spAcStatus = spAcStatus;
	}
	public Integer getOptAcStatus() {
		return optAcStatus;
	}
	public void setOptAcStatus(Integer optAcStatus) {
		this.optAcStatus = optAcStatus;
	}
	public String getSpOrderInfoId() {
		return spOrderInfoId;
	}
	public void setSpOrderInfoId(String spOrderInfoId) {
		this.spOrderInfoId = spOrderInfoId;
	}
	public Date getAckTime() {
		return ackTime;
	}
	public void setAckTime(Date ackTime) {
		this.ackTime = ackTime;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
 
}
