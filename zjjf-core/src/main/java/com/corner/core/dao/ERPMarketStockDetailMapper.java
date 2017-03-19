package com.corner.core.dao;

import com.corner.core.beans.ERPMarketStockDetail;
import com.corner.core.beans.ERPMarketStockDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPMarketStockDetailMapper {
    int countByExample(ERPMarketStockDetailExample example);

    int deleteByExample(ERPMarketStockDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPMarketStockDetail record);

    int insertSelective(ERPMarketStockDetail record);

    List<ERPMarketStockDetail> selectByExample(ERPMarketStockDetailExample example);

    ERPMarketStockDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPMarketStockDetail record, @Param("example") ERPMarketStockDetailExample example);

    int updateByExample(@Param("record") ERPMarketStockDetail record, @Param("example") ERPMarketStockDetailExample example);

    int updateByPrimaryKeySelective(ERPMarketStockDetail record);

    int updateByPrimaryKey(ERPMarketStockDetail record);
}