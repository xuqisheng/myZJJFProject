package com.corner.kefu.beans.ro;

import java.io.Serializable;

import com.corner.core.beans.ro.AmazeUIListRo;

public class BrandRo extends AmazeUIListRo implements Serializable  {
	
	private String noAndName;

	public String getNoAndName() {
		return noAndName;
	}

	public void setNoAndName(String noAndName) {
		this.noAndName = noAndName;
	}
}
