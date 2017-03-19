package com.corner.account.beans.vo;

import java.math.BigDecimal;

import com.corner.core.beans.ro.ABaseRo;

public class BillPayVo extends ABaseRo {


	private static final long serialVersionUID = 1L;
	//for all supplier info
	private Integer areaId;
	private String supplierId;
	private String spOrderUUID;
	private String supplierCode;
	private String supplierName;
	private BigDecimal spOrderPrice;
	private BigDecimal spZfee;
	private BigDecimal spZmaoli;
	private String zfeeRate;
	private Integer noStatus;
	private Integer yStatus;
	private Integer optAllStatus;
	
	// for supplier order feee detail 
	private String storeId;
	private String storeName;
	private String spOrderId;
	private BigDecimal spGoodsPrice;
	private Integer spKFStatus;//客服费用审核状态
	private Integer spSPStatus;//供应商费用审核状态
	private Integer spAcStatus;
	private Integer optAcStatus;
	private String sheetNum;
	private String payBankNum;
	
	//to pay page
	private Integer  toPay;//代付款数
	private Integer  inSheet;//已打单数
	private Integer  alReadyPay;//已打单数
	
	
	// old
	private String ackTime;
	private String orderPrice;
	private String zfee;
	private String acRemark;
	private String acStatus;
	private String supplierNam;
	private String supplierTel;
	public String getSupplierNam() {
		return supplierNam;
	}
	public void setSupplierNam(String supplierNam) {
		this.supplierNam = supplierNam;
	}
	public String getSupplierTel() {
		return supplierTel;
	}
	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}
	public String getAcRemark() {
		return acRemark;
	}
	public void setAcRemark(String acRemark) {
		this.acRemark = acRemark;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getAckTime() {
		return ackTime;
	}
	public void setAckTime(String ackTime) {
		this.ackTime = ackTime;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getZfee() {
		return zfee;
	}
	public void setZfee(String zfee) {
		this.zfee = zfee;
	}
	public String getAcStatus() {
		return acStatus;
	}
	public void setAcStatus(String acStatus) {
		this.acStatus = acStatus;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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
	public Integer getNoStatus() {
		return noStatus;
	}
	public void setNoStatus(Integer noStatus) {
		this.noStatus = noStatus;
	}
	public Integer getyStatus() {
		return yStatus;
	}
	public void setyStatus(Integer yStatus) {
		this.yStatus = yStatus;
	}
	public String getZfeeRate() {
		return zfeeRate;
	}
	public void setZfeeRate(String zfeeRate) {
		this.zfeeRate = zfeeRate;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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
	public Integer getOptAllStatus() {
		return this.noStatus;
	}
	public void setOptAllStatus(Integer optAllStatus) {
		this.optAllStatus = optAllStatus;
	}
	public Integer getOptAcStatus() {
		return this.spAcStatus;
	}
	public void setOptAcStatus(Integer optAcStatus) {
		this.optAcStatus = optAcStatus;
	}
	public Integer getSpKFStatus() {
		return spKFStatus;
	}
	public void setSpKFStatus(Integer spKFStatus) {
		this.spKFStatus = spKFStatus;
	}
	public Integer getSpSPStatus() {
		return spSPStatus;
	}
	public void setSpSPStatus(Integer spSPStatus) {
		this.spSPStatus = spSPStatus;
	}
	public Integer getToPay() {
		return toPay;
	}
	public void setToPay(Integer toPay) {
		this.toPay = toPay;
	}
	public Integer getInSheet() {
		return inSheet;
	}
	public void setInSheet(Integer inSheet) {
		this.inSheet = inSheet;
	}
	public Integer getAlReadyPay() {
		return alReadyPay;
	}
	public void setAlReadyPay(Integer alReadyPay) {
		this.alReadyPay = alReadyPay;
	}
	public String getSpOrderUUID() {
		return spOrderUUID;
	}
	public void setSpOrderUUID(String spOrderUUID) {
		this.spOrderUUID = spOrderUUID;
	}
	public String getPayBankNum() {
		return payBankNum;
	}
	public void setPayBankNum(String payBankNum) {
		this.payBankNum = payBankNum;
	}
	public String getSheetNum() {
		return sheetNum;
	}
	public void setSheetNum(String sheetNum) {
		this.sheetNum = sheetNum;
	}
}
