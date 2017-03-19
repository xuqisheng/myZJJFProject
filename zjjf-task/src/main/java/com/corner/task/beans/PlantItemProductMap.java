package com.corner.task.beans;

import java.math.BigDecimal;

public class PlantItemProductMap extends PlantItemProductMapKey {
    private BigDecimal pkgPrice;

    private Integer num;

    public BigDecimal getPkgPrice() {
        return pkgPrice;
    }

    public void setPkgPrice(BigDecimal pkgPrice) {
        this.pkgPrice = pkgPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}