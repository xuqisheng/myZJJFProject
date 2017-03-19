package com.zjjf.analysis.beans.analysis.store;

import java.math.BigDecimal;
import java.util.Date;

public class StoreKpiDaily {
	private Integer id;

	private Integer storeId;

	private Integer spGroupId;

	private Integer dayTime;

	private BigDecimal kpiTurnover;

	private Date updateTime;

	private Integer createTime;

	private Integer isZj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public BigDecimal getKpiTurnover() {
		return kpiTurnover;
	}

	public void setKpiTurnover(BigDecimal kpiTurnover) {
		this.kpiTurnover = kpiTurnover;
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

	public Integer getIsZj() {
		return isZj;
	}

	public void setIsZj(Integer isZj) {
		this.isZj = isZj;
	}

}