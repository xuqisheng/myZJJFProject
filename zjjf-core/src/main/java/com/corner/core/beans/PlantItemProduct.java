package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class PlantItemProduct {
    private String id;

    private String pkgName;

    private Date addtime;

    private Byte pkgType;

    private Boolean asDefault;

    private BigDecimal productPrice;

    private BigDecimal proMarketPrice;

    private Date upTime;

    private Date downTime;

    private Date buyStartTime;

    private Date buyEndTime;

    private Integer buyLimitNum;

    private Integer totalLimitNum;

    private Integer totalBuyNum;

    private String labelId;

    private String remark;

    private String youHui;

    private String SKUActiveId;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName == null ? null : pkgName.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Byte getPkgType() {
        return pkgType;
    }

    public void setPkgType(Byte pkgType) {
        this.pkgType = pkgType;
    }

    public Boolean getAsDefault() {
        return asDefault;
    }

    public void setAsDefault(Boolean asDefault) {
        this.asDefault = asDefault;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProMarketPrice() {
        return proMarketPrice;
    }

    public void setProMarketPrice(BigDecimal proMarketPrice) {
        this.proMarketPrice = proMarketPrice;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    public Date getBuyStartTime() {
        return buyStartTime;
    }

    public void setBuyStartTime(Date buyStartTime) {
        this.buyStartTime = buyStartTime;
    }

    public Date getBuyEndTime() {
        return buyEndTime;
    }

    public void setBuyEndTime(Date buyEndTime) {
        this.buyEndTime = buyEndTime;
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

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId == null ? null : labelId.trim();
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

    public String getSKUActiveId() {
        return SKUActiveId;
    }

    public void setSKUActiveId(String SKUActiveId) {
        this.SKUActiveId = SKUActiveId == null ? null : SKUActiveId.trim();
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