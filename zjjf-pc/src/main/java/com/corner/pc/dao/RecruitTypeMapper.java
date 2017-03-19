package com.corner.pc.dao;

import com.corner.pc.beans.RecruitType;

public interface RecruitTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecruitType record);

    int insertSelective(RecruitType record);

    RecruitType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecruitType record);

    int updateByPrimaryKey(RecruitType record);
}