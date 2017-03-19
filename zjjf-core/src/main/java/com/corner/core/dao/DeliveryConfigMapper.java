package com.corner.core.dao;

import com.corner.core.beans.DeliveryConfig;

public interface DeliveryConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryConfig record);

    int insertSelective(DeliveryConfig record);

    DeliveryConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryConfig record);

    int updateByPrimaryKey(DeliveryConfig record);
}