package com.corner.core.dao;

import com.corner.core.beans.AppItemLabel;

public interface AppItemLabelMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppItemLabel record);

    int insertSelective(AppItemLabel record);

    AppItemLabel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppItemLabel record);

    int updateByPrimaryKey(AppItemLabel record);
}