package com.corner.kefu.beans.ro.scms;

import com.corner.core.beans.ro.AmazeUIListRo;

public class AuthMgRo extends AmazeUIListRo {

	private static final long serialVersionUID = -7822793143877667463L;
	private String authName;

	private String action;
	
	private Byte appId;
	
	private String upId;
	
	public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }
    
    public Byte getAppId() {
		return appId;
	}

	public void setAppId(Byte appId) {
		this.appId = appId;
	}
	
	public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId == null ? null : upId.trim();
    }
}