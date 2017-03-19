package com.corner.core.dao;

import com.corner.core.beans.AppPayWayCfg;

public interface AppPayWayCfgMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(AppPayWayCfg record);

    int insertSelective(AppPayWayCfg record);

    AppPayWayCfg selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(AppPayWayCfg record);

    int updateByPrimaryKey(AppPayWayCfg record);
}