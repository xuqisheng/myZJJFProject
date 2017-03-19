package com.corner.core.dao;

import com.corner.core.beans.SpPushMsg;

public interface SpPushMsgMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpPushMsg record);

    int insertSelective(SpPushMsg record);

    SpPushMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpPushMsg record);

    int updateByPrimaryKeyWithBLOBs(SpPushMsg record);

    int updateByPrimaryKey(SpPushMsg record);
}