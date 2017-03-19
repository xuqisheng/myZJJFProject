package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class SpOrderActiveMap {
    private String fOrderId;

    private String sOrderId;

    private Integer level;

    private Integer spVoucherActiveId;

    private BigDecimal rebeatMoney;

    private BigDecimal rebeatSp;

    private BigDecimal rebeatSp2Sp;

    private BigDecimal rebeatSp2Plat;

    private BigDecimal rebatePlat;

    private String spId;

    private Double plantHalve;

    private Integer storeId;

    private Date time;

    private Date addTime;

    public String getfOrderId() {
        return fOrderId;
    }

    public void setfOrderId(String fOrderId) {
        this.fOrderId = fOrderId == null ? null : fOrderId.trim();
    }

    public String getsOrderId() {
        return sOrderId;
    }

    public void setsOrderId(String sOrderId) {
        this.sOrderId = sOrderId == null ? null : sOrderId.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSpVoucherActiveId() {
        return spVoucherActiveId;
    }

    public void setSpVoucherActiveId(Integer spVoucherActiveId) {
        this.spVoucherActiveId = spVoucherActiveId;
    }

    public BigDecimal getRebeatMoney() {
        return rebeatMoney;
    }

    public void setRebeatMoney(BigDecimal rebeatMoney) {
        this.rebeatMoney = rebeatMoney;
    }

    public BigDecimal getRebeatSp() {
        return rebeatSp;
    }

    public void setRebeatSp(BigDecimal rebeatSp) {
        this.rebeatSp = rebeatSp;
    }

    public BigDecimal getRebeatSp2Sp() {
        return rebeatSp2Sp;
    }

    public void setRebeatSp2Sp(BigDecimal rebeatSp2Sp) {
        this.rebeatSp2Sp = rebeatSp2Sp;
    }

    public BigDecimal getRebeatSp2Plat() {
        return rebeatSp2Plat;
    }

    public void setRebeatSp2Plat(BigDecimal rebeatSp2Plat) {
        this.rebeatSp2Plat = rebeatSp2Plat;
    }

    public BigDecimal getRebatePlat() {
        return rebatePlat;
    }

    public void setRebatePlat(BigDecimal rebatePlat) {
        this.rebatePlat = rebatePlat;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}