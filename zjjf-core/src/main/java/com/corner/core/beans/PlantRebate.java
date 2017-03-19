package com.corner.core.beans;

import java.util.Date;

public class PlantRebate {
    private Integer id;

    private String rebateName;

    private String rebateContent;

    private Integer ordId;

    private Date addTime;

    private Byte status;

    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRebateName() {
        return rebateName;
    }

    public void setRebateName(String rebateName) {
        this.rebateName = rebateName == null ? null : rebateName.trim();
    }

    public String getRebateContent() {
        return rebateContent;
    }

    public void setRebateContent(String rebateContent) {
        this.rebateContent = rebateContent == null ? null : rebateContent.trim();
    }

    public Integer getOrdId() {
        return ordId;
    }

    public void setOrdId(Integer ordId) {
        this.ordId = ordId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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