package com.corner.core.beans;

import com.corner.core.utils.StringUtil;

public class SpVoucherActiveMiddle {
    private String id = StringUtil.getUUID();

    private String storeGroupID;

    private String supplierId;

    private String scmsItemId;

    private Integer number;

    private Double money;

    private Integer spVoucherActiveId;

    private Boolean isDelete;

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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getScmsItemId() {
        return scmsItemId;
    }

    public void setScmsItemId(String scmsItemId) {
        this.scmsItemId = scmsItemId == null ? null : scmsItemId.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getSpVoucherActiveId() {
        return spVoucherActiveId;
    }

    public void setSpVoucherActiveId(Integer spVoucherActiveId) {
        this.spVoucherActiveId = spVoucherActiveId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}