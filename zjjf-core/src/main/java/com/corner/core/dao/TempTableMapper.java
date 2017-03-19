package com.corner.core.dao;

import com.corner.core.beans.TempTable;

public interface TempTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempTable record);

    int insertSelective(TempTable record);

    TempTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempTable record);

    int updateByPrimaryKey(TempTable record);
}