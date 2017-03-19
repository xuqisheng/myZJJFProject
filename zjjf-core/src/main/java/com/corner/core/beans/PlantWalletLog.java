package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class PlantWalletLog {
    private Long id;

    private Byte payer;

    private Byte geter;

    private String plantWalletId;

    private Byte actionType;

    private Byte optType;

    private Date actionTime;

    private Byte tradeWay;

    private String tradeNo;

    private String bankCode;

    private String bankNo;

    private String orderId;

    private Long spWithDrawId;

    private String sheetId;

    private String couponId;

    private String remark;

    private BigDecimal money;

    private BigDecimal balance;

    private Boolean isDelete;

    private String orderPId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getPayer() {
        return payer;
    }

    public void setPayer(Byte payer) {
        this.payer = payer;
    }

    public Byte getGeter() {
        return geter;
    }

    public void setGeter(Byte geter) {
        this.geter = geter;
    }

    public String getPlantWalletId() {
        return plantWalletId;
    }

    public void setPlantWalletId(String plantWalletId) {
        this.plantWalletId = plantWalletId == null ? null : plantWalletId.trim();
    }

    public Byte getActionType() {
        return actionType;
    }

    public void setActionType(Byte actionType) {
        this.actionType = actionType;
    }

    public Byte getOptType() {
        return optType;
    }

    public void setOptType(Byte optType) {
        this.optType = optType;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public Byte getTradeWay() {
        return tradeWay;
    }

    public void setTradeWay(Byte tradeWay) {
        this.tradeWay = tradeWay;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getSpWithDrawId() {
        return spWithDrawId;
    }

    public void setSpWithDrawId(Long spWithDrawId) {
        this.spWithDrawId = spWithDrawId;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId == null ? null : sheetId.trim();
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getOrderPId() {
        return orderPId;
    }

    public void setOrderPId(String orderPId) {
        this.orderPId = orderPId == null ? null : orderPId.trim();
    }
}