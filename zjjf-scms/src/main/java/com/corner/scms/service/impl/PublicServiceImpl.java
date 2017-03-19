package com.corner.scms.service.impl;

import com.corner.core.beans.Region;
import com.corner.core.beans.SpWalletLog;
import com.corner.core.beans.SystemInfo;
import com.corner.core.dao.RegionMapper;
import com.corner.core.dao.SettlementConfMapper;
import com.corner.core.dao.SystemInfoMapper;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.scms.dao.SpRegionMgMapper;
import com.corner.scms.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PublicServiceImpl extends BaseServiceImpl implements PublicService {

	@Autowired
	SystemInfoMapper systemInfoMapper;
	@Autowired
	RegionMapper regionMapper;
	@Autowired
	SettlementConfMapper settlementConfMapper;
	@Autowired
	SpRegionMgMapper spRegionMgMapper;
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

	@Override
	public SystemInfo getSystemInfoById(String id) {
		return systemInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Region> getRetionByPid(Integer pId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId", pId);
		// TODO Auto-generated method stub
		return spRegionMgMapper.getAllRetionByPid(map);
	}
}
