package com.corner.core.dao;

import com.corner.core.beans.ScOrderInfo;

public interface ScOrderInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScOrderInfo record);

    int insertSelective(ScOrderInfo record);

    ScOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScOrderInfo record);

    int updateByPrimaryKey(ScOrderInfo record);
}