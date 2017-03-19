package com.corner.core.dao;

import com.corner.core.beans.SignResult;

public interface SignResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SignResult record);

    int insertSelective(SignResult record);

    SignResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SignResult record);

    int updateByPrimaryKey(SignResult record);
}