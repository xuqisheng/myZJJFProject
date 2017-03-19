package com.corner.core.dao;

import com.corner.core.beans.SpPushMsgMap;

public interface SpPushMsgMapMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpPushMsgMap record);

    int insertSelective(SpPushMsgMap record);

    SpPushMsgMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpPushMsgMap record);

    int updateByPrimaryKey(SpPushMsgMap record);
}