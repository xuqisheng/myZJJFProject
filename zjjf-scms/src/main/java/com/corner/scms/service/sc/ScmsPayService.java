/**   
* @Title: ScmsPayService.java 
* @Package com.corner.scms.service.sc 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月12日 上午4:45:25 
* @version V1.0   
*/

package com.corner.scms.service.sc;

import com.corner.core.beans.Pay;
import com.corner.scms.service.BaseService;

/** 
* @ClassName: ScmsPayService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月12日 上午4:45:25 
*  
*/

public interface ScmsPayService extends BaseService{

	void savePayRecord(Pay pay) throws Exception;

}
