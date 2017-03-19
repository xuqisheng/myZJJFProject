package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;
import com.corner.core.utils.StringUtil;

public class ScOrderInfo extends AmazeUIListRo {

	private static final long serialVersionUID = 1L;

	private String id = StringUtil.getUUID();
    private String pId;

    private String orderId;

    private String maOrderInfoId;

    private Date addTime;

    private BigDecimal orderPrice = new BigDecimal("0");

	private BigDecimal goodsPrice = new BigDecimal("0");

	private BigDecimal markePrice = new BigDecimal("0");

	private BigDecimal outOfPrice = new BigDecimal("0");

	private BigDecimal rebate;

	private BigDecimal coupon;

	private String couponId;

	private String kfId;

	private String kfName;

	private String kfnote;

	private Byte kfStatus;

	private Date kfCheckTime;

	private String supplierId;

	private String supplierName;

	private String supplierRemark;

	private Integer groupId;

	private String groupName;

	private String consignee;

	private String mobile;

	private String userTel;

	private String address;

	private Byte status;

	private Byte evaluation;

	private Byte supportStatus;

	private Byte supportmetho;

	private Date supportTime;

	private Byte ordertype;

	private String closemsg;

	private Date gaveTime;

	private String managerId;

	private String managerTel;

	private String managerName;

	private Byte spStatus;

	private String spRemark;

	private Date spCheckTime;

	private Date printTime;

	private Date deliveryTime;

	private Date getOrderTime;

	private Date ackTime;

	private Short ackCode;

	private Byte isStar;

	private Date sendDate;

	private Date fistTime;

	private Date endTime;

	private String qrcodeurl;

	private Byte isRemind;

	private BigDecimal zmaoli;

	private BigDecimal zfee;

	private String warehouseId;

	private String warehouseName;

	private Date warehouseTime;

	private String forWxTradeNo;

	private String acId;

	private Integer acStatus;

	private String acRemark;

	private Date acCheckTime;

	private Date acSettleTime;

	private Date acPayTime;

	private Byte level;

	private BigDecimal freight;

	private Byte jsType;

	private Boolean isDelete;

	private Long balandeId;

	private BigDecimal balanceUsedNum;

	private Boolean isUsedBalance;

	private String tradeNo;

	private BigDecimal thirdPaymentNum;

	private BigDecimal thirdPayRealNum;

	private BigDecimal thirdPayFee;

    private Boolean dfFee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getMaOrderInfoId() {
        return maOrderInfoId;
    }

    public void setMaOrderInfoId(String maOrderInfoId) {
        this.maOrderInfoId = maOrderInfoId == null ? null : maOrderInfoId.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getMarkePrice() {
        return markePrice;
    }

    public void setMarkePrice(BigDecimal markePrice) {
        this.markePrice = markePrice;
    }

    public BigDecimal getOutOfPrice() {
        return outOfPrice;
    }

    public void setOutOfPrice(BigDecimal outOfPrice) {
        this.outOfPrice = outOfPrice;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
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

    public Byte getKfStatus() {
        return kfStatus;
    }

    public void setKfStatus(Byte kfStatus) {
        this.kfStatus = kfStatus;
    }

    public Date getKfCheckTime() {
        return kfCheckTime;
    }

    public void setKfCheckTime(Date kfCheckTime) {
        this.kfCheckTime = kfCheckTime;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getSupplierRemark() {
        return supplierRemark;
    }

    public void setSupplierRemark(String supplierRemark) {
        this.supplierRemark = supplierRemark == null ? null : supplierRemark.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Byte evaluation) {
        this.evaluation = evaluation;
    }

    public Byte getSupportStatus() {
        return supportStatus;
    }

    public void setSupportStatus(Byte supportStatus) {
        this.supportStatus = supportStatus;
    }

    public Byte getSupportmetho() {
        return supportmetho;
    }

    public void setSupportmetho(Byte supportmetho) {
        this.supportmetho = supportmetho;
    }

    public Date getSupportTime() {
        return supportTime;
    }

    public void setSupportTime(Date supportTime) {
        this.supportTime = supportTime;
    }

    public Byte getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Byte ordertype) {
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

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    public String getManagerTel() {
        return managerTel;
    }

    public void setManagerTel(String managerTel) {
        this.managerTel = managerTel == null ? null : managerTel.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public Byte getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Byte spStatus) {
        this.spStatus = spStatus;
    }

    public String getSpRemark() {
        return spRemark;
    }

    public void setSpRemark(String spRemark) {
        this.spRemark = spRemark == null ? null : spRemark.trim();
    }

    public Date getSpCheckTime() {
        return spCheckTime;
    }

    public void setSpCheckTime(Date spCheckTime) {
        this.spCheckTime = spCheckTime;
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

    public Date getGetOrderTime() {
        return getOrderTime;
    }

    public void setGetOrderTime(Date getOrderTime) {
        this.getOrderTime = getOrderTime;
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

    public Byte getIsStar() {
        return isStar;
    }

    public void setIsStar(Byte isStar) {
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

    public String getQrcodeurl() {
        return qrcodeurl;
    }

    public void setQrcodeurl(String qrcodeurl) {
        this.qrcodeurl = qrcodeurl == null ? null : qrcodeurl.trim();
    }

    public Byte getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(Byte isRemind) {
        this.isRemind = isRemind;
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

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    public Date getWarehouseTime() {
        return warehouseTime;
    }

    public void setWarehouseTime(Date warehouseTime) {
        this.warehouseTime = warehouseTime;
    }

    public String getForWxTradeNo() {
        return forWxTradeNo;
    }

    public void setForWxTradeNo(String forWxTradeNo) {
        this.forWxTradeNo = forWxTradeNo == null ? null : forWxTradeNo.trim();
    }

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId == null ? null : acId.trim();
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

    public Date getAcCheckTime() {
        return acCheckTime;
    }

    public void setAcCheckTime(Date acCheckTime) {
        this.acCheckTime = acCheckTime;
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

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Byte getJsType() {
        return jsType;
    }

    public void setJsType(Byte jsType) {
        this.jsType = jsType;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Long getBalandeId() {
        return balandeId;
    }

    public void setBalandeId(Long balandeId) {
        this.balandeId = balandeId;
    }

    public BigDecimal getBalanceUsedNum() {
        return balanceUsedNum;
    }

    public void setBalanceUsedNum(BigDecimal balanceUsedNum) {
        this.balanceUsedNum = balanceUsedNum;
    }

    public Boolean getIsUsedBalance() {
        return isUsedBalance;
    }

    public void setIsUsedBalance(Boolean isUsedBalance) {
        this.isUsedBalance = isUsedBalance;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public BigDecimal getThirdPaymentNum() {
        return thirdPaymentNum;
    }

    public void setThirdPaymentNum(BigDecimal thirdPaymentNum) {
        this.thirdPaymentNum = thirdPaymentNum;
    }

    public BigDecimal getThirdPayRealNum() {
        return thirdPayRealNum;
    }

    public void setThirdPayRealNum(BigDecimal thirdPayRealNum) {
        this.thirdPayRealNum = thirdPayRealNum;
    }

    public BigDecimal getThirdPayFee() {
        return thirdPayFee;
    }

    public void setThirdPayFee(BigDecimal thirdPayFee) {
        this.thirdPayFee = thirdPayFee;
    }

    public Boolean getDfFee() {
        return dfFee;
    }

    public void setDfFee(Boolean dfFee) {
        this.dfFee = dfFee;
    }
}