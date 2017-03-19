package com.corner.pc.beans.vo;

import java.io.Serializable;

import com.corner.pc.beans.Recruit;

public class RecruitVo extends Recruit implements Serializable {
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
