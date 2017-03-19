package com.corner.task.beans;

import java.util.Date;

public class SKUActivePublishTagLog {
    private String id;

    private Integer actype;

    private Date actTime;

    private String opUser;

    private String skuActiveId;

    private Integer goodsType;

    private String plantItemId;

    private String tagId;

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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }
}