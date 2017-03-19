package com.corner.task.dao;

import com.corner.task.beans.SpWalletLog;

public interface SpWalletLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpWalletLog record);

    int insertSelective(SpWalletLog record);

    SpWalletLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpWalletLog record);

    int updateByPrimaryKey(SpWalletLog record);
}