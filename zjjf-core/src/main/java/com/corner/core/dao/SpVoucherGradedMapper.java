package com.corner.core.dao;

import com.corner.core.beans.SpVoucherGraded;
import com.corner.core.beans.SpVoucherGradedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpVoucherGradedMapper {
    int countByExample(SpVoucherGradedExample example);

    int deleteByExample(SpVoucherGradedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpVoucherGraded record);

    int insertSelective(SpVoucherGraded record);

    List<SpVoucherGraded> selectByExample(SpVoucherGradedExample example);

    SpVoucherGraded selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpVoucherGraded record, @Param("example") SpVoucherGradedExample example);

    int updateByExample(@Param("record") SpVoucherGraded record, @Param("example") SpVoucherGradedExample example);

    int updateByPrimaryKeySelective(SpVoucherGraded record);

    int updateByPrimaryKey(SpVoucherGraded record);
}