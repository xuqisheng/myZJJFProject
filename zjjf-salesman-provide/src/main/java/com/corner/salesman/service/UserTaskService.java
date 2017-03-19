package com.corner.salesman.service;

import java.util.List;

import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.TaskCycle;
import com.corner.salesman.model.UserTask;

/**  
 * @desc  用户业务层接口
 * 创建时间：2015-1-27 下午5:15:03  
 * @author 元宝  
 * @version 2.2  
 */

public interface UserTaskService {
	
	/**
	 * 根据手机好查询用户信息
	 * @param userTask
	 * @return
	 */
    public List<UserTask> findUserTaskList(UserTask userTask) throws Exception;
    
    /**
     * 任务信息分页查询方法
     * @param page
     * @param userTask
     * @return
     * @throws Exception
     */
    public Page<UserTask> findUser(Page<UserTask> page, UserTask userTask) throws Exception;
    
	/**
	 * 添加用户任务
	 * @param userTask
	 * @return
	 */
    public int insertTaskInfo(UserTask userTask) throws Exception;
    
	/**
	 * 查询任务周期列表
	 * @param taskCycle
	 * @return
	 */
    public List<TaskCycle> findTaskCycleList(TaskCycle taskCycle) throws Exception;
}
