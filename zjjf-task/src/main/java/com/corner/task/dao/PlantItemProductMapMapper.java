package com.corner.task.dao;

import com.corner.task.beans.PlantItemProductMap;
import com.corner.task.beans.PlantItemProductMapExample;
import com.corner.task.beans.PlantItemProductMapKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlantItemProductMapMapper {
    int countByExample(PlantItemProductMapExample example);

    int deleteByExample(PlantItemProductMapExample example);

    int deleteByPrimaryKey(PlantItemProductMapKey key);

    int insert(PlantItemProductMap record);

    int insertSelective(PlantItemProductMap record);

    List<PlantItemProductMap> selectByExample(PlantItemProductMapExample example);

    PlantItemProductMap selectByPrimaryKey(PlantItemProductMapKey key);

    int updateByExampleSelective(@Param("record") PlantItemProductMap record, @Param("example") PlantItemProductMapExample example);

    int updateByExample(@Param("record") PlantItemProductMap record, @Param("example") PlantItemProductMapExample example);

    int updateByPrimaryKeySelective(PlantItemProductMap record);

    int updateByPrimaryKey(PlantItemProductMap record);
}