package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class FinWallet {
    private String id;

    private Float rates;

    private BigDecimal balance;

    private BigDecimal unliquidatedMoney;

    private BigDecimal lastInMoney;

    private String lastInVoucher;

    private BigDecimal lastOutMoney;

    private String lastOutVoucher;

    private Date addTime;

    private Date lastOpTime;

    private Date lastInTime;

    private Date lastOutTime;

    private String payPassword;

    private Byte status;

    private Boolean isDelete;

    private Byte pwdErrorCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Float getRates() {
        return rates;
    }

    public void setRates(Float rates) {
        this.rates = rates;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getUnliquidatedMoney() {
        return unliquidatedMoney;
    }

    public void setUnliquidatedMoney(BigDecimal unliquidatedMoney) {
        this.unliquidatedMoney = unliquidatedMoney;
    }

    public BigDecimal getLastInMoney() {
        return lastInMoney;
    }

    public void setLastInMoney(BigDecimal lastInMoney) {
        this.lastInMoney = lastInMoney;
    }

    public String getLastInVoucher() {
        return lastInVoucher;
    }

    public void setLastInVoucher(String lastInVoucher) {
        this.lastInVoucher = lastInVoucher == null ? null : lastInVoucher.trim();
    }

    public BigDecimal getLastOutMoney() {
        return lastOutMoney;
    }

    public void setLastOutMoney(BigDecimal lastOutMoney) {
        this.lastOutMoney = lastOutMoney;
    }

    public String getLastOutVoucher() {
        return lastOutVoucher;
    }

    public void setLastOutVoucher(String lastOutVoucher) {
        this.lastOutVoucher = lastOutVoucher == null ? null : lastOutVoucher.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getLastOpTime() {
        return lastOpTime;
    }

    public void setLastOpTime(Date lastOpTime) {
        this.lastOpTime = lastOpTime;
    }

    public Date getLastInTime() {
        return lastInTime;
    }

    public void setLastInTime(Date lastInTime) {
        this.lastInTime = lastInTime;
    }

    public Date getLastOutTime() {
        return lastOutTime;
    }

    public void setLastOutTime(Date lastOutTime) {
        this.lastOutTime = lastOutTime;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
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

    public Byte getPwdErrorCount() {
        return pwdErrorCount;
    }

    public void setPwdErrorCount(Byte pwdErrorCount) {
        this.pwdErrorCount = pwdErrorCount;
    }
}