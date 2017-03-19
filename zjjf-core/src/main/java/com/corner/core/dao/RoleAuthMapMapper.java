package com.corner.core.dao;

import com.corner.core.beans.RoleAuthMapKey;

public interface RoleAuthMapMapper {
    int deleteByPrimaryKey(RoleAuthMapKey key);

    int insert(RoleAuthMapKey record);

    int insertSelective(RoleAuthMapKey record);
}