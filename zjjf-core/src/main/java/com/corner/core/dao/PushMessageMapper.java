package com.corner.core.dao;

import com.corner.core.beans.PushMessage;

public interface PushMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(PushMessage record);

    int insertSelective(PushMessage record);

    PushMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PushMessage record);

    int updateByPrimaryKey(PushMessage record);
}