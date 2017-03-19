package com.corner.core.dao;

import com.corner.core.beans.Help;

public interface HelpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Help record);

    int insertSelective(Help record);

    Help selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Help record);

    int updateByPrimaryKey(Help record);
}