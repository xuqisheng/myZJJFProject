/**   
* @Title: ScmsSpSalePriceMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月10日 下午4:09:43 
* @version V1.0   
*/
package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScmsSpSalePrice;

/** 
 * @ClassName: ScmsSpSalePriceMgMapper 
 * @Description:针对不同类型商品定价
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月10日 下午4:09:43  
 */
public interface ScmsSpSalePriceMgMapper {

	void insertOrUpdate(ScmsSpSalePrice scmsSpSalePrice);

	/**
	 * 
	* @Title: getPriceByCondition 
	* @Description:按条件查询价格集合
	* @param @param map
	* @param @return
	* @return List<PlantItemVo>
	* @author 杨开泰  yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScmsSpSalePrice> getPriceByCondition(Map<String, Object> map);

}
