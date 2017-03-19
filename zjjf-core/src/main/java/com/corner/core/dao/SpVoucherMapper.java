package com.corner.core.dao;

import com.corner.core.beans.SpVoucher;

public interface SpVoucherMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpVoucher record);

    int insertSelective(SpVoucher record);

    SpVoucher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpVoucher record);

    int updateByPrimaryKeyWithBLOBs(SpVoucher record);

    int updateByPrimaryKey(SpVoucher record);
}