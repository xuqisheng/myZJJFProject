package com.corner.core.beans;

public class ERPManagerSpWarehouseMap {
    private String eRPManagerId;

    private String supplierId;

    private String eRPWarehouseId;

    public String geteRPManagerId() {
        return eRPManagerId;
    }

    public void seteRPManagerId(String eRPManagerId) {
        this.eRPManagerId = eRPManagerId == null ? null : eRPManagerId.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String geteRPWarehouseId() {
        return eRPWarehouseId;
    }

    public void seteRPWarehouseId(String eRPWarehouseId) {
        this.eRPWarehouseId = eRPWarehouseId == null ? null : eRPWarehouseId.trim();
    }
}