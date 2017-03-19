package com.corner.core.dao;

import com.corner.core.beans.WhWithdrawDealLog;

public interface WhWithdrawDealLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WhWithdrawDealLog record);

    int insertSelective(WhWithdrawDealLog record);

    WhWithdrawDealLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WhWithdrawDealLog record);

    int updateByPrimaryKey(WhWithdrawDealLog record);
}