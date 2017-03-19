package com.corner.core.dao;

import com.corner.core.beans.ScmsStockLog;

public interface ScmsStockLogMapper {
    int insert(ScmsStockLog record);

    int insertSelective(ScmsStockLog record);
}