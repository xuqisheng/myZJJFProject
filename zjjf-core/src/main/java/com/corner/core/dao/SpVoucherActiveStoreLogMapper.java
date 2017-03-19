package com.corner.core.dao;

import com.corner.core.beans.SpVoucherActiveStoreLog;

public interface SpVoucherActiveStoreLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpVoucherActiveStoreLog record);

    int insertSelective(SpVoucherActiveStoreLog record);

    SpVoucherActiveStoreLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpVoucherActiveStoreLog record);

    int updateByPrimaryKey(SpVoucherActiveStoreLog record);
}