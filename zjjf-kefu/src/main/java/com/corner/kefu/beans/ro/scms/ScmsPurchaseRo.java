package com.corner.kefu.beans.ro.scms;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

public class ScmsPurchaseRo  extends EasyUIQueryModel{

	private String name;
	
	private Integer supportStatus;
	
	private Integer status;
	
	private Integer check;
	
	private Integer ordertype;
	
	
	
	
	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public Integer getCheck() {
		return check;
	}

	public void setCheck(Integer check) {
		this.check = check;
	}

	public Integer getSupportStatus() {
		return supportStatus;
	}

	public void setSupportStatus(Integer supportStatus) {
		this.supportStatus = supportStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toHqlString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toHqlObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
