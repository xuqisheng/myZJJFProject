package com.zjjf.analysis.beans.corner;

import java.math.BigDecimal;
import java.util.Date;

public class ERPPurchaseStockDetail {
    private String id;

    private String pId;

    private String orderId;

    private Integer itemBaseId;

    private String barCode;

    private String mdseId;

    private String itemCode;

    private String name;

    private String spec;

    private String pkg;

    private String img;

    private Short quantity;

    private Short operateQuantity;

    private Short operateStock;

    private BigDecimal price;

    private BigDecimal areaPrice;

    private BigDecimal totalPrice;

    private String wh3Id;

    private String wh3Name;

    private String wh2Name;

    private String wh1Name;

    private String remark;

    private Date addTime;

    private Date productionTime;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getItemBaseId() {
        return itemBaseId;
    }

    public void setItemBaseId(Integer itemBaseId) {
        this.itemBaseId = itemBaseId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getMdseId() {
        return mdseId;
    }

    public void setMdseId(String mdseId) {
        this.mdseId = mdseId == null ? null : mdseId.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg == null ? null : pkg.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Short getOperateQuantity() {
        return operateQuantity;
    }

    public void setOperateQuantity(Short operateQuantity) {
        this.operateQuantity = operateQuantity;
    }

    public Short getOperateStock() {
        return operateStock;
    }

    public void setOperateStock(Short operateStock) {
        this.operateStock = operateStock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAreaPrice() {
        return areaPrice;
    }

    public void setAreaPrice(BigDecimal areaPrice) {
        this.areaPrice = areaPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getWh3Id() {
        return wh3Id;
    }

    public void setWh3Id(String wh3Id) {
        this.wh3Id = wh3Id == null ? null : wh3Id.trim();
    }

    public String getWh3Name() {
        return wh3Name;
    }

    public void setWh3Name(String wh3Name) {
        this.wh3Name = wh3Name == null ? null : wh3Name.trim();
    }

    public String getWh2Name() {
        return wh2Name;
    }

    public void setWh2Name(String wh2Name) {
        this.wh2Name = wh2Name == null ? null : wh2Name.trim();
    }

    public String getWh1Name() {
        return wh1Name;
    }

    public void setWh1Name(String wh1Name) {
        this.wh1Name = wh1Name == null ? null : wh1Name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Date productionTime) {
        this.productionTime = productionTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}