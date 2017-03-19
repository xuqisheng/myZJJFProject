package com.zjjf.analysis.beans.analysis.supplier;

import java.util.Date;

public class SupplierSpGroupIdChangeLog {
	
    private Integer id;

    private String supplierId;

    private Integer spGroupId;

    private Integer dayTime;
    
    private Integer cityId;
    
    private Integer areaId;

    private Date updateTime;

    private Integer createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public Integer getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(Integer spGroupId) {
        this.spGroupId = spGroupId;
    }

    public Integer getDayTime() {
        return dayTime;
    }

    public void setDayTime(Integer dayTime) {
        this.dayTime = dayTime;
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
}