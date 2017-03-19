package com.corner.core.dao;

import com.corner.core.beans.SpVoucherTemp;

public interface SpVoucherTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpVoucherTemp record);

    int insertSelective(SpVoucherTemp record);

    SpVoucherTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpVoucherTemp record);

    int updateByPrimaryKey(SpVoucherTemp record);
}