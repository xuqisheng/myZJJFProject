package com.corner.core.dao;

import com.corner.core.beans.JoinInfo;

public interface JoinInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(JoinInfo record);

    int insertSelective(JoinInfo record);

    JoinInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JoinInfo record);

    int updateByPrimaryKey(JoinInfo record);
}