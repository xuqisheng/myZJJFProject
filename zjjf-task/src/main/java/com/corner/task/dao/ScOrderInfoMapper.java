package com.corner.task.dao;

import com.corner.task.beans.ScOrderInfo;

public interface ScOrderInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScOrderInfo record);

    int insertSelective(ScOrderInfo record);

    ScOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScOrderInfo record);

    int updateByPrimaryKey(ScOrderInfo record);
}