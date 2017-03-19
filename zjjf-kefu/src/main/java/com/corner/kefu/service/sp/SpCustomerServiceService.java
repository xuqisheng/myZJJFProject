package com.corner.kefu.service.sp;

import java.util.List;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.CustomerServiceRo;
import com.corner.kefu.beans.vo.sp.CustomerServiceVo;

public interface SpCustomerServiceService {

	Pager<CustomerServiceVo> getAllCustomerServiceByParam(CustomerServiceRo customerServiceRo);

	String getPasswordById(String id);

	void updateLoginPassword(String id, String stringToMd5);

	List<CustomerService> getAllCustomerService();

}
