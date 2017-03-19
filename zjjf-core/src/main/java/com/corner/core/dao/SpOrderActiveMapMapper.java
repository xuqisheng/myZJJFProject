package com.corner.core.dao;

import com.corner.core.beans.SpOrderActiveMap;

public interface SpOrderActiveMapMapper {
    int insert(SpOrderActiveMap record);

    int insertSelective(SpOrderActiveMap record);
}