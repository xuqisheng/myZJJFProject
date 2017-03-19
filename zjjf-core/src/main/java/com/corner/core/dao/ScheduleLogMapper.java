package com.corner.core.dao;

import com.corner.core.beans.ScheduleLog;

public interface ScheduleLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleLog record);

    int insertSelective(ScheduleLog record);

    ScheduleLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScheduleLog record);

    int updateByPrimaryKey(ScheduleLog record);
}