package com.corner.core.dao;

import com.corner.core.beans.SpWDSheetMapKey;

public interface SpWDSheetMapMapper {
    int deleteByPrimaryKey(SpWDSheetMapKey key);

    int insert(SpWDSheetMapKey record);

    int insertSelective(SpWDSheetMapKey record);
}