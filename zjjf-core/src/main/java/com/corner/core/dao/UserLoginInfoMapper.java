package com.corner.core.dao;

import com.corner.core.beans.UserLoginInfo;

public interface UserLoginInfoMapper {
    int deleteByPrimaryKey(String uli_id);

    int insert(UserLoginInfo record);

    int insertSelective(UserLoginInfo record);

    UserLoginInfo selectByPrimaryKey(String uli_id);

    int updateByPrimaryKeySelective(UserLoginInfo record);

    int updateByPrimaryKey(UserLoginInfo record);
}