package com.corner.salesman.dao;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.Sequence;
@MyBatisDao
public interface SequenceMapper {
    int deleteByPrimaryKey(String name);

    int insert(Sequence record);

    int insertSelective(Sequence record);

    Sequence selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Sequence record);

    int updateByPrimaryKey(Sequence record);
    
    String getNextValSeqByName(String name);
}