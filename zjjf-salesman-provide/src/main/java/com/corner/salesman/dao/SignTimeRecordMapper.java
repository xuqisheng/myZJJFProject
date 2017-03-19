package com.corner.salesman.dao;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.SignTimeRecord;
@MyBatisDao
public interface SignTimeRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(SignTimeRecord record);

    int insertSelective(SignTimeRecord record);

    SignTimeRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective22(SignTimeRecord record);

    int updateByPrimaryKey(SignTimeRecord record);
    
    int updateSignTimeByUserId(SignTimeRecord record);
    
    int deleteSignTimeByUserId(String userId);
    
    int checkIsExist(String userId);
}