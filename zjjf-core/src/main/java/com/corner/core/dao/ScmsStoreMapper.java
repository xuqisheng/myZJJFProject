package com.corner.core.dao;

import com.corner.core.beans.ScmsStore;

public interface ScmsStoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScmsStore record);

    int insertSelective(ScmsStore record);

    ScmsStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScmsStore record);

    int updateByPrimaryKeyWithBLOBs(ScmsStore record);

    int updateByPrimaryKey(ScmsStore record);
}