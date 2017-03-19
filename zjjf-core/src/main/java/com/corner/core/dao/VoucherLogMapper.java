package com.corner.core.dao;

import com.corner.core.beans.VoucherLog;

public interface VoucherLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(VoucherLog record);

    int insertSelective(VoucherLog record);

    VoucherLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VoucherLog record);

    int updateByPrimaryKey(VoucherLog record);
}