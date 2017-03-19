package com.corner.salesman.model;

import java.util.ArrayList;
import java.util.List;

import com.corner.salesman.common.persistence.BaseEntity;

/**
 * 我的下属对象
 * @author Administrator
 *
 */
public class MyUnderling extends BaseEntity<MyUnderling> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<User> userList = new ArrayList<User>();//部门下的用户列表
	private List<Department> deptList = new ArrayList<Department>();//子部门列表
	private List<Department> navList = new ArrayList<Department>();//菜单导航列表
	
	public MyUnderling(){
	}
	public MyUnderling(List<User> userList,List<Department> deptList){
		this.userList = userList;
		this.deptList = deptList;
	}
	public MyUnderling(List<User> userList,List<Department> deptList,List<Department> navList){
		this.userList = userList;
		this.deptList = deptList;
		this.navList = navList;
	}
	
	public List<Department> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<Department> getNavList() {
		return navList;
	}
	public void setNavList(List<Department> navList) {
		this.navList = navList;
	}
	
}
