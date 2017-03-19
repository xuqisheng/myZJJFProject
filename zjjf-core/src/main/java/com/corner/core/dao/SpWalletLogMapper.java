package com.corner.core.dao;

import com.corner.core.beans.SpWalletLog;

public interface SpWalletLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpWalletLog record);

    int insertSelective(SpWalletLog record);

    SpWalletLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpWalletLog record);

    int updateByPrimaryKey(SpWalletLog record);
}