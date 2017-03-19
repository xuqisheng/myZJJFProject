package com.corner.core.dao;

import com.corner.core.beans.TagItemMapKey;

public interface TagItemMapMapper {
    int deleteByPrimaryKey(TagItemMapKey key);

    int insert(TagItemMapKey record);

    int insertSelective(TagItemMapKey record);
}