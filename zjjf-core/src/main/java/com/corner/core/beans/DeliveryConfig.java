package com.corner.core.beans;

import java.math.BigDecimal;

public class DeliveryConfig {
    private Integer id;

    private Byte scopType;

    private Integer spGroupId;

    private BigDecimal discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getScopType() {
        return scopType;
    }

    public void setScopType(Byte scopType) {
        this.scopType = scopType;
    }

    public Integer getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(Integer spGroupId) {
        this.spGroupId = spGroupId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}