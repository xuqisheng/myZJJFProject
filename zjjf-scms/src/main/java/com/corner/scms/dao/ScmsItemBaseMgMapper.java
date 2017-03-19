/**   
* @Title: ScmsItemBaseMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月15日 下午5:00:46 
* @version V1.0   
*/
package com.corner.scms.dao;

import java.util.List;

import com.corner.core.beans.ItemBase;

/** 
 * @ClassName: ScmsItemBaseMgMapper 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月15日 下午5:00:46  
 */
public interface ScmsItemBaseMgMapper {
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

}
