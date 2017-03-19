package com.corner.core.dao;

import com.corner.core.beans.StoreWallet;

public interface StoreWalletMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreWallet record);

    int insertSelective(StoreWallet record);

    StoreWallet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreWallet record);

    int updateByPrimaryKey(StoreWallet record);
}