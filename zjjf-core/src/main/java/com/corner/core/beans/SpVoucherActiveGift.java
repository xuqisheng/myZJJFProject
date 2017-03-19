package com.corner.core.beans;

import com.corner.core.utils.StringUtil;

public class SpVoucherActiveGift {
    private String id = StringUtil.getUUID();

    private Integer spVoucherActiveId;

    private String supplierId;

    private String plantItemId;

    private Integer number;

    private Integer count;

    private Integer itemBaseId;

    private Integer buyCount;

    private Integer type;

    private String itemBaseName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSpVoucherActiveId() {
        return spVoucherActiveId;
    }

    public void setSpVoucherActiveId(Integer spVoucherActiveId) {
        this.spVoucherActiveId = spVoucherActiveId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getPlantItemId() {
        return plantItemId;
    }

    public void setPlantItemId(String plantItemId) {
        this.plantItemId = plantItemId == null ? null : plantItemId.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getItemBaseId() {
        return itemBaseId;
    }

    public void setItemBaseId(Integer itemBaseId) {
        this.itemBaseId = itemBaseId;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getItemBaseName() {
        return itemBaseName;
    }

    public void setItemBaseName(String itemBaseName) {
        this.itemBaseName = itemBaseName == null ? null : itemBaseName.trim();
    }
}