package com.corner.core.dao;

import com.corner.core.beans.WhWDSheet;

public interface WhWDSheetMapper {
    int deleteByPrimaryKey(String id);

    int insert(WhWDSheet record);

    int insertSelective(WhWDSheet record);

    WhWDSheet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WhWDSheet record);

    int updateByPrimaryKey(WhWDSheet record);
}