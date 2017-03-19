package com.corner.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.auth.beans.Region;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.RegionRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.RegionVo;
import com.corner.auth.dao.RegionMapper;
import com.corner.auth.dao.mg.RegionMgMapper;
import com.corner.auth.service.RegionService;
@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	RegionMapper regionMapper;
	@Autowired
	RegionMgMapper regionMgMapper;
	@Override
	public Pager<RegionVo> getRegionListPage(RegionRo command) {
		List<RegionVo> list =regionMgMapper.getRegionListPage(command);
		int result = regionMgMapper.getRegionListPageCount(command);
		return new Pager<RegionVo>( result , list);
	}
	@Override
	public ModelMsg updateRegion(Region command) {
		if(regionMapper.updateByPrimaryKeySelective(command) == 0)
			return new ModelMsg(false, "修改失败");
		return new ModelMsg(true, "修改成功");
	}
}
