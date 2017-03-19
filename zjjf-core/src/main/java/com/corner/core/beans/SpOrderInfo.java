package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.enums.TradeWay;
import com.corner.core.utils.StringUtil;

public class SpOrderInfo {
    private String id = StringUtil.getUUID();

    private String pId;

    private String orderId;

    private String tradeNo;

    private Date addTime;

    private BigDecimal goodsPrice;

    private BigDecimal orderPrice;

    private BigDecimal itemPrice;

    private BigDecimal rebate;

    private BigDecimal rebatePlat;

    private BigDecimal rebateSp2Sp;

    private BigDecimal rebateSp2Plat;

    private BigDecimal coupon;

    private BigDecimal platCoupon;

    private String couponId;

    private BigDecimal spCoupon;

    private String spCouponId;

    private BigDecimal spCouponPlat;

    private BigDecimal spCouponSp;

    private String kfId;

    private String kfName;

    private String kfnote;

    private Byte kfStatus;

    private Date kfCheckTime;

    private String userId;

    private String userName;

    private String userRemark;

    private Integer storeId;

    private String storeName;

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

    private String supplierId;

    private String supplierTel;

    private String supplierNam;

    private Byte spStatus;

    private String spRemark;

    private Date spCheckTime;

    private Byte warningStatus;

    private Boolean isOutStock;

    private Byte logisticsStatus;

    private Date whSendTime;

    private Date whAckTime;

    private Date printTime;

    private Date deliveryTime;

    private Date getOrderTime;

    private Date ackTime;

    private Date revokeTime;

    private Short ackCode;

    private Byte isStar;

    private Byte sendDateType;

    private Date sendDate;

    private Date fistTime;

    private Date endTime;

    private Boolean isDelete;

    private String qrcodeurl;

    private Byte isRemind;

    private BigDecimal zmaoli;

    private BigDecimal zfee;

    private String acId;

    private Integer acStatus;

    private String acRemark;

    private Date acCheckTime;

    private Date acSettleTime;

    private Date acPayTime;

    private Byte level;

    private BigDecimal freight;

    private String col1;

    private String col2;

    private String col3;

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

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public BigDecimal getRebatePlat() {
        return rebatePlat;
    }

    public void setRebatePlat(BigDecimal rebatePlat) {
        this.rebatePlat = rebatePlat;
    }

    public BigDecimal getRebateSp2Sp() {
        return rebateSp2Sp;
    }

    public void setRebateSp2Sp(BigDecimal rebateSp2Sp) {
        this.rebateSp2Sp = rebateSp2Sp;
    }

    public BigDecimal getRebateSp2Plat() {
        return rebateSp2Plat;
    }

    public void setRebateSp2Plat(BigDecimal rebateSp2Plat) {
        this.rebateSp2Plat = rebateSp2Plat;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    public BigDecimal getPlatCoupon() {
        return platCoupon;
    }

    public void setPlatCoupon(BigDecimal platCoupon) {
        this.platCoupon = platCoupon;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public BigDecimal getSpCoupon() {
        return spCoupon;
    }

    public void setSpCoupon(BigDecimal spCoupon) {
        this.spCoupon = spCoupon;
    }

    public String getSpCouponId() {
        return spCouponId;
    }

    public void setSpCouponId(String spCouponId) {
        this.spCouponId = spCouponId == null ? null : spCouponId.trim();
    }

    public BigDecimal getSpCouponPlat() {
        return spCouponPlat;
    }

    public void setSpCouponPlat(BigDecimal spCouponPlat) {
        this.spCouponPlat = spCouponPlat;
    }

    public BigDecimal getSpCouponSp() {
        return spCouponSp;
    }

    public void setSpCouponSp(BigDecimal spCouponSp) {
        this.spCouponSp = spCouponSp;
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
    public String getSupportmethoStr() {
        return TradeWay.getName(supportmetho.intValue());
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

    public Byte getWarningStatus() {
        return warningStatus;
    }

    public void setWarningStatus(Byte warningStatus) {
        this.warningStatus = warningStatus;
    }

    public Boolean getIsOutStock() {
        return isOutStock;
    }

    public void setIsOutStock(Boolean isOutStock) {
        this.isOutStock = isOutStock;
    }

    public Byte getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(Byte logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public Date getWhSendTime() {
        return whSendTime;
    }

    public void setWhSendTime(Date whSendTime) {
        this.whSendTime = whSendTime;
    }

    public Date getWhAckTime() {
        return whAckTime;
    }

    public void setWhAckTime(Date whAckTime) {
        this.whAckTime = whAckTime;
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

    public Date getRevokeTime() {
        return revokeTime;
    }

    public void setRevokeTime(Date revokeTime) {
        this.revokeTime = revokeTime;
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

    public Byte getSendDateType() {
        return sendDateType;
    }

    public void setSendDateType(Byte sendDateType) {
        this.sendDateType = sendDateType;
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
}