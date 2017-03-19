package com.corner.core.dao;

import com.corner.core.beans.ActionRecord;

public interface ActionRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActionRecord record);

    int insertSelective(ActionRecord record);

    ActionRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActionRecord record);

    int updateByPrimaryKey(ActionRecord record);
}