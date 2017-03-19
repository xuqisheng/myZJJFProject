package com.corner.core.dao;

import com.corner.core.beans.ERPMarketStockInfo;
import com.corner.core.beans.ERPMarketStockInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPMarketStockInfoMapper {
    int countByExample(ERPMarketStockInfoExample example);

    int deleteByExample(ERPMarketStockInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPMarketStockInfo record);

    int insertSelective(ERPMarketStockInfo record);

    List<ERPMarketStockInfo> selectByExample(ERPMarketStockInfoExample example);

    ERPMarketStockInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPMarketStockInfo record, @Param("example") ERPMarketStockInfoExample example);

    int updateByExample(@Param("record") ERPMarketStockInfo record, @Param("example") ERPMarketStockInfoExample example);

    int updateByPrimaryKeySelective(ERPMarketStockInfo record);

    int updateByPrimaryKey(ERPMarketStockInfo record);
}