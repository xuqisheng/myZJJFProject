package com.corner.core.dao;

import com.corner.core.beans.CustomerService;

public interface CustomerServiceMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerService record);

    int insertSelective(CustomerService record);

    CustomerService selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerService record);

    int updateByPrimaryKey(CustomerService record);
}