package com.zjjf.analysis.beans.analysis.base;

import java.io.Serializable;

public class BaseRoleDataTemplate implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private Integer menuId;

    private String authkey;

    private String name;
    
    private Integer isChecked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey == null ? null : authkey.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}
    
}