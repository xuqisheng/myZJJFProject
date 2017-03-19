package com.corner.core.dao;

import com.corner.core.beans.WithDraw;

public interface WithDrawMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WithDraw record);

    int insertSelective(WithDraw record);

    WithDraw selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WithDraw record);

    int updateByPrimaryKey(WithDraw record);
}