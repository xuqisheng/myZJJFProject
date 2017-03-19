package com.corner.task.dao;

import com.corner.task.beans.PlantItemLog;
import com.corner.task.beans.PlantItemLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlantItemLogMapper {
    int countByExample(PlantItemLogExample example);

    int deleteByExample(PlantItemLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(PlantItemLog record);

    int insertSelective(PlantItemLog record);

    List<PlantItemLog> selectByExample(PlantItemLogExample example);

    PlantItemLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PlantItemLog record, @Param("example") PlantItemLogExample example);

    int updateByExample(@Param("record") PlantItemLog record, @Param("example") PlantItemLogExample example);

    int updateByPrimaryKeySelective(PlantItemLog record);

    int updateByPrimaryKey(PlantItemLog record);
}