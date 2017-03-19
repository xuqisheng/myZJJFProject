package com.corner.core.dao;

import com.corner.core.beans.ERPPurchaseStockDetail;
import com.corner.core.beans.ERPPurchaseStockDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPPurchaseStockDetailMapper {
    int countByExample(ERPPurchaseStockDetailExample example);

    int deleteByExample(ERPPurchaseStockDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPPurchaseStockDetail record);

    int insertSelective(ERPPurchaseStockDetail record);

    List<ERPPurchaseStockDetail> selectByExample(ERPPurchaseStockDetailExample example);

    ERPPurchaseStockDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPPurchaseStockDetail record, @Param("example") ERPPurchaseStockDetailExample example);

    int updateByExample(@Param("record") ERPPurchaseStockDetail record, @Param("example") ERPPurchaseStockDetailExample example);

    int updateByPrimaryKeySelective(ERPPurchaseStockDetail record);

    int updateByPrimaryKey(ERPPurchaseStockDetail record);
}