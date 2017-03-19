package com.corner.task.tasks;

import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.service.SpVoucherActiveService;

public class SpVoucherActiveTasks extends TaskBase {

	@Override
	public ModelMsg taskBase(String taskParams) {
		return this.getBean(SpVoucherActiveService.class).updateSpVoucherActive();
	}

}
