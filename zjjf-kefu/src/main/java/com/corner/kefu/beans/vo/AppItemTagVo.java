package com.corner.kefu.beans.vo;

import java.io.Serializable;

import com.corner.core.beans.AppItemTag;

public class AppItemTagVo extends AppItemTag implements Serializable {
	private String modelName;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
}
