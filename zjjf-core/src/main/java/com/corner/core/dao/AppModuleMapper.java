package com.corner.core.dao;

import com.corner.core.beans.AppModule;

public interface AppModuleMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppModule record);

    int insertSelective(AppModule record);

    AppModule selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppModule record);

    int updateByPrimaryKey(AppModule record);
}