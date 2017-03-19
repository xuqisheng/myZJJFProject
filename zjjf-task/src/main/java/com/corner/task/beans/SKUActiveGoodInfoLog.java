package com.corner.task.beans;

import java.math.BigDecimal;
import java.util.Date;

public class SKUActiveGoodInfoLog {
    private String id;

    private Integer actype;

    private Date actTime;

    private String opUser;

    private String skuActiveId;

    private Integer goodsType;

    private String plantItemId;

    private String spId;

    private Integer spGroupId;

    private Integer buyLimitNum4old;

    private Integer buyLimitNum4new;

    private BigDecimal activePrice4old;

    private BigDecimal activePrice4new;

    private String tagId4old;

    private String tagId4new;

    private Integer rsNum;

    private String activeGoodInfoId;

    private Integer totalLimitNumold;

    private Integer totalLimitNumnew;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getActype() {
        return actype;
    }

    public void setActype(Integer actype) {
        this.actype = actype;
    }

    public Date getActTime() {
        return actTime;
    }

    public void setActTime(Date actTime) {
        this.actTime = actTime;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser == null ? null : opUser.trim();
    }

    public String getSkuActiveId() {
        return skuActiveId;
    }

    public void setSkuActiveId(String skuActiveId) {
        this.skuActiveId = skuActiveId == null ? null : skuActiveId.trim();
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getPlantItemId() {
        return plantItemId;
    }

    public void setPlantItemId(String plantItemId) {
        this.plantItemId = plantItemId == null ? null : plantItemId.trim();
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

    public Integer getBuyLimitNum4old() {
        return buyLimitNum4old;
    }

    public void setBuyLimitNum4old(Integer buyLimitNum4old) {
        this.buyLimitNum4old = buyLimitNum4old;
    }

    public Integer getBuyLimitNum4new() {
        return buyLimitNum4new;
    }

    public void setBuyLimitNum4new(Integer buyLimitNum4new) {
        this.buyLimitNum4new = buyLimitNum4new;
    }

    public BigDecimal getActivePrice4old() {
        return activePrice4old;
    }

    public void setActivePrice4old(BigDecimal activePrice4old) {
        this.activePrice4old = activePrice4old;
    }

    public BigDecimal getActivePrice4new() {
        return activePrice4new;
    }

    public void setActivePrice4new(BigDecimal activePrice4new) {
        this.activePrice4new = activePrice4new;
    }

    public String getTagId4old() {
        return tagId4old;
    }

    public void setTagId4old(String tagId4old) {
        this.tagId4old = tagId4old == null ? null : tagId4old.trim();
    }

    public String getTagId4new() {
        return tagId4new;
    }

    public void setTagId4new(String tagId4new) {
        this.tagId4new = tagId4new == null ? null : tagId4new.trim();
    }

    public Integer getRsNum() {
        return rsNum;
    }

    public void setRsNum(Integer rsNum) {
        this.rsNum = rsNum;
    }

    public String getActiveGoodInfoId() {
        return activeGoodInfoId;
    }

    public void setActiveGoodInfoId(String activeGoodInfoId) {
        this.activeGoodInfoId = activeGoodInfoId == null ? null : activeGoodInfoId.trim();
    }

    public Integer getTotalLimitNumold() {
        return totalLimitNumold;
    }

    public void setTotalLimitNumold(Integer totalLimitNumold) {
        this.totalLimitNumold = totalLimitNumold;
    }

    public Integer getTotalLimitNumnew() {
        return totalLimitNumnew;
    }

    public void setTotalLimitNumnew(Integer totalLimitNumnew) {
        this.totalLimitNumnew = totalLimitNumnew;
    }
}