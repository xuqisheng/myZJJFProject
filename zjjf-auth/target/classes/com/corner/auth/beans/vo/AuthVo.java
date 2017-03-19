package com.corner.auth.beans.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corner.auth.beans.Auth;

public class AuthVo extends Auth implements Serializable {
	private static final long serialVersionUID = 1L;
	List<Auth> auths = new ArrayList<Auth>();

	public List<Auth> getAuths() {
		return auths;
	}

	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}
}
