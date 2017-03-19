package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class FinWalletLog {
    private String id;

    private Byte paySideType;

    private String payerWalletId;

    private BigDecimal payerLastBalance;

    private Byte getSideType;

    private String geterWalletId;

    private BigDecimal geterLastBalance;

    private BigDecimal amount;

    private Integer subjectType;

    private Date addTime;

    private Integer tradeWay;

    private Integer systemCode;

    private Integer businessType;

    private String voucherMain;

    private String voucherSub;

    private String couponId;

    private String tradeNo;

    private Byte purpose;

    private String businessLogNo;

    private Boolean isDelete;

    private String remark;

    private Long sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getPaySideType() {
        return paySideType;
    }

    public void setPaySideType(Byte paySideType) {
        this.paySideType = paySideType;
    }

    public String getPayerWalletId() {
        return payerWalletId;
    }

    public void setPayerWalletId(String payerWalletId) {
        this.payerWalletId = payerWalletId == null ? null : payerWalletId.trim();
    }

    public BigDecimal getPayerLastBalance() {
        return payerLastBalance;
    }

    public void setPayerLastBalance(BigDecimal payerLastBalance) {
        this.payerLastBalance = payerLastBalance;
    }

    public Byte getGetSideType() {
        return getSideType;
    }

    public void setGetSideType(Byte getSideType) {
        this.getSideType = getSideType;
    }

    public String getGeterWalletId() {
        return geterWalletId;
    }

    public void setGeterWalletId(String geterWalletId) {
        this.geterWalletId = geterWalletId == null ? null : geterWalletId.trim();
    }

    public BigDecimal getGeterLastBalance() {
        return geterLastBalance;
    }

    public void setGeterLastBalance(BigDecimal geterLastBalance) {
        this.geterLastBalance = geterLastBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getTradeWay() {
        return tradeWay;
    }

    public void setTradeWay(Integer tradeWay) {
        this.tradeWay = tradeWay;
    }

    public Integer getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(Integer systemCode) {
        this.systemCode = systemCode;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getVoucherMain() {
        return voucherMain;
    }

    public void setVoucherMain(String voucherMain) {
        this.voucherMain = voucherMain == null ? null : voucherMain.trim();
    }

    public String getVoucherSub() {
        return voucherSub;
    }

    public void setVoucherSub(String voucherSub) {
        this.voucherSub = voucherSub == null ? null : voucherSub.trim();
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public Byte getPurpose() {
        return purpose;
    }

    public void setPurpose(Byte purpose) {
        this.purpose = purpose;
    }

    public String getBusinessLogNo() {
        return businessLogNo;
    }

    public void setBusinessLogNo(String businessLogNo) {
        this.businessLogNo = businessLogNo == null ? null : businessLogNo.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}