package com.zjjf.analysis.mapper.origin;

import com.zjjf.analysis.beans.analysis.base.CustomerService;

import java.util.HashMap;
import java.util.List;

public interface CustomerServiceMapper {
    
    CustomerService getUserByCustomerServiceCredential(String userName);
    
    public List<CustomerService> getLoginUserList(HashMap<String, Object> paramMap);
}