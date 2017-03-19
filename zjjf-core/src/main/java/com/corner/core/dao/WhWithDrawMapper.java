package com.corner.core.dao;

import com.corner.core.beans.WhWithDraw;

public interface WhWithDrawMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WhWithDraw record);

    int insertSelective(WhWithDraw record);

    WhWithDraw selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WhWithDraw record);

    int updateByPrimaryKey(WhWithDraw record);
}