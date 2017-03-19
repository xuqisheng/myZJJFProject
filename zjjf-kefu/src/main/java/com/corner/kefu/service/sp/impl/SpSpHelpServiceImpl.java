package com.corner.kefu.service.sp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpHelp;
import com.corner.core.dao.SpHelpMapper;
import com.corner.kefu.beans.vo.sp.SpHelpVo;
import com.corner.kefu.dao.sp.SpSpHelpMgMapper;
import com.corner.kefu.service.sp.SpSpHelpService;

@Service
public class SpSpHelpServiceImpl implements SpSpHelpService {
	@Autowired
	SpSpHelpMgMapper spHelpMgMapper; 
	@Autowired
	SpHelpMapper spHelpMapper; 
	
	@Override
	public List<SpHelpVo> getAllHelpByParameter(SpHelp spHelp) {
		return spHelpMgMapper.getAllHelpByParameter(spHelp);
	}

	@Override
	public void addHelp(SpHelp spHelp) {
		// TODO Auto-generated method stub
		spHelpMapper.insertSelective(spHelp);
	}

	@Override
	public void updateHelp(SpHelp spHelp) {
		// TODO Auto-generated method stub
		spHelpMapper.updateByPrimaryKeySelective(spHelp);
	}

	@Override
	public void orderHelpList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		spHelpMgMapper.orderHelpList(param);
	}

	@Override
	public SpHelp getHelpById(int id) {
		// TODO Auto-generated method stub
		return spHelpMapper.selectByPrimaryKey(id);
	}
	
}
