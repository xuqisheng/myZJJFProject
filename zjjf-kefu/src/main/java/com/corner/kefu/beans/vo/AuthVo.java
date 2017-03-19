package com.corner.kefu.beans.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.Auth;

public class AuthVo extends Auth implements Serializable{
	private static final long serialVersionUID = -2479010309334678376L;
	List<Auth> auths = new ArrayList<Auth>();

	public List<Auth> getAuths() {
		return auths;
	}

	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}
}
