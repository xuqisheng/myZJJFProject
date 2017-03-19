package com.corner.core.dao;

import com.corner.core.beans.PlantWallet;

public interface PlantWalletMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlantWallet record);

    int insertSelective(PlantWallet record);

    PlantWallet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlantWallet record);

    int updateByPrimaryKey(PlantWallet record);
}