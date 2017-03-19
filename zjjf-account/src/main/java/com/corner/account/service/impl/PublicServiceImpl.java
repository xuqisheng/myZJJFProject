package com.corner.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.corner.account.service.PublicService;
import com.corner.core.beans.Region;
import com.corner.core.beans.SystemInfo;
import com.corner.core.dao.RegionMapper;
import com.corner.core.dao.SystemInfoMapper;

@Service
public class PublicServiceImpl extends BaseServiceImpl implements PublicService {

	@Autowired
	SystemInfoMapper systemInfoMapper;
	@Autowired
	RegionMapper regionMapper;
	
	@Override
	public List<Region> getRegions() {
		SystemInfo systemInfo=systemInfoMapper.selectByPrimaryKey("Area_id");
		if(systemInfo != null && !StringUtils.isEmpty(systemInfo.getContent())){
			String[] regionIds=systemInfo.getContent().split(",");
			List<Region> list=new ArrayList<Region>();
			for (String id : regionIds) {
				Region r = regionMapper.selectByPrimaryKey(Integer.valueOf(id));
				if(r!=null){
					list.add(r);
				}
			}
			return list;
		}else{
			return null;
		}

	}

}
