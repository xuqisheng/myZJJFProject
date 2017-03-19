package com.corner.scms.beans.vo.erp;

import java.io.Serializable;

import com.corner.core.beans.ERPManagerItem;

public class ERPManagerItemVo extends ERPManagerItem implements Serializable {
	private String mdseId;
	private String mdseIdX;
	private String name;
	private String spec;
	private String pkg;
	private String picUrl;
	private String yiJiName;
	private String erJiName;
	private String managerId;
	private String managerName;

	public String getMdseIdX() {
		return mdseIdX;
	}

	public void setMdseIdX(String mdseIdX) {
		this.mdseIdX = mdseIdX;
	}

	@Override
	public String getManagerId() {
		return managerId;
	}

	@Override
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getMdseId() {
		return mdseId;
	}
	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getYiJiName() {
		return yiJiName;
	}
	public void setYiJiName(String yiJiName) {
		this.yiJiName = yiJiName;
	}
	public String getErJiName() {
		return erJiName;
	}
	public void setErJiName(String erJiName) {
		this.erJiName = erJiName;
	}
}
