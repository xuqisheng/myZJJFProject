/**   
* @Title: ScmsItemCatelogMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月7日 下午2:00:45 
* @version V1.0   
*/
package com.corner.scms.dao;

import java.util.List;

import com.corner.core.beans.ItemCatelog;

/** 
 * @ClassName: ScmsItemCatelogMgMapper 
 * @Description:商品类别Dao
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月7日 下午2:00:45  
 */
public interface ScmsItemCatelogMgMapper {

	/**
	* @Title: selectAll 
	* @Description:查询所有商品类别
	* @param @return
	* @return List<ItemCatelog>
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ItemCatelog> selectAll();

}
