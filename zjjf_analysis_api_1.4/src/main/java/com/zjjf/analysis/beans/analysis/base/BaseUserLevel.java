package com.zjjf.analysis.beans.analysis.base;

import java.io.Serializable;

public class BaseUserLevel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private Integer level;

	private Integer roleId;

	private Integer dataId;

	private Integer isCheck;

	private Integer roleUserId;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

	public Integer getRoleUserId() {
		return roleUserId;
	}

	public void setRoleUserId(Integer roleUserId) {
		this.roleUserId = roleUserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}