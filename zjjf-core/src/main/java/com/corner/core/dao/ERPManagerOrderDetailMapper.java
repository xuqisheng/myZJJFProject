package com.corner.core.dao;

import com.corner.core.beans.ERPManagerOrderDetail;
import com.corner.core.beans.ERPManagerOrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPManagerOrderDetailMapper {
    int countByExample(ERPManagerOrderDetailExample example);

    int deleteByExample(ERPManagerOrderDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPManagerOrderDetail record);

    int insertSelective(ERPManagerOrderDetail record);

    List<ERPManagerOrderDetail> selectByExample(ERPManagerOrderDetailExample example);

    ERPManagerOrderDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPManagerOrderDetail record, @Param("example") ERPManagerOrderDetailExample example);

    int updateByExample(@Param("record") ERPManagerOrderDetail record, @Param("example") ERPManagerOrderDetailExample example);

    int updateByPrimaryKeySelective(ERPManagerOrderDetail record);

    int updateByPrimaryKey(ERPManagerOrderDetail record);
}