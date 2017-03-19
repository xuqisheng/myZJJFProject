package com.corner.core.beans;

import java.math.BigDecimal;

public class SpVoucherActiveStoreLog {
    private String id;

    private Integer activeId;

    private Integer storeId;

    private BigDecimal useMoney;

    private Integer voucherTempId;

    private Byte status;

    private Boolean isDelete;

    private BigDecimal totalOrDerPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

    public Integer getVoucherTempId() {
        return voucherTempId;
    }

    public void setVoucherTempId(Integer voucherTempId) {
        this.voucherTempId = voucherTempId;
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

    public BigDecimal getTotalOrDerPrice() {
        return totalOrDerPrice;
    }

    public void setTotalOrDerPrice(BigDecimal totalOrDerPrice) {
        this.totalOrDerPrice = totalOrDerPrice;
    }
}