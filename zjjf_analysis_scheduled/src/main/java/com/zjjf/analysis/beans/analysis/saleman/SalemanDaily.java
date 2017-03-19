package com.zjjf.analysis.beans.analysis.saleman;

import java.math.BigDecimal;
import java.util.Date;

public class SalemanDaily {
    private Integer id;

    private String salemanId;
    
    private String salemanName;

    private Integer dayTime;

    private Date updateTime;

    private Integer createTime;

    private Integer isEffect = 1;

    private BigDecimal turnover = new BigDecimal(0);

    private BigDecimal zjturnover = new BigDecimal(0);

    private Integer orderCount = 0;

    private Integer activeStore = 0;

    private Integer newRegStore = 0;
    
    private BigDecimal kpiTurnover = new BigDecimal(0);
    
    private BigDecimal zjKpiTurnover = new BigDecimal(0);
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalemanId() {
        return salemanId;
    }

    public void setSalemanId(String salemanId) {
        this.salemanId = salemanId == null ? null : salemanId.trim();
    }

    public Integer getDayTime() {
        return dayTime;
    }

    public void setDayTime(Integer dayTime) {
        this.dayTime = dayTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(Integer isEffect) {
        this.isEffect = isEffect;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public BigDecimal getZjturnover() {
        return zjturnover;
    }

    public void setZjturnover(BigDecimal zjturnover) {
        this.zjturnover = zjturnover;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getActiveStore() {
        return activeStore;
    }

    public void setActiveStore(Integer activeStore) {
        this.activeStore = activeStore;
    }

    public Integer getNewRegStore() {
        return newRegStore;
    }

    public void setNewRegStore(Integer newRegStore) {
        this.newRegStore = newRegStore;
    }

	public String getSalemanName() {
		return salemanName;
	}

	public void setSalemanName(String salemanName) {
		this.salemanName = salemanName;
	}

	public BigDecimal getKpiTurnover() {
		return kpiTurnover;
	}

	public void setKpiTurnover(BigDecimal kpiTurnover) {
		this.kpiTurnover = kpiTurnover;
	}

	public BigDecimal getZjKpiTurnover() {
		return zjKpiTurnover;
	}

	public void setZjKpiTurnover(BigDecimal zjKpiTurnover) {
		this.zjKpiTurnover = zjKpiTurnover;
	}

}