package com.corner.task.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.task.beans.Task;
import com.corner.task.beans.TaskLog;
import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.service.TaskService;

public class GTask extends Task implements Runnable{
	public GTask() {
		super();
	}
	public GTask(Task task) {
		super();
		this.setId(task.getId());
		this.setIsDelete(task.getIsDelete());
		this.setStatus(task.getStatus());
		this.setTaskAction(task.getTaskAction());
		this.setTaskClassName(task.getTaskClassName());
		this.setTaskGroup(task.getTaskGroup());
		this.setTaskMethod(task.getTaskMethod());
		this.setTaskName(task.getTaskName());
		this.setTaskParams(task.getTaskParams());
		this.setTaskTrigger(task.getTaskTrigger());
	}
	private final Logger log = LoggerFactory.getLogger(GTask.class);  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void run(){
		TaskLog taskLog = new TaskLog();
		taskLog.setTaskId(this.getId());
		taskLog.setActionTime(new Date());
		taskLog.setStatus(Byte.valueOf("0"));
		taskLog.setRemark("执行成功");
		Object modelMsg = new Object();
		log.info("任务开始执行------taskId:"+this.getId()+"------"+ DateUtil.getDateFormatter());
		try {
			
			if(StringUtil.stringIsNullOrEmpty(this.getTaskClassName() , this.getTaskMethod())){
				String url = PropertiesCacheUtil.getValue(PropertiesCacheUtil.SYSTEM_URL, PropertiesCacheUtil.SYSTEM) + this.getTaskAction();
				log.info("任务开始------------------------------url:" + url);
				modelMsg = HttpRequestUtils.httpPost(url, this.getTaskParams());
				
			}else{
				log.info("任务开始------------------------------class:{}  method:{}  params:{}" , this.getTaskClassName() , this.getTaskMethod(),this.getTaskAction());
				
				if(!StringUtil.stringIsNullOrEmpty(this.getTaskAction())){
					Map map = new HashMap();
					if(!StringUtil.stringIsNullOrEmpty(this.getTaskParams()))
						map = JSONUtil.JSONToObject(this.getTaskParams(), Map.class);
					map.put("url", this.getTaskAction());
					map.put("taskId", this.getId());
					this.setTaskParams(JSONUtil.objectToJSONString(map));
				}
				modelMsg = new LoadMethodEx().Load(this.getTaskClassName(), this.getTaskMethod(), new Object[]{this.getTaskParams()});

				try {
					if(modelMsg instanceof ModelMsg){
						ModelMsg modelMsg2 = (ModelMsg)modelMsg;
						taskLog.setStatus(Byte.valueOf(modelMsg2.isSuccess() ? "0" : "1"));
						taskLog.setRemark(modelMsg2.getMessage());
					}else{
						String message = JSONUtil.objectToJSONString(modelMsg);
						taskLog.setStatus(Byte.valueOf("0"));
						taskLog.setRemark(message);
					}
					SpringContextHolder.getBean(TaskService.class).insertTaskLog(taskLog);
				} catch (Exception e) {
					log.error("任务记录日志异常：{"+e+"} , {}" , e);
				}
			}
		} catch (Exception e) {
			log.error("交易异常:{}" , e);
			taskLog.setStatus(Byte.valueOf("1"));
			taskLog.setRemark(e.toString());
			try {
				SpringContextHolder.getBean(TaskService.class).insertTaskLog(taskLog);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
//		log.info("任务请求发送结束------------------------------ 相应信息:"+JSONUtil.objectToJSONString(object));
		log.info("任务结束执行------------------------------taskId:"+this.getId()+"------" + DateUtil.getDateFormatter());
	}
}