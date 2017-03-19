/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.shop.entity;

import org.hibernate.validator.constraints.Length;

import com.corner.salesman.common.persistence.DataEntity;

/**
 * 线路规划Entity
 * @author setsail
 * @version 2016-08-05
 */
public class LinePlans extends DataEntity<LinePlans> {
	
	private static final long serialVersionUID = 1L;
	private String lineId;		// 线路ID
	private String lineName;		// 路线
	private String deptId;		// 部门ID
	private String deptName;		// 部门名
	private String userId;		// userId
	private String shopTotal;		// shopTotal
	private String visitTotal;		// visitTotal
	private String week;		// week
	private String userName;
	private String salesmanId;
	
    private String lineStr;
    
	private String shopNo;
	
	public LinePlans() {
		super();
	}

	public LinePlans(String id){
		super(id);
	}

	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}

	@Length(min=1, max=32, message="线路ID长度必须介于 1 和 32 之间")
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
	@Length(min=0, max=10, message="路线长度必须介于 0 和 10 之间")
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	@Length(min=0, max=32, message="部门ID长度必须介于 0 和 32 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=0, max=32, message="userId长度必须介于 0 和 32 之间")
	public String getUserId() {
		return userId;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=11, message="shopTotal长度必须介于 0 和 11 之间")
	public String getShopTotal() {
		return shopTotal;
	}

	public void setShopTotal(String shopTotal) {
		this.shopTotal = shopTotal;
	}
	
	@Length(min=0, max=10, message="visitTotal长度必须介于 0 和 10 之间")
	public String getVisitTotal() {
		return visitTotal;
	}

	public void setVisitTotal(String visitTotal) {
		this.visitTotal = visitTotal;
	}
	
	@Length(min=0, max=10, message="week长度必须介于 0 和 10 之间")
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getLineStr() {
		return lineStr;
	}

	public void setLineStr(String lineStr) {
		this.lineStr = lineStr;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	
}