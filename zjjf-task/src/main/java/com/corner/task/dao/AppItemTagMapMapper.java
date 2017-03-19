package com.corner.task.dao;

import com.corner.task.beans.AppItemTagMap;
import com.corner.task.beans.AppItemTagMapKey;

public interface AppItemTagMapMapper {
    int deleteByPrimaryKey(AppItemTagMapKey key);

    int insert(AppItemTagMap record);

    int insertSelective(AppItemTagMap record);

    AppItemTagMap selectByPrimaryKey(AppItemTagMapKey key);

    int updateByPrimaryKeySelective(AppItemTagMap record);

    int updateByPrimaryKey(AppItemTagMap record);
}