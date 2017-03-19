package com.corner.core.dao;

import com.corner.core.beans.WaitPush;

public interface WaitPushMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaitPush record);

    int insertSelective(WaitPush record);

    WaitPush selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaitPush record);

    int updateByPrimaryKey(WaitPush record);
}