package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class SpVoucherActive {
    private Integer id;

    private Byte ruleType;

    private String ruleName;

    private String ruleRemark;

    private String ruleContent;

    private Boolean ruleEnable;

    private Date ruleStart;

    private Date ruleEnd;

    private Byte ruleScopFlag;

    private String ruleScop;

    private Byte rulePayType;

    private Byte transportTimeType;

    private String rulePayStr;

    private BigDecimal ruleStartPrice;

    private Byte ruleSendLimit;

    private Byte sendType;

    private Integer sendId;

    private Integer ordId;

    private Date addTime;

    private Date updateTime;

    private Byte status;

    private Boolean isDelete;

    private String buyGoods;

    private String sendGoods;

    private Double plantHalve;

    private Byte useItemFlag;

    private String storeIds;

    private String imgUrl;

    private String mgItems;

    private String useItemIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getRuleType() {
        return ruleType;
    }

    public void setRuleType(Byte ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getRuleRemark() {
        return ruleRemark;
    }

    public void setRuleRemark(String ruleRemark) {
        this.ruleRemark = ruleRemark == null ? null : ruleRemark.trim();
    }

    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent == null ? null : ruleContent.trim();
    }

    public Boolean getRuleEnable() {
        return ruleEnable;
    }

    public void setRuleEnable(Boolean ruleEnable) {
        this.ruleEnable = ruleEnable;
    }

    public Date getRuleStart() {
        return ruleStart;
    }

    public void setRuleStart(Date ruleStart) {
        this.ruleStart = ruleStart;
    }

    public Date getRuleEnd() {
        return ruleEnd;
    }

    public void setRuleEnd(Date ruleEnd) {
        this.ruleEnd = ruleEnd;
    }

    public Byte getRuleScopFlag() {
        return ruleScopFlag;
    }

    public void setRuleScopFlag(Byte ruleScopFlag) {
        this.ruleScopFlag = ruleScopFlag;
    }

    public String getRuleScop() {
        return ruleScop;
    }

    public void setRuleScop(String ruleScop) {
        this.ruleScop = ruleScop == null ? null : ruleScop.trim();
    }

    public Byte getRulePayType() {
        return rulePayType;
    }

    public void setRulePayType(Byte rulePayType) {
        this.rulePayType = rulePayType;
    }

    public Byte getTransportTimeType() {
        return transportTimeType;
    }

    public void setTransportTimeType(Byte transportTimeType) {
        this.transportTimeType = transportTimeType;
    }

    public String getRulePayStr() {
        return rulePayStr;
    }

    public void setRulePayStr(String rulePayStr) {
        this.rulePayStr = rulePayStr == null ? null : rulePayStr.trim();
    }

    public BigDecimal getRuleStartPrice() {
        return ruleStartPrice;
    }

    public void setRuleStartPrice(BigDecimal ruleStartPrice) {
        this.ruleStartPrice = ruleStartPrice;
    }

    public Byte getRuleSendLimit() {
        return ruleSendLimit;
    }

    public void setRuleSendLimit(Byte ruleSendLimit) {
        this.ruleSendLimit = ruleSendLimit;
    }

    public Byte getSendType() {
        return sendType;
    }

    public void setSendType(Byte sendType) {
        this.sendType = sendType;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getOrdId() {
        return ordId;
    }

    public void setOrdId(Integer ordId) {
        this.ordId = ordId;
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

    public String getBuyGoods() {
        return buyGoods;
    }

    public void setBuyGoods(String buyGoods) {
        this.buyGoods = buyGoods == null ? null : buyGoods.trim();
    }

    public String getSendGoods() {
        return sendGoods;
    }

    public void setSendGoods(String sendGoods) {
        this.sendGoods = sendGoods == null ? null : sendGoods.trim();
    }

    public Double getPlantHalve() {
        return plantHalve;
    }

    public void setPlantHalve(Double plantHalve) {
        this.plantHalve = plantHalve;
    }

    public Byte getUseItemFlag() {
        return useItemFlag;
    }

    public void setUseItemFlag(Byte useItemFlag) {
        this.useItemFlag = useItemFlag;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds == null ? null : storeIds.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getMgItems() {
        return mgItems;
    }

    public void setMgItems(String mgItems) {
        this.mgItems = mgItems == null ? null : mgItems.trim();
    }

    public String getUseItemIds() {
        return useItemIds;
    }

    public void setUseItemIds(String useItemIds) {
        this.useItemIds = useItemIds == null ? null : useItemIds.trim();
    }
}