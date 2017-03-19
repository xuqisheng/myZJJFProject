package com.corner.core.dao;

import com.corner.core.beans.UserRefund;

public interface UserRefundMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRefund record);

    int insertSelective(UserRefund record);

    UserRefund selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRefund record);

    int updateByPrimaryKey(UserRefund record);
}