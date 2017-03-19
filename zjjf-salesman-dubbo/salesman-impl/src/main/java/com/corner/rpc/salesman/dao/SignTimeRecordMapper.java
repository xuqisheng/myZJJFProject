package com.corner.rpc.salesman.dao;
import com.corner.rpc.salesman.model.SignTimeRecord;

public interface SignTimeRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(SignTimeRecord record);

    int insertSelective(SignTimeRecord record);

    SignTimeRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SignTimeRecord record);

    int updateByPrimaryKey(SignTimeRecord record);
}