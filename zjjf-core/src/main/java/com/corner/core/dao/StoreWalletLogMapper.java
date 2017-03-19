package com.corner.core.dao;

import com.corner.core.beans.StoreWalletLog;

public interface StoreWalletLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(StoreWalletLog record);

    int insertSelective(StoreWalletLog record);

    StoreWalletLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StoreWalletLog record);

    int updateByPrimaryKey(StoreWalletLog record);
}