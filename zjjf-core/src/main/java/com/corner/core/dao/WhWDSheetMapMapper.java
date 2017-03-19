package com.corner.core.dao;

import com.corner.core.beans.WhWDSheetMapKey;

public interface WhWDSheetMapMapper {
    int deleteByPrimaryKey(WhWDSheetMapKey key);

    int insert(WhWDSheetMapKey record);

    int insertSelective(WhWDSheetMapKey record);
}