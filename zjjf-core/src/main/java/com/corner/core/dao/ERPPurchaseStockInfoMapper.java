package com.corner.core.dao;

import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.core.beans.ERPPurchaseStockInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPPurchaseStockInfoMapper {
    int countByExample(ERPPurchaseStockInfoExample example);

    int deleteByExample(ERPPurchaseStockInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPPurchaseStockInfo record);

    int insertSelective(ERPPurchaseStockInfo record);

    List<ERPPurchaseStockInfo> selectByExample(ERPPurchaseStockInfoExample example);

    ERPPurchaseStockInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPPurchaseStockInfo record, @Param("example") ERPPurchaseStockInfoExample example);

    int updateByExample(@Param("record") ERPPurchaseStockInfo record, @Param("example") ERPPurchaseStockInfoExample example);

    int updateByPrimaryKeySelective(ERPPurchaseStockInfo record);

    int updateByPrimaryKey(ERPPurchaseStockInfo record);
}