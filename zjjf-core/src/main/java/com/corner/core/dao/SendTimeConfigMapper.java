package com.corner.core.dao;

import com.corner.core.beans.SendTimeConfig;
import com.corner.core.beans.SendTimeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendTimeConfigMapper {
    int countByExample(SendTimeConfigExample example);

    int deleteByExample(SendTimeConfigExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(SendTimeConfig record);

    int insertSelective(SendTimeConfig record);

    List<SendTimeConfig> selectByExample(SendTimeConfigExample example);

    SendTimeConfig selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") SendTimeConfig record, @Param("example") SendTimeConfigExample example);

    int updateByExample(@Param("record") SendTimeConfig record, @Param("example") SendTimeConfigExample example);

    int updateByPrimaryKeySelective(SendTimeConfig record);

    int updateByPrimaryKey(SendTimeConfig record);
}