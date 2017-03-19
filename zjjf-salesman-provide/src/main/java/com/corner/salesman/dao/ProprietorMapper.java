package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.Proprietor;

@MyBatisDao
public interface ProprietorMapper {
    int deleteByPrimaryKey(String id);

    int insert(Proprietor record);

    int insertSelective(Proprietor record);

    Proprietor selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Proprietor record);

    int updateByPrimaryKey(Proprietor record);
    
    List<Proprietor> findProprietorByShopId(String id);
}