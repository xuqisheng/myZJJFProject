package com.corner.auth.service;

import org.springframework.stereotype.Service;

import com.corner.auth.beans.Region;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.RegionRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.RegionVo;

@Service
public interface RegionService {
	Pager<RegionVo> getRegionListPage(RegionRo command);
	
	ModelMsg updateRegion(Region command);
}
