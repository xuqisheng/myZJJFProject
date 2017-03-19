package com.zjjf.analysis.beans.analysis.base;

import java.io.Serializable;

public class BaseRoleAuth implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private Integer roleId;

    private Integer menuAuthId;

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

    public Integer getMenuAuthId() {
        return menuAuthId;
    }

    public void setMenuAuthId(Integer menuAuthId) {
        this.menuAuthId = menuAuthId;
    }
}