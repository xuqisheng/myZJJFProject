package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class MaWalletLog {
    private Long id;

    private Byte payer;

    private Byte geter;

    private String maId;

    private Byte actionType;

    private Byte optType;

    private Date actionTime;

    private Byte tradeWay;

    private String tradeNo;

    private String bankCode;

    private String bankNo;

    private String orderId;

    private String orderNo;

    private Long maWithDrawId;

    private String sheetId;

    private String couponId;

    private String remark;

    private BigDecimal money;

    private BigDecimal balance;

    private Boolean isDelete;

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

    public String getMaId() {
        return maId;
    }

    public void setMaId(String maId) {
        this.maId = maId == null ? null : maId.trim();
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getMaWithDrawId() {
        return maWithDrawId;
    }

    public void setMaWithDrawId(Long maWithDrawId) {
        this.maWithDrawId = maWithDrawId;
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
}