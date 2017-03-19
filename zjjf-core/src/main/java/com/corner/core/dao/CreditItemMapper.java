package com.corner.core.dao;

import com.corner.core.beans.CreditItem;

public interface CreditItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditItem record);

    int insertSelective(CreditItem record);

    CreditItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditItem record);

    int updateByPrimaryKey(CreditItem record);
}