package com.corner.core.dao;

import com.corner.core.beans.PcAdvertisement;

public interface PcAdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PcAdvertisement record);

    int insertSelective(PcAdvertisement record);

    PcAdvertisement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PcAdvertisement record);

    int updateByPrimaryKey(PcAdvertisement record);
}