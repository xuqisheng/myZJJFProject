package com.corner.core.dao;

import com.corner.core.beans.SpAcGroup;

public interface SpAcGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpAcGroup record);

    int insertSelective(SpAcGroup record);

    SpAcGroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpAcGroup record);

    int updateByPrimaryKey(SpAcGroup record);
}