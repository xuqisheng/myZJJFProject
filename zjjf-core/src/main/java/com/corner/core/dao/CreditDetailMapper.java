package com.corner.core.dao;

import com.corner.core.beans.CreditDetail;

public interface CreditDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditDetail record);

    int insertSelective(CreditDetail record);

    CreditDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditDetail record);

    int updateByPrimaryKey(CreditDetail record);
}