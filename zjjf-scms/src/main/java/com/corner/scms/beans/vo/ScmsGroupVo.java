package com.corner.scms.beans.vo;

import java.io.Serializable;

import com.corner.core.beans.ScmsGroup;

public class ScmsGroupVo extends ScmsGroup implements Serializable{

	//所属分类的名字
	private String areaName;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
	
}
