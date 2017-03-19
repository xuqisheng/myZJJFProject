package com.corner.task.dao;

import com.corner.task.beans.PlantWalletLog;

public interface PlantWalletLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlantWalletLog record);

    int insertSelective(PlantWalletLog record);

    PlantWalletLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlantWalletLog record);

    int updateByPrimaryKey(PlantWalletLog record);
}