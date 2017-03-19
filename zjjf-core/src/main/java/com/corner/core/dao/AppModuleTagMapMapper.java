package com.corner.core.dao;

import com.corner.core.beans.AppModuleTagMapKey;

public interface AppModuleTagMapMapper {
    int deleteByPrimaryKey(AppModuleTagMapKey key);

    int insert(AppModuleTagMapKey record);

    int insertSelective(AppModuleTagMapKey record);
}