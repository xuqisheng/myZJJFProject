package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class ERPPlantItemLog {
    private String id;

    private Date addTime;

    private String infoOrderId;

    private String orderId;

    private String orderDetailId;

    private Integer goodsStock;

    private Short operateQuantity;

    private BigDecimal areaPrice;

    private Short operateStock;

    private String supplierId;

    private String warehouseId;

    private Byte typeMg;

    private Integer itemBaseId;

    private String itemId;

    private String actionUserId;

    private String actionUserName;

    private Date productionDate;

    private String producingArea;

    private String remark;

    private Byte status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getInfoOrderId() {
        return infoOrderId;
    }

    public void setInfoOrderId(String infoOrderId) {
        this.infoOrderId = infoOrderId == null ? null : infoOrderId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId == null ? null : orderDetailId.trim();
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Short getOperateQuantity() {
        return operateQuantity;
    }

    public void setOperateQuantity(Short operateQuantity) {
        this.operateQuantity = operateQuantity;
    }

    public BigDecimal getAreaPrice() {
        return areaPrice;
    }

    public void setAreaPrice(BigDecimal areaPrice) {
        this.areaPrice = areaPrice;
    }

    public Short getOperateStock() {
        return operateStock;
    }

    public void setOperateStock(Short operateStock) {
        this.operateStock = operateStock;
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

    public Byte getTypeMg() {
        return typeMg;
    }

    public void setTypeMg(Byte typeMg) {
        this.typeMg = typeMg;
    }

    public Integer getItemBaseId() {
        return itemBaseId;
    }

    public void setItemBaseId(Integer itemBaseId) {
        this.itemBaseId = itemBaseId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getActionUserId() {
        return actionUserId;
    }

    public void setActionUserId(String actionUserId) {
        this.actionUserId = actionUserId == null ? null : actionUserId.trim();
    }

    public String getActionUserName() {
        return actionUserName;
    }

    public void setActionUserName(String actionUserName) {
        this.actionUserName = actionUserName == null ? null : actionUserName.trim();
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getProducingArea() {
        return producingArea;
    }

    public void setProducingArea(String producingArea) {
        this.producingArea = producingArea == null ? null : producingArea.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}