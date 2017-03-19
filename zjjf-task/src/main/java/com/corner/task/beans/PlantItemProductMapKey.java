package com.corner.task.beans;

public class PlantItemProductMapKey {
    private String productId;

    private String plantItemId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getPlantItemId() {
        return plantItemId;
    }

    public void setPlantItemId(String plantItemId) {
        this.plantItemId = plantItemId == null ? null : plantItemId.trim();
    }
}