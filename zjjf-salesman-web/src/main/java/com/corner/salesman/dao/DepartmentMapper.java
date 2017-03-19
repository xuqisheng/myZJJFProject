package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.model.Department;

public interface DepartmentMapper {
	
	public int deleteByPrimaryKey(Integer id);

    public int insert(Department record);

    public int insertSelective(Department record);

    public Department selectByPrimaryKey(String deptId);

    public int updateByPrimaryKeySelective(Department record);

    public int updateByPrimaryKey(Department record);
    
    public int updateDeptmentByDeptId(Department record);
    
    public List<Department> findDeptList();
    
	/**
	 * 查询部门信息
	 * @param record
	 * @return
	 */
    public List<Department> getDeptPageList(Department record);
	/**
	 * 查询部门信息总数
	 * @param record
	 * @return
	 */
	public int getDeptPageSize(Department record);
	/**
	 * 根据部门ID获取部门领导名称列表
	 * @param record
	 * @return
	 */
	public String getDeptLeaders(Integer id);
	
	/**
	 * 检查新增部门ID是否已经存在
	 * @param deptId
	 * @return
	 */
	public int checkDeptIsExist(String deptId);
	
	/**
	 * 检查该用户是否为部门管理者，如果是则返回部门名称
	 * @param userId
	 * @return
	 */
	public String checkIsDeptLeader(String userId);
}