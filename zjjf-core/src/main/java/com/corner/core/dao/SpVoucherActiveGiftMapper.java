package com.corner.core.dao;

import com.corner.core.beans.SpVoucherActiveGift;

public interface SpVoucherActiveGiftMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpVoucherActiveGift record);

    int insertSelective(SpVoucherActiveGift record);

    SpVoucherActiveGift selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpVoucherActiveGift record);

    int updateByPrimaryKey(SpVoucherActiveGift record);
}