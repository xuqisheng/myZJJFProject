package com.corner.core.dao;

import com.corner.core.beans.AppItemLabelMap;
import com.corner.core.beans.AppItemLabelMapKey;

public interface AppItemLabelMapMapper {
    int deleteByPrimaryKey(AppItemLabelMapKey key);

    int insert(AppItemLabelMap record);

    int insertSelective(AppItemLabelMap record);

    AppItemLabelMap selectByPrimaryKey(AppItemLabelMapKey key);

    int updateByPrimaryKeySelective(AppItemLabelMap record);

    int updateByPrimaryKey(AppItemLabelMap record);
}