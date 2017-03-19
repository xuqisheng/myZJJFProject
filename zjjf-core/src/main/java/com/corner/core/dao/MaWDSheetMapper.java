package com.corner.core.dao;

import com.corner.core.beans.MaWDSheet;

public interface MaWDSheetMapper {
    int deleteByPrimaryKey(String id);

    int insert(MaWDSheet record);

    int insertSelective(MaWDSheet record);

    MaWDSheet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaWDSheet record);

    int updateByPrimaryKey(MaWDSheet record);
}