package com.corner.core.dao;

import com.corner.core.beans.SpActionRecord;

public interface SpActionRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpActionRecord record);

    int insertSelective(SpActionRecord record);

    SpActionRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpActionRecord record);

    int updateByPrimaryKey(SpActionRecord record);
}