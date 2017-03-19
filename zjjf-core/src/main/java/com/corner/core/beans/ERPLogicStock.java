package com.corner.core.beans;

import java.util.Date;

public class ERPLogicStock {
    private String id;

    private String supplierId;

    private String warehouseId;

    private Integer itemBaseId;

    private String skuId;

    private Byte typeMg;

    private Byte typeSale;

    private Integer stockNum;

    private Integer lockStock;

    private Integer examineStock;

    private Integer transportStock;

    private Date addTime;

    private Date updateTime;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }

    public Integer getItemBaseId() {
        return itemBaseId;
    }

    public void setItemBaseId(Integer itemBaseId) {
        this.itemBaseId = itemBaseId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public Byte getTypeMg() {
        return typeMg;
    }

    public void setTypeMg(Byte typeMg) {
        this.typeMg = typeMg;
    }

    public Byte getTypeSale() {
        return typeSale;
    }

    public void setTypeSale(Byte typeSale) {
        this.typeSale = typeSale;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getLockStock() {
        return lockStock;
    }

    public void setLockStock(Integer lockStock) {
        this.lockStock = lockStock;
    }

    public Integer getExamineStock() {
        return examineStock;
    }

    public void setExamineStock(Integer examineStock) {
        this.examineStock = examineStock;
    }

    public Integer getTransportStock() {
        return transportStock;
    }

    public void setTransportStock(Integer transportStock) {
        this.transportStock = transportStock;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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