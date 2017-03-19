package com.corner.core.dao;

import com.corner.core.beans.SettlementConf;

public interface SettlementConfMapper {
    int deleteByPrimaryKey(String id);

    int insert(SettlementConf record);

    int insertSelective(SettlementConf record);

    SettlementConf selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SettlementConf record);

    int updateByPrimaryKey(SettlementConf record);
}