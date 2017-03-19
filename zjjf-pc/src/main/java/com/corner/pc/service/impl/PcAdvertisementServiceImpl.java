package com.corner.pc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.pc.beans.PcAdvertisement;
import com.corner.pc.beans.ro.PcAdvertisementRo;
import com.corner.pc.beans.vo.Pager;
import com.corner.pc.dao.PcAdvertisementMapper;
import com.corner.pc.service.PcAdvertisementService;

@Service
public class PcAdvertisementServiceImpl implements PcAdvertisementService {

	@Autowired
	PcAdvertisementMapper pcAdvertisementMapper;
	
	/**
	 * 获取所有广告
	 */
	@Override
	public Pager<PcAdvertisement> getAllAdvertisement(PcAdvertisementRo advertisementRo) {
		int num = pcAdvertisementMapper.getAllAdvertisementCount(advertisementRo);
		List<PcAdvertisement> list = pcAdvertisementMapper.getAllAdvertisement(advertisementRo);
		return new Pager(num, list);
	}

	@Override
	public PcAdvertisement getAdvertisementById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pcAdvertisementMapper.getAdvertisementById(map);
	}

	@Override
	public void addAdvertisement(PcAdvertisementRo advertisementRo) {
		pcAdvertisementMapper.insertSelective(advertisementRo);
		
	}

	@Override
	public void updateAdvertisement(PcAdvertisementRo advertisementRo) {
		// TODO Auto-generated method stub
		pcAdvertisementMapper.updateByPrimaryKeySelective(advertisementRo);
	}

	@Override
	public List<PcAdvertisement> getAdvertisementByPositionId(PcAdvertisementRo advertisementRo) {
		// TODO Auto-generated method stub
		return pcAdvertisementMapper.getAdvertisementByPositionId(advertisementRo);
	}

}
