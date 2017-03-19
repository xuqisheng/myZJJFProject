package com.corner.kefu.beans.ro;

import java.io.Serializable;

import com.corner.core.beans.ro.ABaseRo;

public class SendTimeConfigRo extends ABaseRo implements Serializable {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
