package com.corner.core.beans;

import java.util.Date;

import com.corner.core.utils.StringUtil;

public class ScmsMinimum {
    private String id = StringUtil.getUUID();

    private Integer brandId;

    private String brandName;

    private String managerId;

    private Short minimum;

    private Date addTime;

    private Date updateTime;

    private String kfId;

    private String kfName;

    private String kfnote;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    public Short getMinimum() {
        return minimum;
    }

    public void setMinimum(Short minimum) {
        this.minimum = minimum;
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

    public String getKfId() {
        return kfId;
    }

    public void setKfId(String kfId) {
        this.kfId = kfId == null ? null : kfId.trim();
    }

    public String getKfName() {
        return kfName;
    }

    public void setKfName(String kfName) {
        this.kfName = kfName == null ? null : kfName.trim();
    }

    public String getKfnote() {
        return kfnote;
    }

    public void setKfnote(String kfnote) {
        this.kfnote = kfnote == null ? null : kfnote.trim();
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