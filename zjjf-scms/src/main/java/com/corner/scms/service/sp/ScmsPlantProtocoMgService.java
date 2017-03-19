/**   
* @Title: ScmsPlantProtocoMgService.java 
* @Package com.corner.scms.service.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月23日 下午2:09:33 
* @version V1.0   
*/

package com.corner.scms.service.sp;

import com.corner.core.beans.PlantProtocol;
import com.corner.scms.service.BaseService;

/** 
* @ClassName: ScmsPlantProtocoMgService 
* @Description:进销存协议接口
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年2月23日 下午2:09:33 
*  
*/

public interface ScmsPlantProtocoMgService extends BaseService{
  
	PlantProtocol getLastPlantProtocol() throws Exception;

}
