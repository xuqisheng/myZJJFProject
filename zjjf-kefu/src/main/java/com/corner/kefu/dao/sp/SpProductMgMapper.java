/**
 * 
 */
package com.corner.kefu.dao.sp;

import java.util.Map;

/**
 * 
 * @ClassName: SpProductDao
 * 
 * @Description: 定格商品管理dao
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年10月20日 下午3:34:07
 */
public interface SpProductMgMapper{
	/**
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void batchUpdatePlantItemBySupplierIdAndSpGroupId(Map<String, Object> map);
	
	public void batchUpdatePlantItemPreBySupplierIdAndSpGroupId(Map<String, Object> map);
	
	public void updateSpProductBySupplierIdAndSpGroupId(Map<String, Object> map);
	
	public void updatePlantItemPreBySupplierIdAndSpGroupId(Map<String, Object> map);
}
