package com.corner.salesman.model;

import java.util.Date;

import com.corner.core.beans.ro.ABaseRo;

public class Department extends ABaseRo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String deptId;
    
    private String deptName;

    private Integer status;

    private Integer isDelete;

    private String pid;

    private String remarks;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
    
    private String leaders;
    
    private String parentName;
    
    private String leaderId;
    
    private Integer provinceId;
    
    private Integer cityId;
    
    private Integer areaId;
    
    private Integer oldProvinceId;
    
    private Integer oldCityId;
    
    private Integer oldAreaId;
    
	public Integer getOldProvinceId() {
		return oldProvinceId;
	}

	public void setOldProvinceId(Integer oldProvinceId) {
		this.oldProvinceId = oldProvinceId;
	}

	public Integer getOldCityId() {
		return oldCityId;
	}

	public void setOldCityId(Integer oldCityId) {
		this.oldCityId = oldCityId;
	}

	public Integer getOldAreaId() {
		return oldAreaId;
	}

	public void setOldAreaId(Integer oldAreaId) {
		this.oldAreaId = oldAreaId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getLeaders() {
		return leaders;
	}

	public void setLeaders(String leaders) {
		this.leaders = leaders;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}