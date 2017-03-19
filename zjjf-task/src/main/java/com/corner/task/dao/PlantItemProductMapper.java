package com.corner.task.dao;

import com.corner.task.beans.PlantItemProduct;
import com.corner.task.beans.PlantItemProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlantItemProductMapper {
    int countByExample(PlantItemProductExample example);

    int deleteByExample(PlantItemProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(PlantItemProduct record);

    int insertSelective(PlantItemProduct record);

    List<PlantItemProduct> selectByExample(PlantItemProductExample example);

    PlantItemProduct selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PlantItemProduct record, @Param("example") PlantItemProductExample example);

    int updateByExample(@Param("record") PlantItemProduct record, @Param("example") PlantItemProductExample example);

    int updateByPrimaryKeySelective(PlantItemProduct record);

    int updateByPrimaryKey(PlantItemProduct record);
}