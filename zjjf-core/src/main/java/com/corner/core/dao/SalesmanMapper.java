package com.corner.core.dao;

import com.corner.core.beans.Salesman;

public interface SalesmanMapper {
    int deleteByPrimaryKey(String id);

    int insert(Salesman record);

    int insertSelective(Salesman record);

    Salesman selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Salesman record);

    int updateByPrimaryKey(Salesman record);
}