package com.corner.auth.dao.mg;

import com.corner.auth.beans.ro.RegionRo;
import com.corner.auth.beans.vo.RegionVo;

import java.util.List;



public interface RegionMgMapper {
	public List<RegionVo> getRegionListPage(RegionRo command);
	public Integer getRegionListPageCount(RegionRo command);
}
