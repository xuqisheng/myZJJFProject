package com.corner.core.dao;

import com.corner.core.beans.Adboard;

public interface AdboardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Adboard record);

    int insertSelective(Adboard record);

    Adboard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Adboard record);

    int updateByPrimaryKey(Adboard record);
}