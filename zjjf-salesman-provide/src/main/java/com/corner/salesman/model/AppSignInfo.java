package com.corner.salesman.model;

import java.util.ArrayList;
import java.util.List;
/**
 * 签到返回App对象
 * @author Longx
 *
 */
public class AppSignInfo{

	private Integer staffTotal;//总人数
	
	private String typeName;//上下班名称
	
	@SuppressWarnings("rawtypes")
	private List signList = new ArrayList();//上班

	public Integer getStaffTotal() {
		return staffTotal;
	}

	public void setStaffTotal(Integer staffTotal) {
		this.staffTotal = staffTotal;
	}

	public List getSignList() {
		return signList;
	}

	public void setSignList(List signList) {
		this.signList = signList;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
