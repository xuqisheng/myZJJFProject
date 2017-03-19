package com.zjjf.analysis.beans.analysis.authority;

import java.io.Serializable;

public class BaseRoleUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
    private Integer roleId;

    private String userId;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}