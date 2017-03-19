package com.corner.task.beans;

import java.util.Date;

public class SKUActivePublishTagMap {
    private String id;

    private String SKUActiveId;

    private String publishTagId;

    private Integer status;

    private Integer isDelete;

    private Date addTime;

    private String addUser;

    private Date delTime;

    private String delUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSKUActiveId() {
        return SKUActiveId;
    }

    public void setSKUActiveId(String SKUActiveId) {
        this.SKUActiveId = SKUActiveId == null ? null : SKUActiveId.trim();
    }

    public String getPublishTagId() {
        return publishTagId;
    }

    public void setPublishTagId(String publishTagId) {
        this.publishTagId = publishTagId == null ? null : publishTagId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser == null ? null : addUser.trim();
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDelUser() {
        return delUser;
    }

    public void setDelUser(String delUser) {
        this.delUser = delUser == null ? null : delUser.trim();
    }
}