package com.corner.core.dao;

import com.corner.core.beans.HelpType;

public interface HelpTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HelpType record);

    int insertSelective(HelpType record);

    HelpType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HelpType record);

    int updateByPrimaryKey(HelpType record);
}