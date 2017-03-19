package com.corner.core.beans;

public class AppItemLabelMap extends AppItemLabelMapKey {
    private String SKUActiveId;

    public String getSKUActiveId() {
        return SKUActiveId;
    }

    public void setSKUActiveId(String SKUActiveId) {
        this.SKUActiveId = SKUActiveId == null ? null : SKUActiveId.trim();
    }
}