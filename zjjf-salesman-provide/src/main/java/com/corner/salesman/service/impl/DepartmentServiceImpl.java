package com.corner.salesman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.dao.DeptMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.service.DepartmentService;
/**
 * 部门业务层实现类
 * @author 元宝
 * @version 2016-01-26
 */
@Service("departmentService")
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public String findDeptIdByUserId(String userId) throws Exception {
		return userDeptMapper.findDeptIdByUserId(userId);
	}

	@Override
	public int getDeptUserTotal(String deptId) throws Exception {
		return userDeptMapper.getDeptUserTotal(deptId);
	}
	
	/**
	 * 根据当前部门ID获取所有下属子部门绑定的区域编码
	 * @param deptId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<String> findDeptBindAreaList(String deptId) throws Exception{
		return deptMapper.findDeptBindAreaList(deptId);
	}
	
	/**
	 * 根据当前部门ID获取本部门及下属子部门的编码
	 * @param deptId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<String> findAllChildDeptIdList(String deptId) throws Exception{
		return deptMapper.findAllChildDeptIdList(deptId);
	}
	
	/**
	 * 检查部门是否存在
	 * @param deptId
	 * @return
	 */
	@Override
	public int queryDeptIsExist(String deptId) throws Exception{
		return deptMapper.queryDeptIsExist(deptId);
	}

}
