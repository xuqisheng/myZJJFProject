package com.zjjf.analysis.beans.analysis.store;

import java.io.Serializable;
import java.util.Date;

import com.zjjf.analysis.beans.vo.ParamMapVo;

public class StoreSpgroupSaleGoal extends ParamMapVo implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer newRisteredGole;

	private Integer activeStoreGole;

	private Integer newRisteredGoleDaily;

	private Integer activeStoreGoleDaily;

	private Integer dayTime;

	private Integer createTime;

	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNewRisteredGole() {
		return newRisteredGole;
	}

	public void setNewRisteredGole(Integer newRisteredGole) {
		this.newRisteredGole = newRisteredGole;
	}

	public Integer getActiveStoreGole() {
		return activeStoreGole;
	}

	public void setActiveStoreGole(Integer activeStoreGole) {
		this.activeStoreGole = activeStoreGole;
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

	public Integer getNewRisteredGoleDaily() {
		return newRisteredGoleDaily;
	}

	public void setNewRisteredGoleDaily(Integer newRisteredGoleDaily) {
		this.newRisteredGoleDaily = newRisteredGoleDaily;
	}

	public Integer getActiveStoreGoleDaily() {
		return activeStoreGoleDaily;
	}

	public void setActiveStoreGoleDaily(Integer activeStoreGoleDaily) {
		this.activeStoreGoleDaily = activeStoreGoleDaily;
	}

}