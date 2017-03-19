/**   
* @Title: SystemInfoService.java 
* @Package com.corner.kefu.service 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月24日 上午10:46:18 
* @version V1.0   
*/

package com.corner.kefu.service;

import com.corner.core.beans.SystemInfo;

/** 
* @ClassName: SystemInfoService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午10:46:18 
*  
*/

public interface SystemInfoService extends BaseService {

	SystemInfo getKDB_Price_Task(String string) throws Exception;

	int updateSystemInfo(SystemInfo systemInfo);

	SystemInfo getSystemInfo(String string);

}
