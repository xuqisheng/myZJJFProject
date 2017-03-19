package com.corner.core.dao;

import com.corner.core.beans.SpFeedback;

public interface SpFeedbackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpFeedback record);

    int insertSelective(SpFeedback record);

    SpFeedback selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpFeedback record);

    int updateByPrimaryKey(SpFeedback record);
}