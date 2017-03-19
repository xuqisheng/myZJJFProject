package com.corner.scms.dao;

import com.corner.scms.beans.CustomerService;

public interface CustomerServiceMgMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerService record);

    int insertSelective(CustomerService record);

    CustomerService selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerService record);

    int updateByPrimaryKey(CustomerService record);
    
    CustomerService getUserByCustomerServiceCredential(String userName);
}