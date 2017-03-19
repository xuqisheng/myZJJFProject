package com.corner.core.dao;

import com.corner.core.beans.Accounter;

public interface AccounterMapper {
    int deleteByPrimaryKey(String id);

    int insert(Accounter record);

    int insertSelective(Accounter record);

    Accounter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Accounter record);

    int updateByPrimaryKey(Accounter record);
}