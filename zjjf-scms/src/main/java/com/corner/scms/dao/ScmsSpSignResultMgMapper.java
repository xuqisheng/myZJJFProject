/**   
* @Title: ScmsSpSignResultMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月23日 下午2:29:47 
* @version V1.0   
*/

package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SignResult;

/** 
* @ClassName: ScmsSpSignResultMgMapper 
* @Description:进销存签署协议Dao
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年2月23日 下午2:29:47 
*  
*/

public interface ScmsSpSignResultMgMapper {

	/**
	 * 
	* @Title: selectSignResult 
	* @Description:查询签署协议结果
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return SignResult    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SignResult> selectSignResult(Map<String, Object> map) throws Exception;

}
