package com.corner.pc.service;

import java.util.List;
import java.util.Map;

import com.corner.pc.beans.PcAdvertisement;
import com.corner.pc.beans.ro.PcAdvertisementRo;
import com.corner.pc.beans.vo.Pager;

public interface PcAdvertisementService {
	
    public Pager<PcAdvertisement> getAllAdvertisement(PcAdvertisementRo advertisementRo);

	public PcAdvertisement getAdvertisementById(Map<String, Object> map);

	public void addAdvertisement(PcAdvertisementRo advertisementRo);

	public void updateAdvertisement(PcAdvertisementRo advertisementRo);

	public List<PcAdvertisement> getAdvertisementByPositionId(PcAdvertisementRo advertisementRo);

}
