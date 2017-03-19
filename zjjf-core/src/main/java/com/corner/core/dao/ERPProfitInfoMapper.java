package com.corner.core.dao;

import com.corner.core.beans.ERPProfitInfo;
import com.corner.core.beans.ERPProfitInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPProfitInfoMapper {
    int countByExample(ERPProfitInfoExample example);

    int deleteByExample(ERPProfitInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPProfitInfo record);

    int insertSelective(ERPProfitInfo record);

    List<ERPProfitInfo> selectByExample(ERPProfitInfoExample example);

    ERPProfitInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPProfitInfo record, @Param("example") ERPProfitInfoExample example);

    int updateByExample(@Param("record") ERPProfitInfo record, @Param("example") ERPProfitInfoExample example);

    int updateByPrimaryKeySelective(ERPProfitInfo record);

    int updateByPrimaryKey(ERPProfitInfo record);
}