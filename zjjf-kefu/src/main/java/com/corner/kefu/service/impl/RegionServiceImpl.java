/**   
* @Title: RegionServiceImpl.java 
* @Package com.corner.kefu.service.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月24日 上午10:44:41 
* @version V1.0   
*/

package com.corner.kefu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Region;
import com.corner.core.beans.Supplier;
import com.corner.kefu.beans.vo.sp.RegionVo;
import com.corner.kefu.dao.RegionMgMapper;
import com.corner.kefu.dao.sp.SpSupplierMgMapper;
import com.corner.kefu.service.RegionService;

/** 
* @ClassName: RegionServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午10:44:41 
*  
*/
@Service
public class RegionServiceImpl extends BaseServiceImpl implements RegionService {

	@Autowired
	RegionMgMapper regionMgMapper;
	
	@Autowired
	SpSupplierMgMapper spSupplierMgMapper;

	/**
	 * 
	* Title: getAllSupplierRegionList 
	* Description:查询所有批发商所在的区域列表 
	* @return 
	* @see com.corner.kefu.service.RegionService#getAllSupplierRegionList()
	 */
	@Override
	public List<RegionVo> getAllSupplierRegionList() throws Exception{
		List<Region> list = regionMgMapper.getAllSupplierRegionList();
		List<RegionVo> resultList = new ArrayList<RegionVo>();
		for (Region region : list) {
			RegionVo regionVo = new RegionVo();
			regionVo.setId(region.getId());
			regionVo.setName(region.getName());
			List<Supplier> supplierList = spSupplierMgMapper.getRegionSupplierList(region.getId());
			regionVo.setList(supplierList);
			resultList.add(regionVo);
		}
		return resultList;
	}

	@Override
	public List<Region> getRegionByPidOrRegionLevel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return regionMgMapper.getRegionByPidOrRegionLevel(map);
	}

	
}
