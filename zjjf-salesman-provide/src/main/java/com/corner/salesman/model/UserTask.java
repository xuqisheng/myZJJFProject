package com.corner.salesman.model;

import java.util.Date;
import com.corner.salesman.common.persistence.DataEntity;

public class UserTask extends DataEntity<UserTask>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupId;  //用户组ID
	private String taskId;   //任务ID
	private String taskTopic;//任务主题
	private String taskType; //任务类型
	private String assigns;   //用户ID
	private String cycleIds; //周期ID串
	private String createBy; //创建人
	private String updateBy; //修改建人
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskTopic() {
		return taskTopic;
	}
	public void setTaskTopic(String taskTopic) {
		this.taskTopic = taskTopic;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getAssigns() {
		return assigns;
	}
	public void setAssigns(String assigns) {
		this.assigns = assigns;
	}
	public String getCycleIds() {
		return cycleIds;
	}
	public void setCycleIds(String cycleIds) {
		this.cycleIds = cycleIds;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}