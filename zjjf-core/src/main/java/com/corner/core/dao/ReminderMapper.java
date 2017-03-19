package com.corner.core.dao;

import com.corner.core.beans.Reminder;

public interface ReminderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Reminder record);

    int insertSelective(Reminder record);

    Reminder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Reminder record);

    int updateByPrimaryKey(Reminder record);
}