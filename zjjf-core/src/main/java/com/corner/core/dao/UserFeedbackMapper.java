package com.corner.core.dao;

import com.corner.core.beans.UserFeedback;

public interface UserFeedbackMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserFeedback record);

    int insertSelective(UserFeedback record);

    UserFeedback selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserFeedback record);

    int updateByPrimaryKey(UserFeedback record);
}