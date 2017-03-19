package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class SpWithDraw {
    private Long id;

    private Integer storeId;

    private String supplierId;

    private String cardBankName;

    private String cardUserName;

    private String cardNo;

    private BigDecimal amount;

    private Byte status;

    private Boolean isDelete;

    private Date applyTime;

    private String applyRemark;

    private String checkId;

    private Date checkTime;

    private String checkRemark;

    private String denyId;

    private Date denyTime;

    private String denyRemark;

    private String payerId;

    private Date payTime;

    private String payRemark;

    private Byte payState;

    private String bankcode;

    private String bankDealNo;

    private String tradePlant;

    private String tradeNo;

    private BigDecimal payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getCardBankName() {
        return cardBankName;
    }

    public void setCardBankName(String cardBankName) {
        this.cardBankName = cardBankName == null ? null : cardBankName.trim();
    }

    public String getCardUserName() {
        return cardUserName;
    }

    public void setCardUserName(String cardUserName) {
        this.cardUserName = cardUserName == null ? null : cardUserName.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark == null ? null : applyRemark.trim();
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId == null ? null : checkId.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark == null ? null : checkRemark.trim();
    }

    public String getDenyId() {
        return denyId;
    }

    public void setDenyId(String denyId) {
        this.denyId = denyId == null ? null : denyId.trim();
    }

    public Date getDenyTime() {
        return denyTime;
    }

    public void setDenyTime(Date denyTime) {
        this.denyTime = denyTime;
    }

    public String getDenyRemark() {
        return denyRemark;
    }

    public void setDenyRemark(String denyRemark) {
        this.denyRemark = denyRemark == null ? null : denyRemark.trim();
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId == null ? null : payerId.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark == null ? null : payRemark.trim();
    }

    public Byte getPayState() {
        return payState;
    }

    public void setPayState(Byte payState) {
        this.payState = payState;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode == null ? null : bankcode.trim();
    }

    public String getBankDealNo() {
        return bankDealNo;
    }

    public void setBankDealNo(String bankDealNo) {
        this.bankDealNo = bankDealNo == null ? null : bankDealNo.trim();
    }

    public String getTradePlant() {
        return tradePlant;
    }

    public void setTradePlant(String tradePlant) {
        this.tradePlant = tradePlant == null ? null : tradePlant.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
}