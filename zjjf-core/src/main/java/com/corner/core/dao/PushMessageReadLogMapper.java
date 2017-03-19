package com.corner.core.dao;

import com.corner.core.beans.PushMessageReadLog;

public interface PushMessageReadLogMapper {
    int deleteByPrimaryKey(Long msg_lg_id);

    int insert(PushMessageReadLog record);

    int insertSelective(PushMessageReadLog record);

    PushMessageReadLog selectByPrimaryKey(Long msg_lg_id);

    int updateByPrimaryKeySelective(PushMessageReadLog record);

    int updateByPrimaryKey(PushMessageReadLog record);
}