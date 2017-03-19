package com.corner.auth.dao;

import com.corner.auth.beans.CustomerService;

public interface CustomerServiceMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerService record);

    int insertSelective(CustomerService record);

    CustomerService selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerService record);

    int updateByPrimaryKey(CustomerService record);
}