package com.corner.task.beans;

import java.math.BigDecimal;
import java.util.Date;

public class ScOrderDetail {
    private String id;

    private String orderId;

    private String orderId2;

    private String maOrderInfoId;

    private String itemId;

    private String wayCode;

    private String barCode;

    private String brand;

    private String name;

    private String spec;

    private String img;

    private String pkg;

    private Short quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private Integer areaId;

    private BigDecimal areaTotalPrice;

    private BigDecimal areaPrice;

    private String areaName;

    private BigDecimal zjjfPrice;

    private BigDecimal marketPrice;

    private BigDecimal maoli;

    private BigDecimal fee;

    private String spId;

    private Integer scmsGroupId;

    private String managerId;

    private String remark;

    private String youHui;

    private Date addTime;

    private Boolean isDelete;

    private Integer restrict;

    private Byte status;

    private BigDecimal marketTotalPrice;

    private BigDecimal freight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderId2() {
        return orderId2;
    }

    public void setOrderId2(String orderId2) {
        this.orderId2 = orderId2 == null ? null : orderId2.trim();
    }

    public String getMaOrderInfoId() {
        return maOrderInfoId;
    }

    public void setMaOrderInfoId(String maOrderInfoId) {
        this.maOrderInfoId = maOrderInfoId == null ? null : maOrderInfoId.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode == null ? null : wayCode.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg == null ? null : pkg.trim();
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public BigDecimal getAreaTotalPrice() {
        return areaTotalPrice;
    }

    public void setAreaTotalPrice(BigDecimal areaTotalPrice) {
        this.areaTotalPrice = areaTotalPrice;
    }

    public BigDecimal getAreaPrice() {
        return areaPrice;
    }

    public void setAreaPrice(BigDecimal areaPrice) {
        this.areaPrice = areaPrice;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public BigDecimal getZjjfPrice() {
        return zjjfPrice;
    }

    public void setZjjfPrice(BigDecimal zjjfPrice) {
        this.zjjfPrice = zjjfPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getMaoli() {
        return maoli;
    }

    public void setMaoli(BigDecimal maoli) {
        this.maoli = maoli;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public Integer getScmsGroupId() {
        return scmsGroupId;
    }

    public void setScmsGroupId(Integer scmsGroupId) {
        this.scmsGroupId = scmsGroupId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getYouHui() {
        return youHui;
    }

    public void setYouHui(String youHui) {
        this.youHui = youHui == null ? null : youHui.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getRestrict() {
        return restrict;
    }

    public void setRestrict(Integer restrict) {
        this.restrict = restrict;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getMarketTotalPrice() {
        return marketTotalPrice;
    }

    public void setMarketTotalPrice(BigDecimal marketTotalPrice) {
        this.marketTotalPrice = marketTotalPrice;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }
}