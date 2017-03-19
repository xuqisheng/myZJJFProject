package com.corner.scms.service;

import com.corner.core.beans.msg.ModelMsg;


public interface BaseService {

	ModelMsg deleteObjects(String tableName,String[] array);
	
}
