package com.corner.core.dao;

import com.corner.core.beans.UserRoleMapKey;

public interface UserRoleMapMapper {
    int deleteByPrimaryKey(UserRoleMapKey key);

    int insert(UserRoleMapKey record);

    int insertSelective(UserRoleMapKey record);
}