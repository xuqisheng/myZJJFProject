package com.corner.task.tasks;

import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.service.ScOrderInfoService;


public class BakSpShoppingCartTasks extends TaskBase{

	@Override
	public ModelMsg taskBase(String taskParams) {
		return this.getBean(ScOrderInfoService.class).bakSpShoppingCart();
	}
}
