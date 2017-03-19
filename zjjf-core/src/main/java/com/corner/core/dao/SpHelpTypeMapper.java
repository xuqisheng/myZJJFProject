package com.corner.core.dao;

import com.corner.core.beans.SpHelpType;

public interface SpHelpTypeMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(SpHelpType record);

    int insertSelective(SpHelpType record);

    SpHelpType selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(SpHelpType record);

    int updateByPrimaryKey(SpHelpType record);
}