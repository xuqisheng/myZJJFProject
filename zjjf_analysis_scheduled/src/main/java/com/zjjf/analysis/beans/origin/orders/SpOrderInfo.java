package com.zjjf.analysis.beans.origin.orders;

import java.math.BigDecimal;
import java.util.Date;

public class SpOrderInfo {
	private String id;

	private String orderId;

	private String tradeNo;

	private Date addTime;

	private BigDecimal goodsPrice;

	private BigDecimal orderPrice;

	private String kfId;

	private String kfName;

	private String kfnote;

	private String userId;

	private String userName;

	private String userRemark;

	private Integer storeId;

	private String storeName;

	private String consignee;

	private String mobile;

	private String userTel;

	private String address;

	private Integer status;

	private Integer supportmetho;

	private Integer ordertype;

	private String closemsg;

	private Date gaveTime;

	private Date supportTime;

	private String supplierId;

	private String supplierTel;

	private String supplierNam;

	private Date printTime;

	private String printTimes;

	private Date deliveryTime;

	private Date ackTime;

	private Short ackCode;

	private Integer isStar;

	private Date sendDate;

	private Date fistTime;

	private Date endTime;

	private Boolean isDelete;

	private String qrcodeurl;

	private Integer isRemind;

	private String col1;

	private String col2;

	private String col3;

	private Date getOrderTime;

	private BigDecimal zmaoli;

	private BigDecimal zfee;

	private Integer acStatus;

	private String acRemark;

	private Integer kfStatus;

	private Date kfCheckTime;

	private Date acSettleTime;

	private Date acPayTime;

	private Integer spStatus;

	private Date spCheckTime;

	private String spRemark;

	private String acId;

	private Date acCheckTime;

	private BigDecimal rebate;

	private Integer supportStatus;

	private Integer level;

	private String pId;

	private BigDecimal freight;

	private Integer evaluation;

	private BigDecimal coupon;

	private String couponId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo == null ? null : tradeNo.trim();
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getKfId() {
		return kfId;
	}

	public void setKfId(String kfId) {
		this.kfId = kfId == null ? null : kfId.trim();
	}

	public String getKfName() {
		return kfName;
	}

	public void setKfName(String kfName) {
		this.kfName = kfName == null ? null : kfName.trim();
	}

	public String getKfnote() {
		return kfnote;
	}

	public void setKfnote(String kfnote) {
		this.kfnote = kfnote == null ? null : kfnote.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark == null ? null : userRemark.trim();
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName == null ? null : storeName.trim();
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee == null ? null : consignee.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel == null ? null : userTel.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSupportmetho() {
		return supportmetho;
	}

	public void setSupportmetho(Integer supportmetho) {
		this.supportmetho = supportmetho;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public String getClosemsg() {
		return closemsg;
	}

	public void setClosemsg(String closemsg) {
		this.closemsg = closemsg == null ? null : closemsg.trim();
	}

	public Date getGaveTime() {
		return gaveTime;
	}

	public void setGaveTime(Date gaveTime) {
		this.gaveTime = gaveTime;
	}

	public Date getSupportTime() {
		return supportTime;
	}

	public void setSupportTime(Date supportTime) {
		this.supportTime = supportTime;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId == null ? null : supplierId.trim();
	}

	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel == null ? null : supplierTel.trim();
	}

	public String getSupplierNam() {
		return supplierNam;
	}

	public void setSupplierNam(String supplierNam) {
		this.supplierNam = supplierNam == null ? null : supplierNam.trim();
	}

	public Date getPrintTime() {
		return printTime;
	}

	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getAckTime() {
		return ackTime;
	}

	public void setAckTime(Date ackTime) {
		this.ackTime = ackTime;
	}

	public Short getAckCode() {
		return ackCode;
	}

	public void setAckCode(Short ackCode) {
		this.ackCode = ackCode;
	}

	public Integer getIsStar() {
		return isStar;
	}

	public void setIsStar(Integer isStar) {
		this.isStar = isStar;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getFistTime() {
		return fistTime;
	}

	public void setFistTime(Date fistTime) {
		this.fistTime = fistTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getQrcodeurl() {
		return qrcodeurl;
	}

	public void setQrcodeurl(String qrcodeurl) {
		this.qrcodeurl = qrcodeurl == null ? null : qrcodeurl.trim();
	}

	public Integer getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(Integer isRemind) {
		this.isRemind = isRemind;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1 == null ? null : col1.trim();
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2 == null ? null : col2.trim();
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3 == null ? null : col3.trim();
	}

	public Date getGetOrderTime() {
		return getOrderTime;
	}

	public void setGetOrderTime(Date getOrderTime) {
		this.getOrderTime = getOrderTime;
	}

	public BigDecimal getZmaoli() {
		return zmaoli;
	}

	public void setZmaoli(BigDecimal zmaoli) {
		this.zmaoli = zmaoli;
	}

	public BigDecimal getZfee() {
		return zfee;
	}

	public void setZfee(BigDecimal zfee) {
		this.zfee = zfee;
	}

	public Integer getAcStatus() {
		return acStatus;
	}

	public void setAcStatus(Integer acStatus) {
		this.acStatus = acStatus;
	}

	public String getAcRemark() {
		return acRemark;
	}

	public void setAcRemark(String acRemark) {
		this.acRemark = acRemark == null ? null : acRemark.trim();
	}

	public Integer getKfStatus() {
		return kfStatus;
	}

	public void setKfStatus(Integer kfStatus) {
		this.kfStatus = kfStatus;
	}

	public Date getKfCheckTime() {
		return kfCheckTime;
	}

	public void setKfCheckTime(Date kfCheckTime) {
		this.kfCheckTime = kfCheckTime;
	}

	public Date getAcSettleTime() {
		return acSettleTime;
	}

	public void setAcSettleTime(Date acSettleTime) {
		this.acSettleTime = acSettleTime;
	}

	public Date getAcPayTime() {
		return acPayTime;
	}

	public void setAcPayTime(Date acPayTime) {
		this.acPayTime = acPayTime;
	}

	public Integer getSpStatus() {
		return spStatus;
	}

	public void setSpStatus(Integer spStatus) {
		this.spStatus = spStatus;
	}

	public Date getSpCheckTime() {
		return spCheckTime;
	}

	public void setSpCheckTime(Date spCheckTime) {
		this.spCheckTime = spCheckTime;
	}

	public String getSpRemark() {
		return spRemark;
	}

	public void setSpRemark(String spRemark) {
		this.spRemark = spRemark == null ? null : spRemark.trim();
	}

	public String getAcId() {
		return acId;
	}

	public void setAcId(String acId) {
		this.acId = acId == null ? null : acId.trim();
	}

	public Date getAcCheckTime() {
		return acCheckTime;
	}

	public void setAcCheckTime(Date acCheckTime) {
		this.acCheckTime = acCheckTime;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public Integer getSupportStatus() {
		return supportStatus;
	}

	public void setSupportStatus(Integer supportStatus) {
		this.supportStatus = supportStatus;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId == null ? null : pId.trim();
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}

	public BigDecimal getCoupon() {
		return coupon;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId == null ? null : couponId.trim();
	}

	public String getPrintTimes() {
		return printTimes;
	}

	public void setPrintTimes(String printTimes) {
		this.printTimes = printTimes;
	}

}