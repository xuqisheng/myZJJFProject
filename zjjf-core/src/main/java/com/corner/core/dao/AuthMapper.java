package com.corner.core.dao;

import com.corner.core.beans.Auth;

public interface AuthMapper {
    int deleteByPrimaryKey(String id);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);
}