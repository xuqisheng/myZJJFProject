package com.corner.core.dao;

import com.corner.core.beans.ERPSpOrderOwnerDetail;
import com.corner.core.beans.ERPSpOrderOwnerDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ERPSpOrderOwnerDetailMapper {
    int countByExample(ERPSpOrderOwnerDetailExample example);

    int deleteByExample(ERPSpOrderOwnerDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ERPSpOrderOwnerDetail record);

    int insertSelective(ERPSpOrderOwnerDetail record);

    List<ERPSpOrderOwnerDetail> selectByExample(ERPSpOrderOwnerDetailExample example);

    ERPSpOrderOwnerDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ERPSpOrderOwnerDetail record, @Param("example") ERPSpOrderOwnerDetailExample example);

    int updateByExample(@Param("record") ERPSpOrderOwnerDetail record, @Param("example") ERPSpOrderOwnerDetailExample example);

    int updateByPrimaryKeySelective(ERPSpOrderOwnerDetail record);

    int updateByPrimaryKey(ERPSpOrderOwnerDetail record);
}