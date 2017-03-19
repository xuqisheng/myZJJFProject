package com.corner.core.beans;

import java.util.Date;

public class ShareDisseminateLog {
    private String id;

    private Integer sendType;

    private String sendUserId;

    private Byte encourageType;

    private String encourageVoucher;

    private Integer acceptUserId;

    private String acceptPlaceName;

    private String acceptTel;

    private Date regTime;

    private Date confirmTime;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId == null ? null : sendUserId.trim();
    }

    public Byte getEncourageType() {
        return encourageType;
    }

    public void setEncourageType(Byte encourageType) {
        this.encourageType = encourageType;
    }

    public String getEncourageVoucher() {
        return encourageVoucher;
    }

    public void setEncourageVoucher(String encourageVoucher) {
        this.encourageVoucher = encourageVoucher == null ? null : encourageVoucher.trim();
    }

    public Integer getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(Integer acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public String getAcceptPlaceName() {
        return acceptPlaceName;
    }

    public void setAcceptPlaceName(String acceptPlaceName) {
        this.acceptPlaceName = acceptPlaceName == null ? null : acceptPlaceName.trim();
    }

    public String getAcceptTel() {
        return acceptTel;
    }

    public void setAcceptTel(String acceptTel) {
        this.acceptTel = acceptTel == null ? null : acceptTel.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}