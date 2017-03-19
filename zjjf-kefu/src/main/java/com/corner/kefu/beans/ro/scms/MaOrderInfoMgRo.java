package com.corner.kefu.beans.ro.scms;

import com.corner.core.beans.ro.AmazeUIListRo;

public class MaOrderInfoMgRo extends AmazeUIListRo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String managerId;
	private String managerName;
	private String id;
	private String orderId;
	private int groupId;
	private int status;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName == null ? null : managerName.trim();
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId == null ? null : managerId.trim();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}