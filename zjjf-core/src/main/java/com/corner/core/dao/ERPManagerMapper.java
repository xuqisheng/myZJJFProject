package com.corner.core.dao;

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.ERPManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPManagerMapper {
    int countByExample(ERPManagerExample example);

    int deleteByExample(ERPManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPManager record);

    int insertSelective(ERPManager record);

    List<ERPManager> selectByExample(ERPManagerExample example);

    ERPManager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPManager record, @Param("example") ERPManagerExample example);

    int updateByExample(@Param("record") ERPManager record, @Param("example") ERPManagerExample example);

    int updateByPrimaryKeySelective(ERPManager record);

    int updateByPrimaryKey(ERPManager record);
}