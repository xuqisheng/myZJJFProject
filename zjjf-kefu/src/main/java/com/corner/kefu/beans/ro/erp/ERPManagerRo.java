 package com.corner.kefu.beans.ro.erp;

import java.io.Serializable;

import com.corner.core.beans.ERPManager;
import com.corner.kefu.config.CommonPageConfig;

public class ERPManagerRo extends ERPManager implements Serializable {
	
	private static final long serialVersionUID = 154276113544004884L;
	//分页
	private int pageIndex = CommonPageConfig.asyncPageIndex;
	private int pageSize = CommonPageConfig.asyncPageSize;
	
    private Byte regionLevel;
	
	private Integer regionId;
	
	private String[] spIdArr; 
	
	private String keyStr;
	
	private String whId;
	
	public int getPageIndex() {
		return pageSize * pageIndex;
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
		this.codeOrName = codeOrName;
	}

	public Integer getText_cleaningDay() {
		return text_cleaningDay;
	}

	public void setText_cleaningDay(Integer text_cleaningDay) {
		this.text_cleaningDay = text_cleaningDay;
	}

	public String[] getSpIdArr() {
		return spIdArr;
	}

	public void setSpIdArr(String[] spIdArr) {
		this.spIdArr = spIdArr;
	}

	public Byte getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Byte regionLevel) {
		this.regionLevel = regionLevel;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

}
