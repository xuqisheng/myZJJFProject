package com.corner.core.dao;

import com.corner.core.beans.MaWDSheetMapKey;

public interface MaWDSheetMapMapper {
    int deleteByPrimaryKey(MaWDSheetMapKey key);

    int insert(MaWDSheetMapKey record);

    int insertSelective(MaWDSheetMapKey record);
}