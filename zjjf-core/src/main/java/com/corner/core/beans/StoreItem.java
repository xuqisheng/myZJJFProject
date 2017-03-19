package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.utils.StringUtil;

public class StoreItem {
    private String id=StringUtil.getUUID();

    private Integer itemId;

    private String mdseId;

    private Byte ordId;

    private Integer storeId;

    private String storeName;

    private BigDecimal price;

    private BigDecimal discountPrice;

    private Long rates;

    private Short goodsStock;

    private Short sales;

    private Integer clickRate;

    private Boolean subnews;

    private Boolean subtuijian;

    private Date addTime;

    private Date updateTime;

    private Byte status;

    private Boolean isDelete;

    private String col1;

    private String col2;

    private String col3;

    private String col4;

    private String col5;

    private String col6;

    private Short middlenumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getMdseId() {
        return mdseId;
    }

    public void setMdseId(String mdseId) {
        this.mdseId = mdseId == null ? null : mdseId.trim();
    }

    public Byte getOrdId() {
        return ordId;
    }

    public void setOrdId(Byte ordId) {
        this.ordId = ordId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getRates() {
        return rates;
    }

    public void setRates(Long rates) {
        this.rates = rates;
    }

    public Short getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Short goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Short getSales() {
        return sales;
    }

    public void setSales(Short sales) {
        this.sales = sales;
    }

    public Integer getClickRate() {
        return clickRate;
    }

    public void setClickRate(Integer clickRate) {
        this.clickRate = clickRate;
    }

    public Boolean getSubnews() {
        return subnews;
    }

    public void setSubnews(Boolean subnews) {
        this.subnews = subnews;
    }

    public Boolean getSubtuijian() {
        return subtuijian;
    }

    public void setSubtuijian(Boolean subtuijian) {
        this.subtuijian = subtuijian;
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

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1 == null ? null : col1.trim();
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2 == null ? null : col2.trim();
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3 == null ? null : col3.trim();
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4 == null ? null : col4.trim();
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5 == null ? null : col5.trim();
    }

    public String getCol6() {
        return col6;
    }

    public void setCol6(String col6) {
        this.col6 = col6 == null ? null : col6.trim();
    }

    public Short getMiddlenumber() {
        return middlenumber;
    }

    public void setMiddlenumber(Short middlenumber) {
        this.middlenumber = middlenumber;
    }
}