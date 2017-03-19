package com.corner.core.dao;

import com.corner.core.beans.AppModuleCfgMapKey;

public interface AppModuleCfgMapMapper {
    int deleteByPrimaryKey(AppModuleCfgMapKey key);

    int insert(AppModuleCfgMapKey record);

    int insertSelective(AppModuleCfgMapKey record);
}