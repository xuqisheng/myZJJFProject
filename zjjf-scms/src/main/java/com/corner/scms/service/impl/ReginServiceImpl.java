package com.corner.scms.service.impl;

import com.corner.core.beans.Region;
import com.corner.core.dao.RegionMapper;
import com.corner.scms.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReginServiceImpl extends BaseServiceImpl  implements RegionService {

	@Autowired
	RegionMapper regionMapper;

	@Override
	public Region getReginById(Integer id) {
		return regionMapper.selectByPrimaryKey(id);
	}
}
