package com.corner.kefu.beans.ro;

import com.corner.core.beans.ro.AmazeUIListRo;

public class ItemCatelogRo extends AmazeUIListRo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String spid;
	private Boolean status;
	private Boolean isdelete;

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
}
