package com.corner.core.dao;

import com.corner.core.beans.ConfigPay;

public interface ConfigPayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConfigPay record);

    int insertSelective(ConfigPay record);

    ConfigPay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConfigPay record);

    int updateByPrimaryKey(ConfigPay record);
}