package com.corner.core.beans;

import java.util.Date;

public class SpFeedback {
    private Long id;

    private Integer storeId;

    private String title;

    private String content;

    private Date addTime;

    private Byte sendUser;

    private String checkerId;

    private String checkerNm;

    private Byte status;

    private Boolean isDelete;

    private Date confirmTime;

    private Byte deviceType;

    private String appVersion;

    private String tel;

    private String email;

    private Byte questionType;

    private Boolean picNumber;

    private String picPaths;

    private Byte revertStatus;

    private String kfContent;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Byte getSendUser() {
        return sendUser;
    }

    public void setSendUser(Byte sendUser) {
        this.sendUser = sendUser;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId == null ? null : checkerId.trim();
    }

    public String getCheckerNm() {
        return checkerNm;
    }

    public void setCheckerNm(String checkerNm) {
        this.checkerNm = checkerNm == null ? null : checkerNm.trim();
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

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Byte getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Byte deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Byte questionType) {
        this.questionType = questionType;
    }

    public Boolean getPicNumber() {
        return picNumber;
    }

    public void setPicNumber(Boolean picNumber) {
        this.picNumber = picNumber;
    }

    public String getPicPaths() {
        return picPaths;
    }

    public void setPicPaths(String picPaths) {
        this.picPaths = picPaths == null ? null : picPaths.trim();
    }

    public Byte getRevertStatus() {
        return revertStatus;
    }

    public void setRevertStatus(Byte revertStatus) {
        this.revertStatus = revertStatus;
    }

    public String getKfContent() {
        return kfContent;
    }

    public void setKfContent(String kfContent) {
        this.kfContent = kfContent == null ? null : kfContent.trim();
    }
}