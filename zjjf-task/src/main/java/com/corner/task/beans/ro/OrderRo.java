package com.corner.task.beans.ro;

import com.corner.task.util.DateUtil;

public class OrderRo  extends AmazeUIListRo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4742632599382281070L;
	private String[] mdseIds;
	private String addTime;
	private String spName;
	private String mdseId;
	private String itemId;
	private String[] itemIds; 
	
	public String[] getMdseIds() {
		return mdseIds;
	}
	public void setMdseIds(String[] mdseIds) {
		this.mdseIds = mdseIds;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName == null ? null : spName.trim();
	}
	public String getMdseId() {
		return mdseId;
	}
	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String[] getItemIds() {
		return itemIds;
	}
	public void setItemIds(String[] itemIds) {
		this.itemIds = itemIds;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime == null ? DateUtil.getFormateDate() : addTime;
	}
}
