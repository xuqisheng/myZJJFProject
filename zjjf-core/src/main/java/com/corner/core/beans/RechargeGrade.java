package com.corner.core.beans;

import java.math.BigDecimal;

public class RechargeGrade {
    private Integer id;

    private BigDecimal gradeMoney;

    private Integer spVoucherId;

    private Integer spGroupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getGradeMoney() {
        return gradeMoney;
    }

    public void setGradeMoney(BigDecimal gradeMoney) {
        this.gradeMoney = gradeMoney;
    }

    public Integer getSpVoucherId() {
        return spVoucherId;
    }

    public void setSpVoucherId(Integer spVoucherId) {
        this.spVoucherId = spVoucherId;
    }

    public Integer getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(Integer spGroupId) {
        this.spGroupId = spGroupId;
    }
}