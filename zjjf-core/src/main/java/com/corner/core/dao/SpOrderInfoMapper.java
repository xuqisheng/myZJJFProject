package com.corner.core.dao;

import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.SpOrderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpOrderInfoMapper {
    int countByExample(SpOrderInfoExample example);

    int deleteByExample(SpOrderInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpOrderInfo record);

    int insertSelective(SpOrderInfo record);

    List<SpOrderInfo> selectByExample(SpOrderInfoExample example);

    SpOrderInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpOrderInfo record, @Param("example") SpOrderInfoExample example);

    int updateByExample(@Param("record") SpOrderInfo record, @Param("example") SpOrderInfoExample example);

    int updateByPrimaryKeySelective(SpOrderInfo record);

    int updateByPrimaryKey(SpOrderInfo record);
}