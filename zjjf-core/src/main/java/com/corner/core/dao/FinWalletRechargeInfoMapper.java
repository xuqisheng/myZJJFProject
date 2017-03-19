package com.corner.core.dao;

import com.corner.core.beans.FinWalletRechargeInfo;

public interface FinWalletRechargeInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FinWalletRechargeInfo record);

    int insertSelective(FinWalletRechargeInfo record);

    FinWalletRechargeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinWalletRechargeInfo record);

    int updateByPrimaryKey(FinWalletRechargeInfo record);
}