package com.corner.core.dao;

import com.corner.core.beans.MaWalletLog;

public interface MaWalletLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaWalletLog record);

    int insertSelective(MaWalletLog record);

    MaWalletLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaWalletLog record);

    int updateByPrimaryKey(MaWalletLog record);
}