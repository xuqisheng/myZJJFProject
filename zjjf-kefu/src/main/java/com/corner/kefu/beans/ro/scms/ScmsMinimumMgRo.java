package com.corner.kefu.beans.ro.scms;

import com.corner.core.beans.ro.AmazeUIListRo;

public class ScmsMinimumMgRo extends AmazeUIListRo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6732433416846689995L;
	private String[] ids;
	private String[] minimums;
	private String managerId;
	private String kefuId;
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String[] getMinimums() {
		return minimums;
	}
	public void setMinimums(String[] minimums) {
		this.minimums = minimums;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getKefuId() {
		return kefuId;
	}
	public void setKefuId(String kefuId) {
		this.kefuId = kefuId;
	}
	
}