package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.BaseRoleAuth;

public interface BaseRoleAuthMapper {
	
	void deleteByRoleId(Integer roleId);
	
	void deleteByRoleIdMenuId(HashMap<String, Object> paramMap);
	
    int roleId(Integer id);

    int insert(BaseRoleAuth record);

    int insertSelective(BaseRoleAuth record);

    BaseRoleAuth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseRoleAuth record);

    int updateByPrimaryKey(BaseRoleAuth record);
    
    List<BaseRoleAuth> getByMenuId(Integer menuId);
    
    List<BaseRoleAuth> getSelectMenuByRoleId(HashMap<String, Object> paramMap);
    
    public void deleteRoleAuthIdNotInIdList(HashMap<String, Object> map);
}