package com.corner.core.dao;

import com.corner.core.beans.TempStore;

public interface TempStoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempStore record);

    int insertSelective(TempStore record);

    TempStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempStore record);

    int updateByPrimaryKey(TempStore record);
}