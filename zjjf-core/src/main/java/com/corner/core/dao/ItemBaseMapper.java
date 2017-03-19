package com.corner.core.dao;

import com.corner.core.beans.ItemBase;
import com.corner.core.beans.ItemBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemBaseMapper {
    int countByExample(ItemBaseExample example);

    int deleteByExample(ItemBaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemBase record);

    int insertSelective(ItemBase record);

    List<ItemBase> selectByExampleWithBLOBs(ItemBaseExample example);

    List<ItemBase> selectByExample(ItemBaseExample example);

    ItemBase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemBase record, @Param("example") ItemBaseExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemBase record, @Param("example") ItemBaseExample example);

    int updateByExample(@Param("record") ItemBase record, @Param("example") ItemBaseExample example);

    int updateByPrimaryKeySelective(ItemBase record);

    int updateByPrimaryKeyWithBLOBs(ItemBase record);

    int updateByPrimaryKey(ItemBase record);
}