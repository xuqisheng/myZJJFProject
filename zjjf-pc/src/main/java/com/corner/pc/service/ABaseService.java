package com.corner.pc.service;

import com.corner.pc.beans.vo.ModelMsg;


public interface ABaseService {

	ModelMsg deleteObjects(String tableName,String[] array);
	
}
