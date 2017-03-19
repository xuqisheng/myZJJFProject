package com.corner.core.dao;

import com.corner.core.beans.AcActionRecord;

public interface AcActionRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AcActionRecord record);

    int insertSelective(AcActionRecord record);

    AcActionRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AcActionRecord record);

    int updateByPrimaryKeyWithBLOBs(AcActionRecord record);

    int updateByPrimaryKey(AcActionRecord record);
}