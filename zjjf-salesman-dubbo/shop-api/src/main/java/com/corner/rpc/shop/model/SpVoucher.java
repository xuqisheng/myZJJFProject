package com.corner.rpc.shop.model;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.salesman.commons.utils.UuidUtil;

public class SpVoucher {
    private String id = UuidUtil.get32UUID();

    private Integer storeId;

    private String storeNm;

    private Integer ruleId;

    private String name;

    private String remark;

    private Integer money;

    private String spId;

    private Double plantHalve;

    private Byte billType;

    private BigDecimal startPrice;

    private Byte payType;

    private String payStr;

    private Byte goodsType;

    private Byte useType;

    private String preOrderId;

    private String orderId;

    private String scope;

    private Date startTime;

    private Date expiredTime;

    private String createrId;

    private String createrName;

    private Byte createType;

    private Date createTime;

    private Date updateTime;

    private String secKey;

    private Byte pubStatus;

    private Byte status;

    private Byte acStatus;

    private Date acTime;

    private String acId;

    private Boolean isDelete;

    private Integer spVoucherActiveId;

    private String goodsStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreNm() {
        return storeNm;
    }

    public void setStoreNm(String storeNm) {
        this.storeNm = storeNm == null ? null : storeNm.trim();
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public Double getPlantHalve() {
        return plantHalve;
    }

    public void setPlantHalve(Double plantHalve) {
        this.plantHalve = plantHalve;
    }

    public Byte getBillType() {
        return billType;
    }

    public void setBillType(Byte billType) {
        this.billType = billType;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public String getPayStr() {
        return payStr;
    }

    public void setPayStr(String payStr) {
        this.payStr = payStr == null ? null : payStr.trim();
    }

    public Byte getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Byte goodsType) {
        this.goodsType = goodsType;
    }

    public Byte getUseType() {
        return useType;
    }

    public void setUseType(Byte useType) {
        this.useType = useType;
    }

    public String getPreOrderId() {
        return preOrderId;
    }

    public void setPreOrderId(String preOrderId) {
        this.preOrderId = preOrderId == null ? null : preOrderId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    public Byte getCreateType() {
        return createType;
    }

    public void setCreateType(Byte createType) {
        this.createType = createType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey == null ? null : secKey.trim();
    }

    public Byte getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(Byte pubStatus) {
        this.pubStatus = pubStatus;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(Byte acStatus) {
        this.acStatus = acStatus;
    }

    public Date getAcTime() {
        return acTime;
    }

    public void setAcTime(Date acTime) {
        this.acTime = acTime;
    }

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId == null ? null : acId.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getSpVoucherActiveId() {
        return spVoucherActiveId;
    }

    public void setSpVoucherActiveId(Integer spVoucherActiveId) {
        this.spVoucherActiveId = spVoucherActiveId;
    }

    public String getGoodsStr() {
        return goodsStr;
    }

    public void setGoodsStr(String goodsStr) {
        this.goodsStr = goodsStr == null ? null : goodsStr.trim();
    }
}