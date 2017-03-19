package com.corner.core.dao;

import com.corner.core.beans.SpPushMsgType;

public interface SpPushMsgTypeMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(SpPushMsgType record);

    int insertSelective(SpPushMsgType record);

    SpPushMsgType selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(SpPushMsgType record);

    int updateByPrimaryKey(SpPushMsgType record);
}