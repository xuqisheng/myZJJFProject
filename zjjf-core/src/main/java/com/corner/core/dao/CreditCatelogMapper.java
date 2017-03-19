package com.corner.core.dao;

import com.corner.core.beans.CreditCatelog;

public interface CreditCatelogMapper {
    int deleteByPrimaryKey(Short id);

    int insert(CreditCatelog record);

    int insertSelective(CreditCatelog record);

    CreditCatelog selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(CreditCatelog record);

    int updateByPrimaryKey(CreditCatelog record);
}