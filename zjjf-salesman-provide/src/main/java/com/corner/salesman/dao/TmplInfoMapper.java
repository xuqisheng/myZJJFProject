package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.TmplInfo;

@MyBatisDao
public interface TmplInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TmplInfo record);

    int insertSelective(TmplInfo record);

    TmplInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TmplInfo record);

    int updateByPrimaryKey(TmplInfo record);
    
    /**
     * 获取模板列表信息
     * @return
     */
    List<TmplInfo> findTmplInfoList();
}