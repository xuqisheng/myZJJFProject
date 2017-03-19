package com.zjjf.analysis.beans.analysis.supplier;

import java.util.Date;

public class SupplierSpGroupSaleGoal {
    private Integer id;

    private Integer cityId;

    private Integer areaId;

    private Integer spGroupId;

    private String supplierId;

    private Integer monthGoal;
    
    private Integer dailyGoal;

    private Integer dayTime;

    private Integer createTime;

    private Date updateTime;
    
    public Integer getDailyGoal() {
		return dailyGoal;
	}

	public void setDailyGoal(Integer dailyGoal) {
		this.dailyGoal = dailyGoal;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(Integer spGroupId) {
        this.spGroupId = spGroupId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public Integer getMonthGoal() {
        return monthGoal;
    }

    public void setMonthGoal(Integer monthGoal) {
        this.monthGoal = monthGoal;
    }

    public Integer getDayTime() {
        return dayTime;
    }

    public void setDayTime(Integer dayTime) {
        this.dayTime = dayTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}