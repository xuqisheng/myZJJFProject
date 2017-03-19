package com.corner.core.dao;

import com.corner.core.beans.AppItemTagProductMapKey;

public interface AppItemTagProductMapMapper {
    int deleteByPrimaryKey(AppItemTagProductMapKey key);

    int insert(AppItemTagProductMapKey record);

    int insertSelective(AppItemTagProductMapKey record);
}