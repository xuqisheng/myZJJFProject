package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.kefu.beans.vo.sp.StoreMgVo;

public interface SpOrderActiveMapMgMapper {

	/**
	 * 
	* @Title: getSpVoucherActivePlantCost 
	* @Description: 计算某个批发商下参与此活动的客户数和平台付出的成本
	* @param @param map
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<String,Object>    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> getSpVoucherActivePlantCost(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getManjianActiveStore 
	* @Description:获取参与满减活动的店铺信息以及分组信息 
	* @param @param map
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<StoreMgVo>    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	List<StoreMgVo> getManjianActiveStore(Map<String, Object> map) throws Exception;


}
