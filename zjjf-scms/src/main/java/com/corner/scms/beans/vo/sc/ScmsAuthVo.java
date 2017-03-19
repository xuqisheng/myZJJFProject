package com.corner.scms.beans.vo.sc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.Auth;

public class ScmsAuthVo extends Auth implements Serializable {
	
	private static final long serialVersionUID = 4368550230849743582L;
	
	private List<Auth> auths = new ArrayList<Auth>();

	public List<Auth> getAuths() {
		return auths;
	}

	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}
}
