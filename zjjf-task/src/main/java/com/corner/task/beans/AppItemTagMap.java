package com.corner.task.beans;

public class AppItemTagMap extends AppItemTagMapKey {
    private Integer brandId;

    private String SKUActiveId;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getSKUActiveId() {
        return SKUActiveId;
    }

    public void setSKUActiveId(String SKUActiveId) {
        this.SKUActiveId = SKUActiveId == null ? null : SKUActiveId.trim();
    }
}