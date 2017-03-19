/**   
* @Title: ScmsManagerRo.java 
* @Package com.corner.scms.beans.ro 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月8日 上午9:48:27 
* @version V1.0   
*/

package com.corner.scms.beans.ro;

import com.corner.core.beans.ScmsManager;
import com.corner.scms.config.CommonPageConfig;

/** 
* @ClassName: ScmsManagerRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月8日 上午9:48:27 
*  
*/

public class ScmsManagerRo extends ScmsManager {

	private static final long serialVersionUID = 1L;
	
	private int pageIndex = CommonPageConfig.common_kkPage_index;
	private int pageSize = CommonPageConfig.common_kkPage_size;
	
		

}
