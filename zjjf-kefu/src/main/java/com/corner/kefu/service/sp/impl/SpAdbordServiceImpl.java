package com.corner.kefu.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Adboard;
import com.corner.kefu.dao.sp.SpAdboardMgMapper;
import com.corner.kefu.service.sp.SpAdbordService;



@Service
public class SpAdbordServiceImpl implements SpAdbordService {

	@Autowired
	private SpAdboardMgMapper spAdboardMgMapper;
	
	@Override
	public List<Adboard> getAdPositionList() {
		
		return spAdboardMgMapper.getAdPositionList();
	}

	@Override
	public void updateByPrimaryKeySelective(Adboard adboard) {
		this.spAdboardMgMapper.updateByPrimaryKeySelective(adboard);
	}

	@Override
	public void saveAdboard(Adboard adboard) {
		this.spAdboardMgMapper.insertSelective(adboard);
	}

	@Override
	public Adboard getAdboardById(Integer id) {
		return spAdboardMgMapper.getAdboardById(id);
	}

	@Override
	public void removeAdPosition(Integer id) {
		spAdboardMgMapper.deleteByPrimaryKey(id);
		spAdboardMgMapper.deleteAdByAdBoardId(id);
	}

	@Override
	public Adboard getadBoardByAdId(Integer id) {
		
		return spAdboardMgMapper.getadBoardByAdId(id);
	}

	@Override
	public List<Adboard> getAllAdboard() {
		// TODO Auto-generated method stub
		return spAdboardMgMapper.getAllAdboard();
	}
	
	
}
