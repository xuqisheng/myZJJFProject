package com.corner.core.dao;

import com.corner.core.beans.FinWalletLog;

public interface FinWalletLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(FinWalletLog record);

    int insertSelective(FinWalletLog record);

    FinWalletLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinWalletLog record);

    int updateByPrimaryKey(FinWalletLog record);
}