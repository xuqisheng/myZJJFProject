package com.corner.salesman.service;

import java.util.List;


public interface DepartmentService {
	/**
	 * 根据用户ID 查询部门ID
	 * @param userId
	 * @return
	 */
	public String findDeptIdByUserId(String userId) throws Exception;
	
	/**
	 * 根据部门ID查询所在部门人员总数
	 * @param deptId
	 * @return
	 */
	public int getDeptUserTotal(String deptId) throws Exception;
	
	/**
	 * 根据当前部门ID获取所有下属子部门绑定的区域编码
	 * @param deptId
	 * @return
	 * @throws Exception
	 */
	public List<String> findDeptBindAreaList(String deptId) throws Exception;
	
	/**
	 * 根据当前部门ID获取本部门及下属子部门的编码
	 * @param deptId
	 * @return
	 * @throws Exception
	 */
	public List<String> findAllChildDeptIdList(String deptId) throws Exception;
	
	/**
	 * 检查部门是否存在
	 * @param deptId
	 * @return
	 */
	public int queryDeptIsExist(String deptId) throws Exception;
}
