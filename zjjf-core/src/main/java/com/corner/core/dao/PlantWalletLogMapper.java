package com.corner.core.dao;

import com.corner.core.beans.PlantWalletLog;

public interface PlantWalletLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlantWalletLog record);

    int insertSelective(PlantWalletLog record);

    PlantWalletLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlantWalletLog record);

    int updateByPrimaryKey(PlantWalletLog record);
}