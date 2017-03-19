package com.corner.kefu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.corner.core.beans.Region;
import com.corner.core.beans.SystemInfo;
import com.corner.core.dao.RegionMapper;
import com.corner.core.dao.SystemInfoMapper;
import com.corner.kefu.dao.RegionMgMapper;
import com.corner.kefu.service.PublicService;

@Service
public class PublicServiceImpl extends BaseServiceImpl implements PublicService {

	@Autowired
	SystemInfoMapper systemInfoMapper;
	@Autowired
	RegionMapper regionMapper;
	@Autowired
	RegionMgMapper regionMgMapper;
	
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

	/*	根据 pid查询地区信息
	* <p>Title: findRegionByPId</p> 
	* <p>Description: </p> 
	* @param pId
	* @return 
	* @see com.corner.kefu.service.PublicService#findRegionByPId(java.lang.String) 
	*/ 
	@Override
	public List<Region> findRegionByPId(String pId) {
		return regionMgMapper.findRegionByPId(pId);
	}
	
	
	@Override
	public List<Region> getRetionByPid(Integer pId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId", pId);
		return regionMgMapper.getAllRetionByPid(map);
	}

}
