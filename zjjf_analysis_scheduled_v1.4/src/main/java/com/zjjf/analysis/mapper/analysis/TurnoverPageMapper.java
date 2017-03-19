package com.zjjf.analysis.mapper.analysis;

import com.zjjf.analysis.beans.analysis.base.TurnoverPage;

public interface TurnoverPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TurnoverPage record);

    int insertSelective(TurnoverPage record);

    TurnoverPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TurnoverPage record);

    int updateByPrimaryKey(TurnoverPage record);
}