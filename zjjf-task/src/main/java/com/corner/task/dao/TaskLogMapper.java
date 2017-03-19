package com.corner.task.dao;

import com.corner.task.beans.TaskLog;

public interface TaskLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskLog record);

    int insertSelective(TaskLog record);

    TaskLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskLog record);

    int updateByPrimaryKey(TaskLog record);
}