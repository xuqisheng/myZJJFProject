/**   
* @Title: ERPWarehouseMgMapper.java 
* @Package com.corner.kefu.dao 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月23日 下午5:28:05 
* @version V1.0   
*/

package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.ERPWarehouse;
import com.corner.kefu.beans.ro.erp.ERPWarehouseRo;
import com.corner.kefu.beans.vo.erp.ERPWarehouseVo;

/** 
* @ClassName: ERPWarehouseMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月23日 下午5:28:05 
*  
*/

public interface ERPWarehouseMgMapper {

	/**
	 * 
	* Title: getSupplierAllERPWarehouseById 
	* Description:根据批发商id查询批发商所有的仓库 
	* @param id
	* @return 
	* @see com.corner.kefu.service.sp.ERPWarehouseService#getSupplierAllERPWarehouseById(java.lang.String)
	 */
	List<ERPWarehouse> getSupplierAllERPWarehouseById(String id);

	/**
	 * 
	* @Title: getSupplierAllERPWarehouseById 
	* @Description:根据批发商id获取
	* @param @param id
	* @param @return
	* @return List<ERPWarehouse>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ERPWarehouseVo> getAllJsonErpWareHose(ERPWarehouseRo warehouseRo);

	Integer getCountyAllJsonErpWareHose(ERPWarehouseRo warehouseRo);

}
