/**   
* @Title: ScmsItemCatelogMgService.java 
* @Package com.corner.scms.service.sp 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月7日 下午1:57:36 
* @version V1.0   
*/
package com.corner.scms.service.sp;

import java.util.List;

import com.corner.core.beans.ItemCatelog;
import com.corner.scms.service.BaseService;

/** 
 * @ClassName: ScmsItemCatelogMgService 
 * @Description:商品类别业务类
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月7日 下午1:57:36  
 */
public interface ScmsItemCatelogMgService extends BaseService {

	/**
	 * 查询所有商品类别
	* @Title: selectAll 
	* @Description:
	* @param @return
	* @return List<ItemCatelog>
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ItemCatelog> selectAll();

}
