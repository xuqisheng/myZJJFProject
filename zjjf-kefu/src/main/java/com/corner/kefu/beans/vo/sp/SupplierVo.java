package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;

import com.corner.core.beans.Supplier;

public class SupplierVo extends Supplier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer spGroupId;
	private String provinceName;
	private String cityName;
	private String areaName;
	private String groupName;
	private int totalConsumer;//客户数
	private Double cost;//成本
	
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public int getTotalConsumer() {
		return totalConsumer;
	}
	public void setTotalConsumer(int totalConsumer) {
		this.totalConsumer = totalConsumer;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getSpGroupId() {
		return spGroupId;
	}
	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}
}
