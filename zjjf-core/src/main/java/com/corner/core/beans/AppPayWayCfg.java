package com.corner.core.beans;

public class AppPayWayCfg {
    private Byte id;

    private String name;

    private String paywayList;

    private Byte status;

    private Boolean isDelete;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPaywayList() {
        return paywayList;
    }

    public void setPaywayList(String paywayList) {
        this.paywayList = paywayList == null ? null : paywayList.trim();
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