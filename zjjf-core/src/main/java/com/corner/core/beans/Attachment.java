package com.corner.core.beans;

import java.util.Date;

public class Attachment {
    private Integer id;

    private Integer obIntId;

    private String obStrId;

    private String name;

    private String picNum;

    private String picCode;

    private String picRemark;

    private Date picStartDate;

    private Date picEndDate;

    private String picUrl;

    private Short typex;

    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObIntId() {
        return obIntId;
    }

    public void setObIntId(Integer obIntId) {
        this.obIntId = obIntId;
    }

    public String getObStrId() {
        return obStrId;
    }

    public void setObStrId(String obStrId) {
        this.obStrId = obStrId == null ? null : obStrId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPicNum() {
        return picNum;
    }

    public void setPicNum(String picNum) {
        this.picNum = picNum == null ? null : picNum.trim();
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode == null ? null : picCode.trim();
    }

    public String getPicRemark() {
        return picRemark;
    }

    public void setPicRemark(String picRemark) {
        this.picRemark = picRemark == null ? null : picRemark.trim();
    }

    public Date getPicStartDate() {
        return picStartDate;
    }

    public void setPicStartDate(Date picStartDate) {
        this.picStartDate = picStartDate;
    }

    public Date getPicEndDate() {
        return picEndDate;
    }

    public void setPicEndDate(Date picEndDate) {
        this.picEndDate = picEndDate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Short getTypex() {
        return typex;
    }

    public void setTypex(Short typex) {
        this.typex = typex;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}