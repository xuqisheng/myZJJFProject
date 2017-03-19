package com.corner.core.beans;

public class DBAppConfig {
    private Integer id;

    private String name;

    private Byte payCfgId;

    private String paywayList;

    private Byte status;

    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getPayCfgId() {
        return payCfgId;
    }

    public void setPayCfgId(Byte payCfgId) {
        this.payCfgId = payCfgId;
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