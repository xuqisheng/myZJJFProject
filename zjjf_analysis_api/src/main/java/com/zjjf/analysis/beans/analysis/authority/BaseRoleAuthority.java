package com.zjjf.analysis.beans.analysis.authority;

import java.io.Serializable;

public class BaseRoleAuthority implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer roleId;

    private Integer authId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
}