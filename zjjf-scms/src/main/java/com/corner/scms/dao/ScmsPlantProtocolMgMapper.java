/**   
* @Title: ScmsPlantProtocolMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月23日 下午2:15:54 
* @version V1.0   
*/

package com.corner.scms.dao;

import com.corner.core.beans.PlantProtocol;

/** 
* @ClassName: ScmsPlantProtocolMgMapper 
* @Description:进销存协议Dao
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年2月23日 下午2:15:54 
*  
*/

public interface ScmsPlantProtocolMgMapper {

	/**
	 * 
	* @Title: getLastPlantProtocol 
	* @Description:获取最新版协议
	* @param @return
	* @param @throws Exception
	* @return PlantProtocol    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	PlantProtocol getLastPlantProtocol() throws Exception;

}
