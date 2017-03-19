package com.corner.core.dao;

import com.corner.core.beans.ShareDisseminateLog;

public interface ShareDisseminateLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShareDisseminateLog record);

    int insertSelective(ShareDisseminateLog record);

    ShareDisseminateLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShareDisseminateLog record);

    int updateByPrimaryKey(ShareDisseminateLog record);
}