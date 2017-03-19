package com.corner.core.dao;

import com.corner.core.beans.PayErrorRecord;

public interface PayErrorRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayErrorRecord record);

    int insertSelective(PayErrorRecord record);

    PayErrorRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayErrorRecord record);

    int updateByPrimaryKey(PayErrorRecord record);
}