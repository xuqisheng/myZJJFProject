package com.corner.scms.beans.vo;

import java.io.Serializable;

import com.corner.core.beans.SpOrderDetail;

public class SpOrderDetailVo extends SpOrderDetail implements Serializable {
	private String cateName;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
}
