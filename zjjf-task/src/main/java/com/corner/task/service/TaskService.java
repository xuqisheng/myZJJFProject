package com.corner.task.service;

import java.util.Map;

import com.corner.task.beans.Task;
import com.corner.task.beans.TaskLog;
import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.beans.ro.TaskRo;
import com.corner.task.beans.vo.Pager;
import com.corner.task.util.GTask;

public interface TaskService{
	Pager<Task>  getTaskListPage(TaskRo command);
	Pager<Map<String, Object>>  getTaskLogPage(TaskRo command);
	
	/** 
	 * 启动一个计划任务. 
	 * @param task 当前进行的任务. 
	 */  
	public ModelMsg start(GTask gTask)  throws Exception;
	/** 
	 * 启动一个计划任务. 
	 * @param task 当前进行的任务. 
	 */  
	public void startAll()  throws Exception;

	/** 
	 * 停止一个计划任务. 
	 * @param taskId 任务编号. 
	 */  
	public ModelMsg stop(String taskId) throws Exception;
	/** 
	 * 获得任务. 
	 * @param taskId 任务编号. 
	 */  
	public Task getTaskById(String taskId) throws Exception;

	/** 
	 * 重新设置当前任务的执行频率. 
	 *  
	 * @param taskId 
	 *            任务编号. 
	 */  
	public ModelMsg resetTrigger(String taskId, String cronExpression)  throws Exception; 

	/** 
	 * 仅执行一次. 
	 *  
	 * @param task 所要执行任务. 
	 */  
	public ModelMsg onlyOneTime(GTask task)  throws Exception;

	/** 
	 * 销毁线程池中的任务. 
	 */  
	public void destrory() throws Exception;
	
	
	public ModelMsg updateTask(Task task) throws Exception;
	public ModelMsg insertTask(Task task) throws Exception;
	
	public ModelMsg insertTaskLog(TaskLog taskLog);
}
