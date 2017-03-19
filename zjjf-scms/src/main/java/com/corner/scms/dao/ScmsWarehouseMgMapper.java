/**   
* @Title: ScmsMgWarehouseMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月28日 下午2:04:42 
* @version V1.0   
*/

package com.corner.scms.dao;

import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.Supplier;

/** 
* @ClassName: ScmsMgWarehouseMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月28日 下午2:04:42 
*  
*/

public interface ScmsWarehouseMgMapper {

	/**
	 * 
	* @Title: selectScmsWarehouseBySupplier 
	* @Description:查询批发商所属经销区域相关联的仓库
	* @param @param supplier
	* @param @return
	* @return ScmsWarehouse    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ScmsWarehouse selectScmsWarehouseBySupplier(Supplier supplier);

}
