package com.corner.core.dao;

import com.corner.core.beans.WhWallet;

public interface WhWalletMapper {
    int deleteByPrimaryKey(String id);

    int insert(WhWallet record);

    int insertSelective(WhWallet record);

    WhWallet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WhWallet record);

    int updateByPrimaryKey(WhWallet record);
}