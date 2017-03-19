package com.corner.auth.beans.vo;

import java.io.Serializable;

import com.corner.auth.beans.AdmRole;

public class RoleVo extends AdmRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**用户数量**/
	private int userNum;
	
	private Byte appId;
	
	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	
	public Byte getAppId() {
        return appId;
    }

    public void setAppId(Byte appId) {
        this.appId = appId;
    }
}
