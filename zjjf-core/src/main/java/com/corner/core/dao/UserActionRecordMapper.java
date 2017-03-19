package com.corner.core.dao;

import com.corner.core.beans.UserActionRecord;

public interface UserActionRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserActionRecord record);

    int insertSelective(UserActionRecord record);

    UserActionRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserActionRecord record);

    int updateByPrimaryKey(UserActionRecord record);
}