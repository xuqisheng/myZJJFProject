package com.corner.core.dao;

import com.corner.core.beans.SpComment;

public interface SpCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpComment record);

    int insertSelective(SpComment record);

    SpComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpComment record);

    int updateByPrimaryKey(SpComment record);
}