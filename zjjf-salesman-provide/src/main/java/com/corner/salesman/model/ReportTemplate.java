package com.corner.salesman.model;

import java.util.Date;

import com.corner.salesman.common.persistence.BaseEntity;

public class ReportTemplate extends BaseEntity<ReportTemplate>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;

    private String fieldCnName;
    
    private String fieldEnName;

    private Integer fieldType;

    private Integer isDelete;

    private Integer isRequired;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
    
    private String fieldVal;
    
    private int length;
    
    public String getFieldVal() {
		return fieldVal;
	}

	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFieldCnName() {
        return fieldCnName;
    }

    public void setFieldEnName(String fieldEnName) {
        this.fieldEnName = fieldEnName == null ? null : fieldEnName.trim();
    }
    
    public String getFieldEnName() {
        return fieldEnName;
    }

    public void setFieldCnName(String fieldCnName) {
        this.fieldCnName = fieldCnName == null ? null : fieldCnName.trim();
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
    
}