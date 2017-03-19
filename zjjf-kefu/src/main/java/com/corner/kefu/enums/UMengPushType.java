package com.corner.kefu.enums;


public enum UMengPushType {
	/**
	 * 主要用于全部推送和组推送
	 */
	ALL_PUSH("全部推送", "ALL"),
	USER_NO_LOGIN_PUSH("对未登录用户推送", "USER_NO_LOGIN"),
	USER_LOGIN_PUSH("对已登录用户推送", "USER_LOGIN"),
	CUST_PUSH("对买家推送", "CUST"),
	SHOP_PUSH("对商铺推送", "SHOP"),
	SPGROUP_PUSH("按定格推送", "SPGROUP");

	private String name;
	private String type;

	private UMengPushType(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
