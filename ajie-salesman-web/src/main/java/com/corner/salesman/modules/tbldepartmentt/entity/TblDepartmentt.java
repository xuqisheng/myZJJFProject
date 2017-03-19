/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.tbldepartmentt.entity;

import org.hibernate.validator.constraints.Length;

//import org.hibernate.validator.constraints.Length(min=1, max=4, message="状态 ;
//import org.hibernate.validator.constraints.Length(min=1, max=1, message="部门是否删除;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.corner.salesman.common.persistence.DataEntity;

/**
 * 部门信息Entity
 * @author 小金刚
 * @version 2016-08-16
 */
public class TblDepartmentt extends DataEntity<TblDepartmentt> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String deptId;		// 部门编码
	private String deptName;		// 部门名称
	private String status;		// 状态 (0不可用，1正常)
	private String isDelete;		// 部门是否删除(0-否，1-是)
	private String pid;		// pid
	private Date createTime;		// createTime
	private Date updateTime;		// updateTime
	private String provinceId;		// provinceId
	private String cityId;		// cityId
	private String areaId;		// areaId
	private String parentName;
	private String userName;
	private String mobile;
	private String sid;      //用户ID
	

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public TblDepartmentt() {
		super();
	}

	public TblDepartmentt(String id){
		super(id);
	}

	@Length(min=0, max=20, message="部门编码长度必须介于 0 和 20 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=1, max=30, message="部门名称长度必须介于 1 和 30 之间")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Length(min=1, max=4, message="状态 (0不可用，1正常)长度必须介于 1 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=1, message="部门是否删除(0-否，1-是)长度必须介于 1 和 1 之间")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@Length(min=0, max=20, message="pid长度必须介于 0 和 20 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=11, message="provinceId长度必须介于 0 和 11 之间")
	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	@Length(min=0, max=11, message="cityId长度必须介于 0 和 11 之间")
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Length(min=0, max=11, message="areaId长度必须介于 0 和 11 之间")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
}