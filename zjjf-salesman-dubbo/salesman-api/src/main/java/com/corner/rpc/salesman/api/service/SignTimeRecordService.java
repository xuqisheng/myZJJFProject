package com.corner.rpc.salesman.api.service;

import com.corner.rpc.salesman.model.SignTimeRecord;

public interface SignTimeRecordService {
	/*
	* 通过id获取数据
	*/
	public SignTimeRecord findSignTimeRecordById(String id) throws Exception;
}
