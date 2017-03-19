package com.corner.task.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.corner.task.beans.Task;
import com.corner.task.beans.TaskLog;
import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.beans.ro.TaskRo;
import com.corner.task.beans.vo.Pager;
import com.corner.task.dao.TaskLogMapper;
import com.corner.task.dao.TaskMapper;
import com.corner.task.dao.mg.TaskMgMapper;
import com.corner.task.service.TaskService;
import com.corner.task.util.GTask;
import com.corner.task.util.JSONUtil;
import com.corner.task.util.RunTask;
import com.corner.task.util.StringUtil;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	TaskMapper taskMapper;
	@Autowired
	TaskLogMapper taskLogMapper;
	@Autowired
	TaskMgMapper taskMgMapper;
	@Autowired
	RunTask runTask;

	private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
	private final static int POOL_SIZE = 64;  
	private final ConcurrentTaskScheduler ct = new ConcurrentTaskScheduler(Executors.newScheduledThreadPool(POOL_SIZE));  
	@Override
	public Pager<Task> getTaskListPage(TaskRo command) {
		List<Task> list = taskMgMapper.getTaskListPage(command);
		Integer count = taskMgMapper.getTaskListPageCount(command);
		return new Pager<Task>(count, list);
	}  
	@Override
	public Pager<Map<String, Object>> getTaskLogPage(TaskRo command) {
		List<Map<String, Object>> list = taskMgMapper.getTaskLogPage(command);
		Integer count = taskMgMapper.getTaskLogPageCount(command);
		return new Pager<Map<String , Object>>(count, list);
	}
	/** 
	 * 启动一个计划任务. 
	 * @param task 当前进行的任务. 
	 */  
	@Override
	public ModelMsg start(GTask gTask)  throws Exception{ 
		if (StringUtil.stringIsNullOrEmpty(gTask.getId())) {  
			throw new Exception("the taskid must be not empty.");  
		}else if (StringUtil.stringIsNullOrEmpty(gTask.getTaskTrigger())) {  
			throw new Exception("任务的调度表达式不能为空.");  
		}else if (StringUtil.stringIsNullOrEmpty(gTask.getTaskName())) {  
			throw new Exception("任务的调度名称不能为空.");  
		}else if (StringUtil.stringIsNullOrEmpty(gTask.getTaskAction()) && StringUtil.stringIsNullOrEmpty(gTask.getTaskClassName() , gTask.getTaskMethod())) {  
			throw new Exception("任务的调度请求或者调度类方法不能为空.");  
		}
		ScheduledFuture<?> scheduledFuture = ct.schedule(gTask, new CronTrigger(gTask.getTaskTrigger()));  
		runTask.SCHEDULED_FUTURE.put(gTask.getId(), scheduledFuture);  
		runTask.TASKS.put(gTask.getId(), gTask);  
		log.info("the task with " + gTask.getId() + "has bean already started.");
		return new ModelMsg(true, "新增成功");
	}  
	@Override
	public void startAll() throws Exception {
		try {
			TaskRo command = new TaskRo();
			command.setType(1);
			command.setPageSize(1000);
			List<Task> tasks = taskMgMapper.getTaskListPage(command);
			for (Task task : tasks) {
				GTask gTask = new GTask(task);
				ScheduledFuture<?> scheduledFuture = ct.schedule(gTask, new CronTrigger(gTask.getTaskTrigger()));  
				runTask.SCHEDULED_FUTURE.put(gTask.getId(), scheduledFuture);  
				runTask.TASKS.put(gTask.getId(), gTask);  
				log.info("the task with " + gTask.getId() + "has bean already started.");
			}
		} catch (Exception e) {  
			log.info(null, e);  
			throw new Exception(e);  
		} 
	}
	/** 
	 * 停止一个计划任务. 
	 * @param taskId 任务编号. 
	 */  
	@Override
	public ModelMsg stop(String taskId) throws Exception{  
		log.info("正在停止任务 " + taskId);  
		if (StringUtils.isEmpty(taskId)) {  
			throw new Exception("the taskid must be not empty.");  
		}  

		try {  
			ScheduledFuture<?> scheduledFuture = runTask.SCHEDULED_FUTURE.remove(taskId);  
			if (scheduledFuture == null) {  
				return new ModelMsg(true, "修改成功");
			} else {  
				if (!scheduledFuture.isCancelled()) {  
					/** false 表示当前任务若正在执行，则待其执行结束，再结束此任务. */  
					scheduledFuture.cancel(false);  
				}  
			}  
			return new ModelMsg(true, "修改成功");
		} catch (Exception e) {  
			log.info(null, e);  
			throw new Exception(e);  
		}
	}  

	/** 
	 * 重新设置当前任务的执行频率. 
	 *  
	 * @param taskId 
	 *            任务编号. 
	 */  
	@Override
	public ModelMsg resetTrigger(String taskId, String cronExpression)  throws Exception{  
		log.info("正在修改当前任务 " + taskId + "执行频率.");  
		if (StringUtils.isEmpty(taskId)) {  
			throw new Exception("the taskid must be not empty.");  
		}  

		if (StringUtils.isEmpty(cronExpression)) {  
			throw new Exception("任务的调度表达式不能为空.");  
		}  

		GTask task = runTask.TASKS.get(taskId);  
		if (task != null) {  
			if (cronExpression.equals(task.getTaskTrigger())) {  
				return new ModelMsg(true, "修改成功");
			}  
			/** first, stop the task. */  
			ScheduledFuture<?> scheduledFuture = runTask.SCHEDULED_FUTURE.remove(taskId);  
			scheduledFuture.cancel(false);  

			/** second, reset the task with cronExpression. */  
			task.setTaskTrigger(cronExpression);  
			/** third, restart the task. */  
			scheduledFuture = ct.schedule(task, new CronTrigger(cronExpression));  
			runTask.SCHEDULED_FUTURE.put(taskId, scheduledFuture);  
		}
		return new ModelMsg(true, "修改成功");
	}  

	/** 
	 * 仅执行一次. 
	 *  
	 * @param task 所要执行任务. 
	 */  
	@Override
	public ModelMsg onlyOneTime(GTask task)  throws Exception{  
		if (StringUtils.isEmpty(task.getId())) {  
			throw new Exception("the taskid must be not empty.");  
		}  
		ct.execute(task, 2000);  
		return new ModelMsg(true, "修改成功");
	}  

	/** 
	 * 销毁线程池中的任务. 
	 */  
	@Override
	public void destrory() throws Exception{  
		log.info("正在终止自动任务的线程池资源.");  
		ScheduledExecutorService scheduledExecutor = (ScheduledExecutorService) ct.getConcurrentExecutor();  
		try {  
			scheduledExecutor.shutdownNow();  
		} catch (Exception e) {  
			log.info("自动任务的线程池资源清理发生异常.", e);  
		} finally {  
			log.info("自动任务的线程池资源清理完成.");  
		}  
	}  

	@Override
	public Task getTaskById(String taskId) throws Exception {
		return taskMapper.selectByPrimaryKey(taskId);
	}

	@Override
	public ModelMsg updateTask(Task task) throws Exception{
		ModelMsg modelMsg = new ModelMsg(true, "任务修改成功");
		int result = taskMapper.updateByPrimaryKeySelective(task);
		modelMsg = stop(task.getId());
		if(task.getStatus() == 1){
			modelMsg = start(new GTask(taskMapper.selectByPrimaryKey(task.getId())));
		}
		return modelMsg;
	}

	@Override
	public ModelMsg insertTask(Task task) throws Exception {
		if (StringUtil.stringIsNullOrEmpty(task.getId())) {  
			throw new Exception("the taskid must be not empty.");  
		}else if (StringUtil.stringIsNullOrEmpty(task.getTaskTrigger())) {  
			throw new Exception("任务的调度表达式不能为空");  
		}else if (StringUtil.stringIsNullOrEmpty(task.getTaskName())) {  
			throw new Exception("任务的调度名称不能为空");  
		}else if (StringUtil.stringIsNullOrEmpty(task.getTaskAction()) && StringUtil.stringIsNullOrEmpty(task.getTaskClassName() , task.getTaskMethod())) {  
			throw new Exception("任务的调度请求或者调度类方法不能为空");  
		}else if(!StringUtil.stringIsNullOrEmpty(task.getTaskParams())){
			try {
				Map map = JSONUtil.JSONToObject(task.getTaskParams(), Map.class);
			} catch (Exception e) {
				throw new Exception("任务的参数不正确：请输入JSON格式的数据");
			}
		}
		
		int result = taskMapper.insertSelective(task);
		if(result == 0)
			return new ModelMsg(false, "新增失败");
		return new ModelMsg(true, "新增成功");
		
	}

	@Override
	public ModelMsg insertTaskLog(TaskLog taskLog){
		if(taskLogMapper.insertSelective(taskLog) == 0){
			return new ModelMsg(false, "日志添加失败");
		}
		return new ModelMsg(true, "日志添加成功");
	}
}
