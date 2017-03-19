/**   
* @Title: SpRegionService.java 
* @Package com.corner.scms.service.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年6月27日 下午2:43:06 
* @version V1.0   
*/

package com.corner.scms.service.sp;

import java.util.List;

import com.corner.scms.beans.vo.RegionRegistVo;

/** 
* @ClassName: SpRegionService 
* @Description:区域接口
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年6月27日 下午2:43:06 
*  
*/

public interface SpRegionService {

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
	
//	List<RegionRegistVo> getAllAreaAndSpGroup();

}
