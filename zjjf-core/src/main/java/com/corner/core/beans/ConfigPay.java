package com.corner.core.beans;

import java.util.Date;

public class ConfigPay {
    private Integer id;

    private Integer configId;

    private Byte payType;

    private Byte selectType;

    private String spGroupIds;

    private Date addTime;

    private Date updateTime;

    private Byte status;

    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Byte getSelectType() {
        return selectType;
    }

    public void setSelectType(Byte selectType) {
        this.selectType = selectType;
    }

    public String getSpGroupIds() {
        return spGroupIds;
    }

    public void setSpGroupIds(String spGroupIds) {
        this.spGroupIds = spGroupIds == null ? null : spGroupIds.trim();
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