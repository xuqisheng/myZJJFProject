package com.corner.rpc.salesman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.salesman.api.service.SignTimeRecordService;
import com.corner.rpc.salesman.dao.SignTimeRecordMapper;
import com.corner.rpc.salesman.model.SignTimeRecord;

@Service("signTimeRecordService")
public class SignTimeRecordServiceImpl implements SignTimeRecordService {

	@Autowired
	private SignTimeRecordMapper signTimeRecordMapper;
	
	@Override
	public SignTimeRecord findSignTimeRecordById(String id) throws Exception {
		
		return signTimeRecordMapper.selectByPrimaryKey(id);
	}

}
