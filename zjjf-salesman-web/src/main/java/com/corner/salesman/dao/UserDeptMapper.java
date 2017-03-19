package com.corner.salesman.dao;

import com.corner.salesman.model.UserDept;

public interface UserDeptMapper {
    int insert(UserDept record);

    int insertSelective(UserDept record);
    
    int updateByPrimaryKey(UserDept record);
    
    int deleteUserDeptByUserId(UserDept record);
    
    int deleteUserDeptByDeptId(String deptId);
}