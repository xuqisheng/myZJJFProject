package com.zjjf.analysis.mapper.analysis;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;

import java.util.List;

public interface AnaDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnaDictionary record);

    int insertSelective(AnaDictionary record);

    AnaDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnaDictionary record);

    int updateByPrimaryKey(AnaDictionary record);
    
    List<AnaDictionary> getByDictId(String dictId);
}