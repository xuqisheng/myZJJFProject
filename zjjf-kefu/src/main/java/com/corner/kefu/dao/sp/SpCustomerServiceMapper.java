package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.CustomerService;
import com.corner.kefu.beans.ro.sp.CustomerServiceRo;
import com.corner.kefu.beans.vo.sp.CustomerServiceVo;

public interface SpCustomerServiceMapper {

	List<CustomerServiceVo> getAllCustomerServiceByParam(CustomerServiceRo customerServiceRo);

	int getCount(CustomerServiceRo customerServiceRo);

	String getPasswordById(String id);

	void updateLoginPassword(Map<String, Object> upPassParm);

	List<CustomerService> getAllCustomerService();

}
