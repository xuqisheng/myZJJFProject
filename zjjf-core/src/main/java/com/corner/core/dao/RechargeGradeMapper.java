package com.corner.core.dao;

import com.corner.core.beans.RechargeGrade;

public interface RechargeGradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeGrade record);

    int insertSelective(RechargeGrade record);

    RechargeGrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeGrade record);

    int updateByPrimaryKey(RechargeGrade record);
}