package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class MaWallet {
    private String id;

    private BigDecimal wallet;

    private Date walletAddTime;

    private Date walletUpdateTime;

    private String remark;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public Date getWalletAddTime() {
        return walletAddTime;
    }

    public void setWalletAddTime(Date walletAddTime) {
        this.walletAddTime = walletAddTime;
    }

    public Date getWalletUpdateTime() {
        return walletUpdateTime;
    }

    public void setWalletUpdateTime(Date walletUpdateTime) {
        this.walletUpdateTime = walletUpdateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}