package com.corner.salesman.dao;

import com.corner.salesman.model.DeptManager;

public interface DeptManagerMapper {
    int insert(DeptManager record);

    int insertSelective(DeptManager record);
    
    int deleteDeptManager(String deptCode);
}