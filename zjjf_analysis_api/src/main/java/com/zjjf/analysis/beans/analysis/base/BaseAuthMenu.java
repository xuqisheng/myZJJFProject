package com.zjjf.analysis.beans.analysis.base;

import java.io.Serializable;

public class BaseAuthMenu implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private Integer menuId;

    private Integer authId;

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

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
}