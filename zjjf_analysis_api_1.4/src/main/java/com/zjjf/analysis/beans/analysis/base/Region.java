package com.zjjf.analysis.beans.analysis.base;

import java.io.Serializable;

public class Region implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String pId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

}