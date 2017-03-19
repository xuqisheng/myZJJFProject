package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.TaskCycle;
import com.corner.salesman.model.UserTask;

@MyBatisDao
public interface UserTaskMapper extends CrudDao<UserTask> {

	/**
	 * 根据手机好查询用户信息
	 * @param userTask
	 * @return
	 */
    public List<UserTask> findUserTaskList(UserTask userTask);
    
	/**
	 * 添加用户任务
	 * @param userTask
	 * @return
	 */
    public int insertTaskInfo(UserTask userTask);
    
	/**
	 * 查询任务周期列表
	 * @param taskCycle
	 * @return
	 */
    public List<TaskCycle> findTaskCycleList(TaskCycle taskCycle);
}