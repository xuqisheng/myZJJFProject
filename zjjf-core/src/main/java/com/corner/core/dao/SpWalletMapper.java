package com.corner.core.dao;

import com.corner.core.beans.SpWallet;

public interface SpWalletMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpWallet record);

    int insertSelective(SpWallet record);

    SpWallet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpWallet record);

    int updateByPrimaryKey(SpWallet record);
}