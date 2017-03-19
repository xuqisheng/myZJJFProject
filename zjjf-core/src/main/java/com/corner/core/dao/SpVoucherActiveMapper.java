package com.corner.core.dao;

import com.corner.core.beans.SpVoucherActive;

public interface SpVoucherActiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpVoucherActive record);

    int insertSelective(SpVoucherActive record);

    SpVoucherActive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpVoucherActive record);

    int updateByPrimaryKey(SpVoucherActive record);

}