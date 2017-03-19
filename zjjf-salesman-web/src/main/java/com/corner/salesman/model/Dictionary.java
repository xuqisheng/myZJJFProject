package com.corner.salesman.model;

import java.util.Date;

import com.corner.core.beans.ro.ABaseRo;

public class Dictionary extends ABaseRo {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String value;

    private String label;

    private String type;

    private String description;

    private Long sort;

    private String pid;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remarks;

    private String isDelete;
    
    private String timeHzType;//临时添加属性用于disablel禁用时存储数据类型所用
    private String timeHzVal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getTimeHzType() {
		return timeHzType;
	}

	public void setTimeHzType(String timeHzType) {
		this.timeHzType = timeHzType;
	}

	public String getTimeHzVal() {
		return timeHzVal;
	}

	public void setTimeHzVal(String timeHzVal) {
		this.timeHzVal = timeHzVal;
	}

	public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}