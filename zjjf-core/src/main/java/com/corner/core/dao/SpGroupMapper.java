package com.corner.core.dao;

import com.corner.core.beans.SpGroup;

public interface SpGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpGroup record);

    int insertSelective(SpGroup record);

    SpGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpGroup record);

    int updateByPrimaryKey(SpGroup record);
}