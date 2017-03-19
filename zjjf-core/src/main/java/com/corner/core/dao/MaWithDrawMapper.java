package com.corner.core.dao;

import com.corner.core.beans.MaWithDraw;

public interface MaWithDrawMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaWithDraw record);

    int insertSelective(MaWithDraw record);

    MaWithDraw selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaWithDraw record);

    int updateByPrimaryKey(MaWithDraw record);
}