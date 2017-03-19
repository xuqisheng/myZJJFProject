/**   
* @Title: SpRegionMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年6月27日 下午2:46:52 
* @version V1.0   
*/

package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Region;
import com.corner.scms.beans.vo.RegionRegistVo;

/** 
* @ClassName: SpRegionMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年6月27日 下午2:46:52 
*  
*/

public interface SpRegionMgMapper {

	/**
	 * 
	* @Title: getAllEnableRegionList 
	* @Description:获取所有可用的区域数据
	* @param @return
	* @return List<RegionRegistVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<RegionRegistVo> getAllEnableRegionList();

	List<Region> getRetionByPid(Map<String, Object> map);
	
	List<Region> getAllRetionByPid(Map<String, Object> map);
	
//	List<RegionRegistVo> getAllAreaAndSpGroup();

}
