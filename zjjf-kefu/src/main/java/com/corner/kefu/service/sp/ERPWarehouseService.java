/**   
* @Title: ERPWarehouseService.java 
* @Package com.corner.kefu.service.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月23日 下午5:24:09 
* @version V1.0   
*/

package com.corner.kefu.service.sp;

import java.util.List;

import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.erp.ERPWarehouseRo;
import com.corner.kefu.beans.vo.erp.ERPWarehouseVo;

/** 
* @ClassName: ERPWarehouseService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月23日 下午5:24:09 
*  
*/

public interface ERPWarehouseService {
     
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
	List<ERPWarehouse> getSupplierAllERPWarehouseById(String id);

	/**
	 * 
	* @Title: getAllJsonErpWareHose 
	* @Description:异步分页获取仓库数据
	* @param @param warehouseRo
	* @param @return
	* @return Pager<ERPWarehouse>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Pager<ERPWarehouseVo> getAllJsonErpWareHose(ERPWarehouseRo warehouseRo);

	ERPWarehouse selectByPrimaryKey(String whId);

}
