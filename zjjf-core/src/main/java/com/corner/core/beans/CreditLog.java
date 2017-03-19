package com.corner.core.beans;

import java.util.Date;

import com.corner.core.utils.StringUtil;

public class CreditLog {
    private String id=StringUtil.getUUID();

    private String userId;

    private String action;

    private Integer credIt;

    private Date addTime;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public Integer getCredIt() {
        return credIt;
    }

    public void setCredIt(Integer credIt) {
        this.credIt = credIt;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}