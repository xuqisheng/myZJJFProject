package com.corner.task.tasks;

import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.service.KaiDianBaoService;


public class KaiDianBaoTasks extends TaskBase{

	@Override
	public ModelMsg taskBase(String taskParams) {
		return this.getBean(KaiDianBaoService.class).updateKDBPlantItemPrice();
	}
}
