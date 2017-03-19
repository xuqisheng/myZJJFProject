package com.corner.core.dao;

import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpOrderDetailMapper {
    int countByExample(SpOrderDetailExample example);

    int deleteByExample(SpOrderDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpOrderDetail record);

    int insertSelective(SpOrderDetail record);

    List<SpOrderDetail> selectByExample(SpOrderDetailExample example);

    SpOrderDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpOrderDetail record, @Param("example") SpOrderDetailExample example);

    int updateByExample(@Param("record") SpOrderDetail record, @Param("example") SpOrderDetailExample example);

    int updateByPrimaryKeySelective(SpOrderDetail record);

    int updateByPrimaryKey(SpOrderDetail record);
}