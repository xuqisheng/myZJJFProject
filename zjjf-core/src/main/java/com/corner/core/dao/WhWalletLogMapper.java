package com.corner.core.dao;

import com.corner.core.beans.WhWalletLog;

public interface WhWalletLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WhWalletLog record);

    int insertSelective(WhWalletLog record);

    WhWalletLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WhWalletLog record);

    int updateByPrimaryKey(WhWalletLog record);
}