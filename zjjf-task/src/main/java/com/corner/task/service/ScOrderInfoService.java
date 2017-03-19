package com.corner.task.service;

import com.corner.task.beans.msg.ModelMsg;



public interface ScOrderInfoService {
	ModelMsg clearOrder(String taskParams) throws Exception;

	ModelMsg bakSpShoppingCart();
}
