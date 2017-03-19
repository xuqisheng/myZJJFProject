/**   
* @Title: ScmsSpSignResultMgService.java 
* @Package com.corner.scms.service.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月23日 下午2:26:27 
* @version V1.0   
*/

package com.corner.scms.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SignResult;
import com.corner.scms.service.BaseService;

/** 
* @ClassName: ScmsSpSignResultMgService 
* @Description:进销存协议签署接口
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年2月23日 下午2:26:27 
*  
*/

public interface ScmsSpSignResultMgService extends BaseService {

	/**
	 * 
	* @Title: selectSignResult 
	* @Description:查询协议签署协议
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return SignResult    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SignResult> selectSignResult(Map<String, Object> map) throws Exception;

    /**
     * 
    * @Title: addSignResult 
    * @Description:签署协议
    * @param @param map
    * @param @throws Exception
    * @return void    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	void addSignResult(Map<String, Object> map) throws Exception;

}
