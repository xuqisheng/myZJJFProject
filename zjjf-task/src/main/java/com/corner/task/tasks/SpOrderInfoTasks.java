package com.corner.task.tasks;

import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.service.SpOrderInfoService;


public class SpOrderInfoTasks extends TaskBase{

	@Override
	public ModelMsg taskBase(String taskParams) {
		return this.getBean(SpOrderInfoService.class).clearOrder(taskParams);
	}
}
