package com.zjjf.analysis.beans.analysis.supplier;

import java.io.Serializable;
import java.util.Date;

import com.zjjf.analysis.beans.vo.ParamMapVo;

public class SupplierSpgroupSaleGoal extends ParamMapVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer monthGoal;
	
	private Integer dailyGoal;
	
	private Integer dayTime;
	
	private Integer createTime;
	
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getMonthGoal() {
		return monthGoal;
	}

	public void setMonthGoal(int monthGoal) {
		this.monthGoal = monthGoal;
	}

	public Integer getDailyGoal() {
		return dailyGoal;
	}

	public void setDailyGoal(int dailyGoal) {
		this.dailyGoal = dailyGoal;
	}

	public Integer getDayTime() {
		return dayTime;
	}

	public void setDayTime(int dayTime) {
		this.dayTime = dayTime;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
