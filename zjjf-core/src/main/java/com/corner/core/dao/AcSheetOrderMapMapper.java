package com.corner.core.dao;

import com.corner.core.beans.AcSheetOrderMapKey;

public interface AcSheetOrderMapMapper {
    int deleteByPrimaryKey(AcSheetOrderMapKey key);

    int insert(AcSheetOrderMapKey record);

    int insertSelective(AcSheetOrderMapKey record);
}