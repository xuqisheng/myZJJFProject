package com.zjjf.analysis.beans.vo.saleman;

import java.io.Serializable;
import java.util.List;

public class BaseReqParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String salesmanId;
	
	private String dayTime;
	
	private String timeType;
	
	private Integer pageNo = 1;
	
	private Integer pageSize = 5;
	
	private List<String> salemanIds;
	
	//部门,全国等名字
	private String areaName;
	
	//是否汇总
	private Integer isSum = 0;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getIsSum() {
		return isSum;
	}

	public void setIsSum(Integer isSum) {
		this.isSum = isSum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<String> getSalemanIds() {
		return salemanIds;
	}

	public void setSalemanIds(List<String> salemanIds) {
		this.salemanIds = salemanIds;
	}
	
}
