package com.corner.pc.dao;

import java.util.List;
import java.util.Map;

import com.corner.pc.beans.PcAdvertisement;
import com.corner.pc.beans.ro.PcAdvertisementRo;

public interface PcAdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PcAdvertisement record);

    int insertSelective(PcAdvertisement record);

    PcAdvertisement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PcAdvertisement record);

    int updateByPrimaryKey(PcAdvertisement record);
    
    /**************************************************************************************************************************/
    
    public List<PcAdvertisement> getAllAdvertisement(PcAdvertisementRo advertisementRo);
    
    public int getAllAdvertisementCount(PcAdvertisementRo advertisementRo);

	public PcAdvertisement getAdvertisementById(Map<String, Object> map);

	public List<PcAdvertisement> getAdvertisementByPositionId(PcAdvertisementRo advertisementRo);
}