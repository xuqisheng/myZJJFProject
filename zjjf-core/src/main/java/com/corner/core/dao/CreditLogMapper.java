package com.corner.core.dao;

import com.corner.core.beans.CreditLog;

public interface CreditLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(CreditLog record);

    int insertSelective(CreditLog record);

    CreditLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditLog record);

    int updateByPrimaryKey(CreditLog record);
}