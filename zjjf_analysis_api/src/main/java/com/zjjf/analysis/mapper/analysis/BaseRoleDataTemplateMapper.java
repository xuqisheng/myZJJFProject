package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.BaseRoleDataTemplate;

public interface BaseRoleDataTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseRoleDataTemplate record);

    int insertSelective(BaseRoleDataTemplate record);

    BaseRoleDataTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseRoleDataTemplate record);

    int updateByPrimaryKey(BaseRoleDataTemplate record);
    
    List<BaseRoleDataTemplate> getRoleDataTemplate(HashMap<String, Object> dataParam);
    
    List<HashMap<String, Object>> getParentTitle(HashMap<String, Object> dataParam);
}