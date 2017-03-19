package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.utils.StringUtil;

public class OrderInfo {
    private String id=StringUtil.getUUID();

    private String orderId;

    private String tradeNo;

    private Date addTime;

    private BigDecimal goodsPrice;

    private BigDecimal orderPrice;

    private String userId;

    private String userName;

    private String consignee;

    private String mobile;

    private String address;

    private Byte status;

    private Byte supportmetho;

    private String closemsg;

    private Date supportTime;

    private Date deliveryTime;

    private Date ackTime;

    private Short ackCode;

    private Integer storeId;

    private String storeName;

    private Byte isStar;

    private Byte isFirst;

    private Date fistTime;

    private Date endTime;

    private Boolean isDelete;

    private String col1;

    private String col2;

    private String col3;

    private String col4;

    private String col5;

    private String clo6;

    private String clo7;

    private String clo8;

    private String clo9;

    private String clo10;

    private Byte isRemind;

    private String qrcodeurl;

    private Byte ordertype;

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

    public Byte getSupportmetho() {
        return supportmetho;
    }

    public void setSupportmetho(Byte supportmetho) {
        this.supportmetho = supportmetho;
    }

    public String getClosemsg() {
        return closemsg;
    }

    public void setClosemsg(String closemsg) {
        this.closemsg = closemsg == null ? null : closemsg.trim();
    }

    public Date getSupportTime() {
        return supportTime;
    }

    public void setSupportTime(Date supportTime) {
        this.supportTime = supportTime;
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

    public Byte getIsStar() {
        return isStar;
    }

    public void setIsStar(Byte isStar) {
        this.isStar = isStar;
    }

    public Byte getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Byte isFirst) {
        this.isFirst = isFirst;
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

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4 == null ? null : col4.trim();
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5 == null ? null : col5.trim();
    }

    public String getClo6() {
        return clo6;
    }

    public void setClo6(String clo6) {
        this.clo6 = clo6 == null ? null : clo6.trim();
    }

    public String getClo7() {
        return clo7;
    }

    public void setClo7(String clo7) {
        this.clo7 = clo7 == null ? null : clo7.trim();
    }

    public String getClo8() {
        return clo8;
    }

    public void setClo8(String clo8) {
        this.clo8 = clo8 == null ? null : clo8.trim();
    }

    public String getClo9() {
        return clo9;
    }

    public void setClo9(String clo9) {
        this.clo9 = clo9 == null ? null : clo9.trim();
    }

    public String getClo10() {
        return clo10;
    }

    public void setClo10(String clo10) {
        this.clo10 = clo10 == null ? null : clo10.trim();
    }

    public Byte getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(Byte isRemind) {
        this.isRemind = isRemind;
    }

    public String getQrcodeurl() {
        return qrcodeurl;
    }

    public void setQrcodeurl(String qrcodeurl) {
        this.qrcodeurl = qrcodeurl == null ? null : qrcodeurl.trim();
    }

    public Byte getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Byte ordertype) {
        this.ordertype = ordertype;
    }
}