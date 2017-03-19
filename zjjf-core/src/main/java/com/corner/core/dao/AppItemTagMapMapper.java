package com.corner.core.dao;

import com.corner.core.beans.AppItemTagMap;
import com.corner.core.beans.AppItemTagMapKey;

public interface AppItemTagMapMapper {
    int deleteByPrimaryKey(AppItemTagMapKey key);

    int insert(AppItemTagMap record);

    int insertSelective(AppItemTagMap record);

    AppItemTagMap selectByPrimaryKey(AppItemTagMapKey key);

    int updateByPrimaryKeySelective(AppItemTagMap record);

    int updateByPrimaryKey(AppItemTagMap record);
}