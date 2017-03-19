package com.corner.kefu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SystemVersionMapper;
import com.corner.kefu.beans.ro.SystemVersionRo;
import com.corner.kefu.beans.vo.SystemVersionVo;
import com.corner.kefu.dao.SystemVersionMgMapper;
import com.corner.kefu.service.SystemVersionService;

@Service
public class SystemVersionServiceImpl extends BaseServiceImpl implements SystemVersionService {

	@Autowired
	SystemVersionMgMapper versionMgMapper;
	@Autowired
	SystemVersionMapper versionMapper;
	
	
	public Pager<SystemVersionVo> getAllVersionInfo(SystemVersionRo versionRo){
		List<SystemVersionVo> versionVoList = versionMgMapper.getAllVersionInfo(versionRo);
		int num = versionMgMapper.getAllVersionInfoCount(versionRo);
		return new Pager<SystemVersionVo>(num, versionVoList);
	}

	@Override
	public SystemVersionVo getVersionInfoById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return versionMgMapper.getVersionInfoById(map);
	}

	@Override
	public void updateVersionInfoById(SystemVersionRo versionRo) {
		// TODO Auto-generated method stub
		versionMapper.updateByPrimaryKeySelective(versionRo);
	}

	@Override
	public void addVersionInfo(SystemVersionRo versionRo) {
		// TODO Auto-generated method stub
		versionMapper.insertSelective(versionRo);
	}

	@Override
	public void updateVersionInfoStatusById(SystemVersionRo versionRo) {
		// TODO Auto-generated method stub
		versionMapper.updateByPrimaryKeySelective(versionRo);
	}
	
}
