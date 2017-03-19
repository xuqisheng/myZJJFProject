package com.corner.scms.service.sc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Pay;
import com.corner.core.dao.PayMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sc.ScmsPayService;
@Service
public class ScmsPayServiceImpl extends BaseServiceImpl implements ScmsPayService {

	@Autowired
	PayMapper payMapper;
	
	@Override
	public void savePayRecord(Pay pay) throws Exception {
		payMapper.insertSelective(pay);
	}

}
