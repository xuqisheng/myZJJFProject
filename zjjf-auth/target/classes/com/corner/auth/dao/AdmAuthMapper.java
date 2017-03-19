package com.corner.auth.dao;

import com.corner.auth.beans.AdmAuth;

public interface AdmAuthMapper {
    int deleteByPrimaryKey(String id);

    int insert(AdmAuth record);

    int insertSelective(AdmAuth record);

    AdmAuth selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdmAuth record);

    int updateByPrimaryKey(AdmAuth record);
}