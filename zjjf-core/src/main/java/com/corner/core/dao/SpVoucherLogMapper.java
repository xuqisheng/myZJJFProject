package com.corner.core.dao;

import com.corner.core.beans.SpVoucherLog;

public interface SpVoucherLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpVoucherLog record);

    int insertSelective(SpVoucherLog record);

    SpVoucherLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpVoucherLog record);

    int updateByPrimaryKey(SpVoucherLog record);
}