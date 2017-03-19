package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class SKUActiveGoodInfo {
    private String id;

    private String SKUActiveId;

    private String spId;

    private Integer spGroupId;

    private Byte goodsType;

    private String plantItemId;

    private Integer brandId;

    private Integer buyLimitNum;

    private Integer totalLimitNum;

    private Integer totalBuyNum;

    private BigDecimal activePrice;

    private String tagId;

    private Date addTime;

    private Date updateTime;

    private Boolean status;

    private Byte isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSKUActiveId() {
        return SKUActiveId;
    }

    public void setSKUActiveId(String SKUActiveId) {
        this.SKUActiveId = SKUActiveId == null ? null : SKUActiveId.trim();
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public Integer getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(Integer spGroupId) {
        this.spGroupId = spGroupId;
    }

    public Byte getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Byte goodsType) {
        this.goodsType = goodsType;
    }

    public String getPlantItemId() {
        return plantItemId;
    }

    public void setPlantItemId(String plantItemId) {
        this.plantItemId = plantItemId == null ? null : plantItemId.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getBuyLimitNum() {
        return buyLimitNum;
    }

    public void setBuyLimitNum(Integer buyLimitNum) {
        this.buyLimitNum = buyLimitNum;
    }

    public Integer getTotalLimitNum() {
        return totalLimitNum;
    }

    public void setTotalLimitNum(Integer totalLimitNum) {
        this.totalLimitNum = totalLimitNum;
    }

    public Integer getTotalBuyNum() {
        return totalBuyNum;
    }

    public void setTotalBuyNum(Integer totalBuyNum) {
        this.totalBuyNum = totalBuyNum;
    }

    public BigDecimal getActivePrice() {
        return activePrice;
    }

    public void setActivePrice(BigDecimal activePrice) {
        this.activePrice = activePrice;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}