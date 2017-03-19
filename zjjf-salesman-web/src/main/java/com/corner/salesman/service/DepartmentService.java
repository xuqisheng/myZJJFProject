package com.corner.salesman.service;

import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.model.Department;

public interface DepartmentService {
	
    public List<Department> findDeptList() throws Exception;
    
	/**
	 * 查询部门分页方法
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public Pager<Department> getDeptPageList(Department department) throws Exception;
	
	/**
	 * 添加部门信息方法
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int addDepartment(Department department) throws Exception;
	
	/**
	 * 修改部门信息方法
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int updateDepartment(Department department) throws Exception;
	
	/**
	 * 删除部门信息方法
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int deleteDepartment(Department deptVo) throws Exception;
	
	/**
	 * 根据ID查询部门信息
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public Department findDepartmentById(String deptId) throws Exception;
	
	/**
	 * 检查新增部门ID是否已经存在
	 * @param deptId
	 * @return
	 */
	public int checkDeptIsExist(String deptId) throws Exception;
	
	/**
	 * 检查该用户是否为部门管理者，如果是则返回部门名称
	 * @param userId
	 * @return
	 */
	public String checkIsDeptLeader(String userId) throws Exception;
}
