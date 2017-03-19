package com.corner.core.dao;

import com.corner.core.beans.PaySuccessRecord;

public interface PaySuccessRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaySuccessRecord record);

    int insertSelective(PaySuccessRecord record);

    PaySuccessRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaySuccessRecord record);

    int updateByPrimaryKey(PaySuccessRecord record);
}