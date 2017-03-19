package com.corner.core.beans;

import java.math.BigDecimal;

public class SpVoucherGraded {
    private Integer id;

    private Integer activeId;

    private Integer tempId;

    private Byte sendLimit;

    private BigDecimal startPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public Integer getTempId() {
        return tempId;
    }

    public void setTempId(Integer tempId) {
        this.tempId = tempId;
    }

    public Byte getSendLimit() {
        return sendLimit;
    }

    public void setSendLimit(Byte sendLimit) {
        this.sendLimit = sendLimit;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }
}