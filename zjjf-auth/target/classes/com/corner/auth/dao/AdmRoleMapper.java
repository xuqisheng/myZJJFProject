package com.corner.auth.dao;

import com.corner.auth.beans.AdmRole;

public interface AdmRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(AdmRole record);

    int insertSelective(AdmRole record);

    AdmRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdmRole record);

    int updateByPrimaryKey(AdmRole record);
}