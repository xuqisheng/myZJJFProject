package com.corner.core.dao;

import com.corner.core.beans.Settlement;

public interface SettlementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Settlement record);

    int insertSelective(Settlement record);

    Settlement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Settlement record);

    int updateByPrimaryKeyWithBLOBs(Settlement record);

    int updateByPrimaryKey(Settlement record);
}