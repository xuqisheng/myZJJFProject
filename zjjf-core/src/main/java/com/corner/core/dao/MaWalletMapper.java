package com.corner.core.dao;

import com.corner.core.beans.MaWallet;

public interface MaWalletMapper {
    int deleteByPrimaryKey(String id);

    int insert(MaWallet record);

    int insertSelective(MaWallet record);

    MaWallet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaWallet record);

    int updateByPrimaryKey(MaWallet record);
}