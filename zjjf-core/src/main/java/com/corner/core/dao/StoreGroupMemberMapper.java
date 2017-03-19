package com.corner.core.dao;

import java.util.List;

import com.corner.core.beans.StoreGroup;
import com.corner.core.beans.StoreGroupMember;

public interface StoreGroupMemberMapper {
    int deleteByPrimaryKey(String id);

    int insert(StoreGroupMember record);

    int insertSelective(StoreGroupMember record);

    StoreGroupMember selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StoreGroupMember record);

    int updateByPrimaryKey(StoreGroupMember record);

	

}