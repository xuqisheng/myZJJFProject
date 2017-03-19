package com.corner.core.dao;

import com.corner.core.beans.MaWithdrawDealLog;

public interface MaWithdrawDealLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaWithdrawDealLog record);

    int insertSelective(MaWithdrawDealLog record);

    MaWithdrawDealLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MaWithdrawDealLog record);

    int updateByPrimaryKey(MaWithdrawDealLog record);
}