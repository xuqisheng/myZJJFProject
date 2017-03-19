package com.corner.core.dao;

import com.corner.core.beans.SpSearchLog;

public interface SpSearchLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpSearchLog record);

    int insertSelective(SpSearchLog record);

    SpSearchLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpSearchLog record);

    int updateByPrimaryKey(SpSearchLog record);
}