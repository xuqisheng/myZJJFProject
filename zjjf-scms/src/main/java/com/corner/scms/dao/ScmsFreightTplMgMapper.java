/**   
* @Title: ScmsFreightTplMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月21日 上午10:47:39 
* @version V1.0   
*/

package com.corner.scms.dao;

import com.corner.core.beans.Supplier;
import com.corner.scms.beans.vo.ScmsFreightTplVo;

/** 
* @ClassName: ScmsFreightTplMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月21日 上午10:47:39 
*  
*/

public interface ScmsFreightTplMgMapper {


	/**
	 * 
	* @Title: getFreightTpl 
	* @Description:获取批发商关联经销区域的运费模板
	* @param @param supplier
	* @param @return
	* @param @throws Exception
	* @return ScmsFreightTplVo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ScmsFreightTplVo getFreightTpl(Supplier supplier) throws Exception;

}
