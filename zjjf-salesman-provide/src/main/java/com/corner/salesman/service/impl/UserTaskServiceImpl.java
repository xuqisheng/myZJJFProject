package com.corner.salesman.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.dao.UserTaskMapper;
import com.corner.salesman.model.TaskCycle;
import com.corner.salesman.model.UserTask;
import com.corner.salesman.service.UserTaskService;

/**
 * 创建时间：2015-1-27 下午5:22:59
 * 
 * @author andy
 * @version 2.2
 */
@Service("userTaskService")
@Transactional(readOnly = true)
public class UserTaskServiceImpl implements UserTaskService {
	
	@Autowired
	private UserTaskMapper userTaskMapper;
	@Autowired
	//private GroupMapper groupMapper;
	private UserDeptMapper userDeptMapper;

	/**
	 * 根据条件查询任务列表信息
	 * @param userTask
	 * @return
	 */
	@Override
	public List<UserTask> findUserTaskList(UserTask userTask) throws Exception {
		return userTaskMapper.findUserTaskList(userTask);
	}
	
    /**
     * 任务信息分页查询方法
     * @param page
     * @param userTask
     * @return
     * @throws Exception
     */
	public Page<UserTask> findUser(Page<UserTask> page, UserTask userTask) throws Exception{
		// 设置分页参数
		userTask.setPage(page);
		// 执行分页查询
		List<UserTask> list = userTaskMapper.findUserTaskList(userTask);
		page.setList(list);
		return page;
	}

    
	/**
	 * 添加用户任务
	 * @param userTask
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public int insertTaskInfo(UserTask userTask) throws Exception {
		//1、根据创建人找到组ID
		/*String leaderId = userTask.getUserId();
		Group group = groupMapper.findGroupInfo(leaderId);
		if(null != group){
			String groupId = group.getGroupId();
			//2、将查询到的组ID设置到新增任务中
			userTask.setGroupId(groupId);
		}*/
    	String deptId = userDeptMapper.findDeptIdByUserId(userTask.getUserId());
    	if(StringUtils.isNotBlank(deptId)){
    		userTask.setGroupId(deptId);
    	}
		userTask.setCreateBy(userTask.getUserId());
		userTask.setCreateTime(DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT));
		return userTaskMapper.insertTaskInfo(userTask);
	}

	/**
	 * 查询任务周期列表
	 * @param taskCycle
	 * @return
	 */
	@Override
	public List<TaskCycle> findTaskCycleList(TaskCycle taskCycle) throws Exception {
		return userTaskMapper.findTaskCycleList(taskCycle);
	}
	
	
}
