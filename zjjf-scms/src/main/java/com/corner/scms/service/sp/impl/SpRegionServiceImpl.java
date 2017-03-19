/**   
* @Title: SpRegionServiceImpl.java 
* @Package com.corner.scms.service.sp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年6月27日 下午2:43:54 
* @version V1.0   
*/

package com.corner.scms.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.scms.beans.vo.RegionRegistVo;
import com.corner.scms.dao.SpRegionMgMapper;
import com.corner.scms.service.sp.SpRegionService;

/** 
* @ClassName: SpRegionServiceImpl 
* @Description:区域接口
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年6月27日 下午2:43:54 
*  
*/
@Service
public class SpRegionServiceImpl implements SpRegionService{

	@Autowired
	SpRegionMgMapper spRegionMgMapper;

	/**
	 * 
	* @Title: getAllEnableRegionList 
	* @Description:获取所有可用的区域数据
	* @param @return
	* @return List<RegionRegistVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public List<RegionRegistVo> getAllEnableRegionList() {
		List<RegionRegistVo>list = spRegionMgMapper.getAllEnableRegionList();
		return list;
	}

//	@Override
//	public List<RegionRegistVo> getAllAreaAndSpGroup() {
//		// TODO Auto-generated method stub
//		return spRegionMgMapper.getAllAreaAndSpGroup();
//	}
}
