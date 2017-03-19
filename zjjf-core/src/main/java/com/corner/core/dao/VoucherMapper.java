package com.corner.core.dao;

import com.corner.core.beans.Voucher;

public interface VoucherMapper {
    int deleteByPrimaryKey(String id);

    int insert(Voucher record);

    int insertSelective(Voucher record);

    Voucher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Voucher record);

    int updateByPrimaryKey(Voucher record);
}