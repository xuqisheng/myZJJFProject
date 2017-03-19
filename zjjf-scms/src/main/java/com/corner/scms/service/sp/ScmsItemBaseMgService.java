/**   
* @Title: ScmsItemBaseMgService.java 
* @Package com.corner.scms.service.sp 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月15日 下午4:55:52 
* @version V1.0   
*/
package com.corner.scms.service.sp;

import java.util.List;

import com.corner.core.beans.ItemBase;
import com.corner.scms.service.BaseService;

/** 
 * @ClassName: ScmsItemBaseMgService 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月15日 下午4:55:52  
 */
public interface ScmsItemBaseMgService extends BaseService {
	  /**
	   * 
	  * @Title: getAllItemBase 
	  * @Description:
	  * @param @return
	  * @return List<ItemBase>
	  * @author 杨开泰 yangkaitai@izjjf.cn
	  * @throws
	   */
      List<ItemBase> getAllItemBase();
      
      void Update(ItemBase itemBase);
}
