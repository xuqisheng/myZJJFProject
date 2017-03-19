package com.corner.task.beans.ro;


public class TaskRo extends AmazeUIListRo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6400737022096104144L;
	private String taskName;
	private String taskId;
	private Integer type;	//2 all ，1-启用状态的

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}
