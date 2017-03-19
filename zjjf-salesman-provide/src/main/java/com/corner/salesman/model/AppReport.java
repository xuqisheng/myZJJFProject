package com.corner.salesman.model;

import java.util.List;

import com.corner.salesman.common.persistence.BaseEntity;

public class AppReport extends BaseEntity<AppReport> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String remark;
    
    private String createTime;
    
    private String tmplName;//日志模板名称

    @SuppressWarnings("rawtypes")
	private List picList = null;//图片列表

    @SuppressWarnings("rawtypes")
	private List fieldList= null;
    
    private String participant;//查阅关系人

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@SuppressWarnings("rawtypes")
	public List getFieldList() {
		return fieldList;
	}

	@SuppressWarnings("rawtypes")
	public void setFieldList(List fieldList) {
		this.fieldList = fieldList;
	}

	public String getTmplName() {
		return tmplName;
	}

	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}
}