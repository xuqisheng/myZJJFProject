package com.corner.core.dao;

import com.corner.core.beans.SpWDSheet;

public interface SpWDSheetMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpWDSheet record);

    int insertSelective(SpWDSheet record);

    SpWDSheet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpWDSheet record);

    int updateByPrimaryKey(SpWDSheet record);
}