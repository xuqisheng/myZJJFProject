package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.authority.BaseRole;

public interface BaseRoleMapper {
	
    void deleteById(Integer id);

    int insert(BaseRole record);

    List<BaseRole> getRoleByUserId(String userId);
    
    List<BaseRole> getAllRole();
    
    BaseRole getRoleByRoleId(Integer roleId);
    
    Integer getTotalCount();
    
    void updateRole(HashMap<String, Object> updateMap);
    
    List<BaseRole> getRoleByRoleName(String userName);
}