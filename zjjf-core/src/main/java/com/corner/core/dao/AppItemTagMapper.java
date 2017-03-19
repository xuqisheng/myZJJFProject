package com.corner.core.dao;

import com.corner.core.beans.AppItemTag;

public interface AppItemTagMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppItemTag record);

    int insertSelective(AppItemTag record);

    AppItemTag selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppItemTag record);

    int updateByPrimaryKey(AppItemTag record);
}