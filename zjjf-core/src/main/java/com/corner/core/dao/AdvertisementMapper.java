package com.corner.core.dao;

import com.corner.core.beans.Advertisement;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKeyWithBLOBs(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
}