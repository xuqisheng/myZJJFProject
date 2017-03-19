package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;

import com.corner.core.beans.CustomerService;


public class CustomerServiceVo extends CustomerService implements Serializable{
	//部门名称
	private String deptName;
	//职位名称
	private String postName;
	//角色名称
	private String roleName;
	//创建者名字
	private String createName;
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
   
}