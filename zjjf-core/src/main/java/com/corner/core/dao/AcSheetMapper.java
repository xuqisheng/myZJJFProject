package com.corner.core.dao;

import com.corner.core.beans.AcSheet;

public interface AcSheetMapper {
    int deleteByPrimaryKey(String id);

    int insert(AcSheet record);

    int insertSelective(AcSheet record);

    AcSheet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AcSheet record);

    int updateByPrimaryKey(AcSheet record);
}