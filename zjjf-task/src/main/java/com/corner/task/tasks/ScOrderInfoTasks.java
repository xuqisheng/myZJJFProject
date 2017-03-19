package com.corner.task.tasks;

import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.service.ScOrderInfoService;


public class ScOrderInfoTasks extends TaskBase{

	@Override
	public ModelMsg taskBase(String taskParams) {
		try {
			return this.getBean(ScOrderInfoService.class).clearOrder(taskParams);
		} catch (Exception e) {
			return new ModelMsg(false, e.getMessage());
		}
	}
}
