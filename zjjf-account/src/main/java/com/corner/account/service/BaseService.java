package com.corner.account.service;

import com.corner.core.beans.msg.ModelMsg;


public interface BaseService {

	ModelMsg deleteObjects(String tableName,String[] array);
	
}
