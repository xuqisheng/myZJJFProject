package com.corner.core.dao;

import com.corner.core.beans.ERPManagerOrderInfo;
import com.corner.core.beans.ERPManagerOrderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPManagerOrderInfoMapper {
    int countByExample(ERPManagerOrderInfoExample example);

    int deleteByExample(ERPManagerOrderInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPManagerOrderInfo record);

    int insertSelective(ERPManagerOrderInfo record);

    List<ERPManagerOrderInfo> selectByExample(ERPManagerOrderInfoExample example);

    ERPManagerOrderInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPManagerOrderInfo record, @Param("example") ERPManagerOrderInfoExample example);

    int updateByExample(@Param("record") ERPManagerOrderInfo record, @Param("example") ERPManagerOrderInfoExample example);

    int updateByPrimaryKeySelective(ERPManagerOrderInfo record);

    int updateByPrimaryKey(ERPManagerOrderInfo record);
}