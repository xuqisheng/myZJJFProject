package com.corner.task.tasks;

import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.service.SKUActiveTaskService;

public class SKUActiveTask extends TaskBase{
	
	@Override
	public ModelMsg taskBase(String taskParams) {
//		try {
//			return this.getBean(SKUActiveTaskService.class).taskSKUActive();
//		} catch (Exception e) {
//			return new ModelMsg(false, e.getMessage());
//		}
		return new ModelMsg(true, "暗号停止单品促销定时任务");
	}
}
