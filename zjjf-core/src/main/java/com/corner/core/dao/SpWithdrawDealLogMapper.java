package com.corner.core.dao;

import com.corner.core.beans.SpWithdrawDealLog;

public interface SpWithdrawDealLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpWithdrawDealLog record);

    int insertSelective(SpWithdrawDealLog record);

    SpWithdrawDealLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpWithdrawDealLog record);

    int updateByPrimaryKey(SpWithdrawDealLog record);
}