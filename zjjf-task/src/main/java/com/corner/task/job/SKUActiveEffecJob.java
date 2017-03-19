package com.corner.task.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.task.beans.SKUActive;
import com.corner.task.service.SKUActiveTaskService;
import com.corner.task.util.JSONUtil;

@Service
public class SKUActiveEffecJob implements Job {

	@Autowired
	SKUActiveTaskService taskService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap map = context.getMergedJobDataMap();
		System.out.println(JSONUtil.objectToJSONString(map));
		SKUActive active = null;
		Object obj = map.get("SKUActive");
		if(obj != null){
			active = (SKUActive)obj;
			if(active.getStatus().intValue() == 1){
				taskService.taskEffecNow(active);
			}else if(active.getStatus().intValue() == 2){
				taskService.taskInvalidNow(active);
			}
		}
	}

}
