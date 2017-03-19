package com.corner.core.dao;

import com.corner.core.beans.WithdrawDealLog;

public interface WithdrawDealLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawDealLog record);

    int insertSelective(WithdrawDealLog record);

    WithdrawDealLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WithdrawDealLog record);

    int updateByPrimaryKey(WithdrawDealLog record);
}