package com.corner.core.dao;

import com.corner.core.beans.PushMessageType;

public interface PushMessageTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PushMessageType record);

    int insertSelective(PushMessageType record);

    PushMessageType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushMessageType record);

    int updateByPrimaryKey(PushMessageType record);
}