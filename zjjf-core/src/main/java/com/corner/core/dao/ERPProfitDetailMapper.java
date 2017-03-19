package com.corner.core.dao;

import com.corner.core.beans.ERPProfitDetail;
import com.corner.core.beans.ERPProfitDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPProfitDetailMapper {
    int countByExample(ERPProfitDetailExample example);

    int deleteByExample(ERPProfitDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPProfitDetail record);

    int insertSelective(ERPProfitDetail record);

    List<ERPProfitDetail> selectByExample(ERPProfitDetailExample example);

    ERPProfitDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPProfitDetail record, @Param("example") ERPProfitDetailExample example);

    int updateByExample(@Param("record") ERPProfitDetail record, @Param("example") ERPProfitDetailExample example);

    int updateByPrimaryKeySelective(ERPProfitDetail record);

    int updateByPrimaryKey(ERPProfitDetail record);
}