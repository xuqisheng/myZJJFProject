package com.corner.salesman.dao;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.Department;

import java.util.List;
@MyBatisDao
public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    Department findDeptById(String deptId);
    
    Department findDeptByPid(String deptId);
    
    List<String> findDeptBindAreaList(String deptId);
    
    List<String> findAllChildDeptIdList(String deptId);
    
    int queryDeptIsExist(String deptId);
}