package com.corner.core.beans;

import com.corner.core.enums.SpOrderOwnerCash;
import com.corner.core.enums.SpOrderOwnerType;

import java.math.BigDecimal;
import java.util.Date;

public class ERPSpOrderOwnerDetail {
    private String id;

    private String orderInfoId;

    private String orderInfoPid;

    private String orderId;

    private Integer itemBaseId;

    private String barCode;

    private String name;

    private String spec;

    private String pkg;

    private Short quantity;

    private Short cashQuantity;

    private BigDecimal cashPrice;

    private Short backQuantity;

    private Short sureQuantity;

    private BigDecimal surePrice;

    private Short outStockNum;

    private String wh3Id;

    private String wh3Name;

    private String wh2Name;

    private String wh1Id;

    private String wh1Name;

    private String remark;

    private Byte type;

    private Byte cash;

    private Date addTime;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(String orderInfoId) {
        this.orderInfoId = orderInfoId == null ? null : orderInfoId.trim();
    }

    public String getOrderInfoPid() {
        return orderInfoPid;
    }

    public void setOrderInfoPid(String orderInfoPid) {
        this.orderInfoPid = orderInfoPid == null ? null : orderInfoPid.trim();
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

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Short getCashQuantity() {
        return cashQuantity;
    }

    public void setCashQuantity(Short cashQuantity) {
        this.cashQuantity = cashQuantity;
    }

    public BigDecimal getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(BigDecimal cashPrice) {
        this.cashPrice = cashPrice;
    }

    public Short getBackQuantity() {
        return backQuantity;
    }

    public void setBackQuantity(Short backQuantity) {
        this.backQuantity = backQuantity;
    }

    public Short getSureQuantity() {
        return sureQuantity;
    }

    public void setSureQuantity(Short sureQuantity) {
        this.sureQuantity = sureQuantity;
    }

    public BigDecimal getSurePrice() {
        return surePrice;
    }

    public void setSurePrice(BigDecimal surePrice) {
        this.surePrice = surePrice;
    }

    public Short getOutStockNum() {
        return outStockNum;
    }

    public void setOutStockNum(Short outStockNum) {
        this.outStockNum = outStockNum;
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

    public String getWh1Id() {
        return wh1Id;
    }

    public void setWh1Id(String wh1Id) {
        this.wh1Id = wh1Id == null ? null : wh1Id.trim();
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

    public Byte getType() {
        return type;
    }
    public String getTypeStr() {
        return SpOrderOwnerType.getName(getType());
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getCash() {
        return cash;
    }
    public String getCashStr() {
        return SpOrderOwnerCash.getName(getCash());
    }

    public void setCash(Byte cash) {
        this.cash = cash;
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
}