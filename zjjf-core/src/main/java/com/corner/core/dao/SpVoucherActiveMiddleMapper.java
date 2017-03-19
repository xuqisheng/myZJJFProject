package com.corner.core.dao;

import com.corner.core.beans.SpVoucherActiveMiddle;

public interface SpVoucherActiveMiddleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpVoucherActiveMiddle record);

    int insertSelective(SpVoucherActiveMiddle record);

    SpVoucherActiveMiddle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpVoucherActiveMiddle record);

    int updateByPrimaryKey(SpVoucherActiveMiddle record);
}