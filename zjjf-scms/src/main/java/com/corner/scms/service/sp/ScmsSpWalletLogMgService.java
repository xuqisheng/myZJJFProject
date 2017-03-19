/**   
* @Title: ScmsSpWalletLogMgService.java 
* @Package com.corner.scms.service.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月21日 上午10:28:55 
* @version V1.0   
*/

package com.corner.scms.service.sp;

import com.corner.core.beans.SpWalletLog;
import com.corner.scms.service.BaseService;

/** 
* @ClassName: ScmsSpWalletLogMgService 
* @Description:批发商钱包流水
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月21日 上午10:28:55 
*  
*/

public interface ScmsSpWalletLogMgService extends BaseService {

	void insertSelective(SpWalletLog spWalletLog) throws Exception;

}
