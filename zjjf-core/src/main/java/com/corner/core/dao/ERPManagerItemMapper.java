package com.corner.core.dao;

import com.corner.core.beans.ERPManagerItem;
import com.corner.core.beans.ERPManagerItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPManagerItemMapper {
    int countByExample(ERPManagerItemExample example);

    int deleteByExample(ERPManagerItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPManagerItem record);

    int insertSelective(ERPManagerItem record);

    List<ERPManagerItem> selectByExample(ERPManagerItemExample example);

    ERPManagerItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPManagerItem record, @Param("example") ERPManagerItemExample example);

    int updateByExample(@Param("record") ERPManagerItem record, @Param("example") ERPManagerItemExample example);

    int updateByPrimaryKeySelective(ERPManagerItem record);

    int updateByPrimaryKey(ERPManagerItem record);
}