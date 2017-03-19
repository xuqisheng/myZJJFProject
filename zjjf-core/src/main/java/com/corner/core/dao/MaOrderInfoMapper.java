package com.corner.core.dao;

import com.corner.core.beans.MaOrderInfo;

public interface MaOrderInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(MaOrderInfo record);

    int insertSelective(MaOrderInfo record);

    MaOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaOrderInfo record);

    int updateByPrimaryKey(MaOrderInfo record);
}