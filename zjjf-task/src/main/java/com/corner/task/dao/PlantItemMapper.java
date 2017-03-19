package com.corner.task.dao;

import com.corner.task.beans.PlantItem;
import com.corner.task.beans.PlantItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlantItemMapper {
    int countByExample(PlantItemExample example);

    int deleteByExample(PlantItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(PlantItem record);

    int insertSelective(PlantItem record);

    List<PlantItem> selectByExample(PlantItemExample example);

    PlantItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PlantItem record, @Param("example") PlantItemExample example);

    int updateByExample(@Param("record") PlantItem record, @Param("example") PlantItemExample example);

    int updateByPrimaryKeySelective(PlantItem record);

    int updateByPrimaryKey(PlantItem record);
}