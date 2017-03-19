package com.corner.task.beans;

import java.math.BigDecimal;
import java.util.Date;

public class PlantItemLog {
    private String id;

    private Integer itemBaseId;

    private Integer spGroupId;

    private String spId;

    private Integer areaId;

    private String areaName;

    private BigDecimal areaPrice;

    private BigDecimal plantMemPrice;

    private BigDecimal plantDisPrice;

    private BigDecimal maoli;

    private BigDecimal fee;

    private Integer ordId;

    private Date addTime;

    private Date updateTime;

    private Boolean tuijian;

    private Integer goodsStock;

    private Integer middleStock;

    private Integer upper;

    private Integer lower;

    private BigDecimal scInPrice;

    private Integer sales;

    private Integer clickRate;

    private String remark;

    private String youHui;

    private Integer restrict;

    private Byte status;

    private Boolean isDelete;

    private Integer stockTactic;

    private Integer priceTactic;

    private Integer isSKUPromotion;

    private Date SKUProStartTime;

    private Date SKUProEndTime;

    private BigDecimal SKUProPrice;

    private Integer SKUProLimitNum;

    private String tagLabelId1;

    private String tagLabelId2;

    private String tagLabelId3;

    private Integer goodsType;

    private String warehouseId;

    private String logicStockId;

    private Byte logicStockTypeMg;

    private Integer SKUProTotalLimitNum;

    private String SKUPromotionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getItemBaseId() {
        return itemBaseId;
    }

    public void setItemBaseId(Integer itemBaseId) {
        this.itemBaseId = itemBaseId;
    }

    public Integer getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(Integer spGroupId) {
        this.spGroupId = spGroupId;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public BigDecimal getAreaPrice() {
        return areaPrice;
    }

    public void setAreaPrice(BigDecimal areaPrice) {
        this.areaPrice = areaPrice;
    }

    public BigDecimal getPlantMemPrice() {
        return plantMemPrice;
    }

    public void setPlantMemPrice(BigDecimal plantMemPrice) {
        this.plantMemPrice = plantMemPrice;
    }

    public BigDecimal getPlantDisPrice() {
        return plantDisPrice;
    }

    public void setPlantDisPrice(BigDecimal plantDisPrice) {
        this.plantDisPrice = plantDisPrice;
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

    public Boolean getTuijian() {
        return tuijian;
    }

    public void setTuijian(Boolean tuijian) {
        this.tuijian = tuijian;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Integer getMiddleStock() {
        return middleStock;
    }

    public void setMiddleStock(Integer middleStock) {
        this.middleStock = middleStock;
    }

    public Integer getUpper() {
        return upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }

    public Integer getLower() {
        return lower;
    }

    public void setLower(Integer lower) {
        this.lower = lower;
    }

    public BigDecimal getScInPrice() {
        return scInPrice;
    }

    public void setScInPrice(BigDecimal scInPrice) {
        this.scInPrice = scInPrice;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getClickRate() {
        return clickRate;
    }

    public void setClickRate(Integer clickRate) {
        this.clickRate = clickRate;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStockTactic() {
        return stockTactic;
    }

    public void setStockTactic(Integer stockTactic) {
        this.stockTactic = stockTactic;
    }

    public Integer getPriceTactic() {
        return priceTactic;
    }

    public void setPriceTactic(Integer priceTactic) {
        this.priceTactic = priceTactic;
    }

    public Integer getIsSKUPromotion() {
        return isSKUPromotion;
    }

    public void setIsSKUPromotion(Integer isSKUPromotion) {
        this.isSKUPromotion = isSKUPromotion;
    }

    public Date getSKUProStartTime() {
        return SKUProStartTime;
    }

    public void setSKUProStartTime(Date SKUProStartTime) {
        this.SKUProStartTime = SKUProStartTime;
    }

    public Date getSKUProEndTime() {
        return SKUProEndTime;
    }

    public void setSKUProEndTime(Date SKUProEndTime) {
        this.SKUProEndTime = SKUProEndTime;
    }

    public BigDecimal getSKUProPrice() {
        return SKUProPrice;
    }

    public void setSKUProPrice(BigDecimal SKUProPrice) {
        this.SKUProPrice = SKUProPrice;
    }

    public Integer getSKUProLimitNum() {
        return SKUProLimitNum;
    }

    public void setSKUProLimitNum(Integer SKUProLimitNum) {
        this.SKUProLimitNum = SKUProLimitNum;
    }

    public String getTagLabelId1() {
        return tagLabelId1;
    }

    public void setTagLabelId1(String tagLabelId1) {
        this.tagLabelId1 = tagLabelId1 == null ? null : tagLabelId1.trim();
    }

    public String getTagLabelId2() {
        return tagLabelId2;
    }

    public void setTagLabelId2(String tagLabelId2) {
        this.tagLabelId2 = tagLabelId2 == null ? null : tagLabelId2.trim();
    }

    public String getTagLabelId3() {
        return tagLabelId3;
    }

    public void setTagLabelId3(String tagLabelId3) {
        this.tagLabelId3 = tagLabelId3 == null ? null : tagLabelId3.trim();
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }

    public String getLogicStockId() {
        return logicStockId;
    }

    public void setLogicStockId(String logicStockId) {
        this.logicStockId = logicStockId == null ? null : logicStockId.trim();
    }

    public Byte getLogicStockTypeMg() {
        return logicStockTypeMg;
    }

    public void setLogicStockTypeMg(Byte logicStockTypeMg) {
        this.logicStockTypeMg = logicStockTypeMg;
    }

    public Integer getSKUProTotalLimitNum() {
        return SKUProTotalLimitNum;
    }

    public void setSKUProTotalLimitNum(Integer SKUProTotalLimitNum) {
        this.SKUProTotalLimitNum = SKUProTotalLimitNum;
    }

    public String getSKUPromotionId() {
        return SKUPromotionId;
    }

    public void setSKUPromotionId(String SKUPromotionId) {
        this.SKUPromotionId = SKUPromotionId == null ? null : SKUPromotionId.trim();
    }
}