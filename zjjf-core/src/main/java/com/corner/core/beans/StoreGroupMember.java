package com.corner.core.beans;

import com.corner.core.utils.StringUtil;

public class StoreGroupMember {
    private String id=StringUtil.getUUID();

    private String storeGroupID;

    private Integer type;

    private Integer storeId;

    private String supplierId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStoreGroupID() {
        return storeGroupID;
    }

    public void setStoreGroupID(String storeGroupID) {
        this.storeGroupID = storeGroupID == null ? null : storeGroupID.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }
}