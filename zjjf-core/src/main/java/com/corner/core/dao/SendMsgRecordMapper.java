package com.corner.core.dao;

import com.corner.core.beans.SendMsgRecord;

public interface SendMsgRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SendMsgRecord record);

    int insertSelective(SendMsgRecord record);

    SendMsgRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SendMsgRecord record);

    int updateByPrimaryKey(SendMsgRecord record);
}