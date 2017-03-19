package com.corner.core.dao;

import com.corner.core.beans.SpHelp;

public interface SpHelpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpHelp record);

    int insertSelective(SpHelp record);

    SpHelp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpHelp record);

    int updateByPrimaryKey(SpHelp record);
}