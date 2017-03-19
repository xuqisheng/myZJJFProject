package com.corner.core.beans;

import java.util.Date;

public class AppItemTag {
    private String id;

    private String name;

    private String remark;

    private Byte weekDay;

    private Date beginTime;

    private Date stopTime;

    private String appModuleId;

    private String detailPosition;

    private Boolean canClick;

    private String tagId1;

    private String tagId2;

    private String tagId3;

    private String title1;

    private String color1;

    private String title2;

    private String color2;

    private String title3;

    private String color3;

    private String picUrl;

    private Byte skipType;

    private String skipObjectId;

    private Integer adBoardId;

    private Integer orderId;

    private Date addTime;

    private Date updateTime;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Byte weekDay) {
        this.weekDay = weekDay;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getAppModuleId() {
        return appModuleId;
    }

    public void setAppModuleId(String appModuleId) {
        this.appModuleId = appModuleId == null ? null : appModuleId.trim();
    }

    public String getDetailPosition() {
        return detailPosition;
    }

    public void setDetailPosition(String detailPosition) {
        this.detailPosition = detailPosition == null ? null : detailPosition.trim();
    }

    public Boolean getCanClick() {
        return canClick;
    }

    public void setCanClick(Boolean canClick) {
        this.canClick = canClick;
    }

    public String getTagId1() {
        return tagId1;
    }

    public void setTagId1(String tagId1) {
        this.tagId1 = tagId1 == null ? null : tagId1.trim();
    }

    public String getTagId2() {
        return tagId2;
    }

    public void setTagId2(String tagId2) {
        this.tagId2 = tagId2 == null ? null : tagId2.trim();
    }

    public String getTagId3() {
        return tagId3;
    }

    public void setTagId3(String tagId3) {
        this.tagId3 = tagId3 == null ? null : tagId3.trim();
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1 == null ? null : title1.trim();
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1 == null ? null : color1.trim();
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2 == null ? null : title2.trim();
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2 == null ? null : color2.trim();
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3 == null ? null : title3.trim();
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3 == null ? null : color3.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Byte getSkipType() {
        return skipType;
    }

    public void setSkipType(Byte skipType) {
        this.skipType = skipType;
    }

    public String getSkipObjectId() {
        return skipObjectId;
    }

    public void setSkipObjectId(String skipObjectId) {
        this.skipObjectId = skipObjectId == null ? null : skipObjectId.trim();
    }

    public Integer getAdBoardId() {
        return adBoardId;
    }

    public void setAdBoardId(Integer adBoardId) {
        this.adBoardId = adBoardId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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