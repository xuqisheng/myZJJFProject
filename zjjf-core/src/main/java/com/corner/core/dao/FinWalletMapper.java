package com.corner.core.dao;

import com.corner.core.beans.FinWallet;

public interface FinWalletMapper {
    int deleteByPrimaryKey(String id);

    int insert(FinWallet record);

    int insertSelective(FinWallet record);

    FinWallet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinWallet record);

    int updateByPrimaryKey(FinWallet record);
}