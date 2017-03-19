package com.corner.core.dao;

import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.ERPWarehouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPWarehouseMapper {
    int countByExample(ERPWarehouseExample example);

    int deleteByExample(ERPWarehouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPWarehouse record);

    int insertSelective(ERPWarehouse record);

    List<ERPWarehouse> selectByExample(ERPWarehouseExample example);

    ERPWarehouse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPWarehouse record, @Param("example") ERPWarehouseExample example);

    int updateByExample(@Param("record") ERPWarehouse record, @Param("example") ERPWarehouseExample example);

    int updateByPrimaryKeySelective(ERPWarehouse record);

    int updateByPrimaryKey(ERPWarehouse record);
}