package com.corner.task.dao;

import com.corner.task.beans.PlantItemPre;
import com.corner.task.beans.PlantItemPreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlantItemPreMapper {
    int countByExample(PlantItemPreExample example);

    int deleteByExample(PlantItemPreExample example);

    int deleteByPrimaryKey(String id);

    int insert(PlantItemPre record);

    int insertSelective(PlantItemPre record);

    List<PlantItemPre> selectByExample(PlantItemPreExample example);

    PlantItemPre selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PlantItemPre record, @Param("example") PlantItemPreExample example);

    int updateByExample(@Param("record") PlantItemPre record, @Param("example") PlantItemPreExample example);

    int updateByPrimaryKeySelective(PlantItemPre record);

    int updateByPrimaryKey(PlantItemPre record);
}