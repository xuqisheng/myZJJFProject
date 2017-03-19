package com.corner.kefu.dao.sp;

import com.corner.core.beans.Region;
import com.corner.kefu.beans.vo.sp.RegionSpGroupVo;
import com.corner.kefu.beans.vo.sp.RegionVo;

import java.util.List;
import java.util.Map;


/**
 * 开店宝购物车dao
 * 
 * @author aimee at 2015年2月3日下午3:31:04
 * @email 1297579898@qq.com
 */
public interface SpRegionMgMapper{
	public RegionVo selectPIDWithID(Integer id);
	public Integer getPidByid(Map<String, Object> map);
	
	public Region getUpperByLowerId(Map<String, Object> map);
	public List<Region> getRegionByPidOrRegionLevel(Map<String, Object> map);
	public List<RegionVo> getAllRegion();
	
	/**
	 * 
	* @Title: getAllEnabledRegion 
	* @Description:查询所有启用的地区
	* @param @return
	* @param @throws Exception
	* @return List<RegionVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<RegionVo> getAllEnabledRegion() throws Exception;
	/**
	 * 
	* @Title: getChildrenRegionList 
	* @Description:根据regionLevel 和 id 获取所有regionLevel=4的数据集合
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<Region>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<Region> getChildrenRegionList(Map<String, Object> map) throws Exception;
	//组装区域和定格
	public List<RegionVo> getAllAreaAndSpGroup();
	
	public RegionSpGroupVo getRegionSpGroupVoById(Map<String, Object> map);

    List<RegionVo> getShiQuDingGeData();

}
