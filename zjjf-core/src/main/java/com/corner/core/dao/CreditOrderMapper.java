package com.corner.core.dao;

import com.corner.core.beans.CreditOrder;

public interface CreditOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditOrder record);

    int insertSelective(CreditOrder record);

    CreditOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditOrder record);

    int updateByPrimaryKey(CreditOrder record);
}