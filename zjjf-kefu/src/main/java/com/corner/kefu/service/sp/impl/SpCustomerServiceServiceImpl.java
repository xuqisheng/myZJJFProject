package com.corner.kefu.service.sp.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.CustomerServiceRo;
import com.corner.kefu.beans.vo.sp.CustomerServiceVo;
import com.corner.kefu.dao.sp.SpCustomerServiceMapper;
import com.corner.kefu.service.sp.SpCustomerServiceService;

@Service
public class SpCustomerServiceServiceImpl implements SpCustomerServiceService {
	
	@Autowired
	SpCustomerServiceMapper spCustomerServiceMapper;
	@Override
	public Pager<CustomerServiceVo> getAllCustomerServiceByParam(CustomerServiceRo customerServiceRo) {
		List<CustomerServiceVo> CustomerServiceVoList = spCustomerServiceMapper.getAllCustomerServiceByParam(customerServiceRo);
		int count = spCustomerServiceMapper.getCount(customerServiceRo);
		return new Pager<CustomerServiceVo>(count, CustomerServiceVoList);
	}
	@Override
	public String getPasswordById(String id) {
		// TODO Auto-generated method stub
		return spCustomerServiceMapper.getPasswordById(id);
	}
	@Override
	public void updateLoginPassword(String id, String newPassword) {
		Map<String, Object> upPassParm = new HashMap<String, Object>();
		upPassParm.put("id", id);
		upPassParm.put("password", newPassword);
		upPassParm.put("updateTime", new Date());
		spCustomerServiceMapper.updateLoginPassword(upPassParm);
	}
	@Override
	public List<CustomerService> getAllCustomerService() {
		// TODO Auto-generated method stub
		return spCustomerServiceMapper.getAllCustomerService();
	}

}
