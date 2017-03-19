package com.corner.core.dao;

import com.corner.core.beans.ScmsUserType;

public interface ScmsUserTypeMapper {
    int insert(ScmsUserType record);

    int insertSelective(ScmsUserType record);
}