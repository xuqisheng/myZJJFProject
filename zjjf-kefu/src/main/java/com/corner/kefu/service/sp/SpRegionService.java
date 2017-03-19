package com.corner.kefu.service.sp;

import com.corner.core.beans.Region;
import com.corner.core.beans.vo.ResponseVo;
import com.corner.kefu.beans.vo.sp.RegionSpGroupVo;
import com.corner.kefu.beans.vo.sp.RegionVo;

import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: SpGroupService
 * 
 * @Description: TODO
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年10月10日 上午10:59:38
 */

public interface SpRegionService {
	/**
	 * 查询所有定格
	 * @return
	 * @throws Exception 
	 */
	RegionVo getPID(Integer id);
	
	Integer getPidByid(Map<String, Object> map);
	
	List<Region> getRegionByPidOrRegionLevel(Map<String, Object> map);

	List<RegionVo> getAllRegion();

	Region getRegionById(Integer id);
	
	void updateRegionStatus(Region region);

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
	List<RegionVo> getAllEnabledRegion() throws Exception;
	
	public List<RegionVo> getAllAreaAndSpGroup();
	
	public String getRegionStr(Integer provinceId,Integer cityId,Integer areaId);
	
	/**
	 * 
	 * @Title: getRegionSpGroupVoById
	 * @date 2016年9月7日  上午10:40:34
	 * @author 小武
	 * @version  飓风
	 * @param id
	 * @return
	 */
	RegionSpGroupVo getRegionSpGroupVoById(Integer id);
	
	ResponseVo updateRegionDefaultSpGroupId(Integer id,Integer spGroupId,String opUser);
	
	ResponseVo clearRegionDefaultSpGroupId(Integer id,String opUser);


    List<RegionVo> getShiQuDingGeData();

}
