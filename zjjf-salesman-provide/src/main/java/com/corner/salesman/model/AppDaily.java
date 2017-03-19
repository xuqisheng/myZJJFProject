package com.corner.salesman.model;

import java.util.ArrayList;
import java.util.List;

import com.corner.salesman.common.persistence.BaseEntity;

public class AppDaily extends BaseEntity<AppDaily> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String remark;
    
    private String createTime;

    @SuppressWarnings("rawtypes")
	private List picList = null;//图片列表

    private List<ReportTemplate> tmplList= new ArrayList<ReportTemplate>();

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ReportTemplate> getTmplList() {
		return tmplList;
	}

	public void setTmplList(List<ReportTemplate> tmplList) {
		this.tmplList = tmplList;
	}

	@SuppressWarnings("rawtypes")
	public List getPicList() {
		return picList;
	}

	@SuppressWarnings("rawtypes")
	public void setPicList(List picList) {
		this.picList = picList;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}