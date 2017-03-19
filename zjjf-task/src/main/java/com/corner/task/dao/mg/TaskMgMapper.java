package com.corner.task.dao.mg;

import java.util.List;
import java.util.Map;

import com.corner.task.beans.Task;
import com.corner.task.beans.ro.TaskRo;

public interface TaskMgMapper {
	public List<Task> getTaskListPage(TaskRo command);
	public Integer getTaskListPageCount(TaskRo command);
	public List<Map<String, Object>> getTaskLogPage(TaskRo command);
	public Integer getTaskLogPageCount(TaskRo command);
}