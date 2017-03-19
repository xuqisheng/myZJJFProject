package com.corner.core.dao;

import com.corner.core.beans.SpAdminVerifyRecord;

public interface SpAdminVerifyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpAdminVerifyRecord record);

    int insertSelective(SpAdminVerifyRecord record);

    SpAdminVerifyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpAdminVerifyRecord record);

    int updateByPrimaryKey(SpAdminVerifyRecord record);
}