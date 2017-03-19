package com.corner.kefu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.AppPayWayCfg;
import com.corner.kefu.dao.AppPayWayCfgMgMapper;
import com.corner.kefu.service.AppPayWayCfgService;

@Service
public class AppPayWayCfgServiceImpl implements AppPayWayCfgService {

	@Autowired
	AppPayWayCfgMgMapper appPayWayCfgMgMapper;
	
	@Override
	public List<AppPayWayCfg> getAllAppPayWayCfg() {
		// TODO Auto-generated method stub
		return appPayWayCfgMgMapper.getAllAppPayWayCfg();
	}

}
