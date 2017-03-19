 package com.corner.scms.beans.ro.erp;

import java.io.Serializable;

import com.corner.core.beans.ERPManager;

public class ERPManagerRo extends ERPManager implements Serializable {
	
	private static final long serialVersionUID = -5109999678967644652L;
	//分页
	private int pageIndex;
	private int pageSize = 10;
	
	public int getPageIndex() {
		return pageSize * ((pageIndex - 1) > 0 ? (pageIndex - 1) : 0);
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//
	private String codeOrName;
	
	private Integer text_cleaningDay;

	public String getCodeOrName() {
		return codeOrName;
	}

	public void setCodeOrName(String codeOrName) {
		this.codeOrName = codeOrName == null ? "" : codeOrName.trim();
	}

	public Integer getText_cleaningDay() {
		return text_cleaningDay;
	}

	public void setText_cleaningDay(Integer text_cleaningDay) {
		this.text_cleaningDay = text_cleaningDay;
	}

}
