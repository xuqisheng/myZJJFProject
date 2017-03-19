package com.corner.core.dao;

import com.corner.core.beans.SpWithDraw;

public interface SpWithDrawMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(SpWithDraw record);

    Long insertSelective(SpWithDraw record);

    SpWithDraw selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpWithDraw record);

    int updateByPrimaryKey(SpWithDraw record);
}