package com.corner.core.dao;

import com.corner.core.beans.ERPManagerContract;

public interface ERPManagerContractMapper {
    int deleteByPrimaryKey(String id);

    int insert(ERPManagerContract record);

    int insertSelective(ERPManagerContract record);

    ERPManagerContract selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPManagerContract record);

    int updateByPrimaryKey(ERPManagerContract record);
}