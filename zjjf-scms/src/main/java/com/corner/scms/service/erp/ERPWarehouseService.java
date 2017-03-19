package com.corner.scms.service.erp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.ERPWarehouseRo;
import com.corner.scms.beans.vo.erp.ERPWarehouseVo;

public interface ERPWarehouseService {

	Pager<ERPWarehouseVo> getWarehouseBySupplierId(ERPWarehouseRo warehouseRo);
	
	List<ERPWarehouseVo> getWarehouseByUpId(String upId, String supplierId);

	List<ERPWarehouseVo> createTree(String upId, String supplierId);

	boolean addWarehouse(ERPWarehouseRo warehouseRo);

	boolean editWarehouse(ERPWarehouseRo warehouseRo);

	ERPWarehouseVo getWarehouseById(Map<String, Object> map);

	ModelMsg delWarehouse(Byte level, String id,String supplierId);

	List<ERPWarehouse> getWarehouseLevel3(String whId) throws Exception;

	List<ERPWarehouse> getERPWarehouseLevel1All(String supplierId) throws Exception;

	ERPWarehouse getWarehouseById(String whId) throws Exception;

	ERPWarehouse getTopWarehouseByLevel3Id(String whId) throws Exception;

	/**
	 *
	* @Title: getWarehouseLevel3ByLevel1Id
	* @Description:更具level1的仓库id 获取旗下所有level=3 的仓库集合
	* @param @param wareHouseId
	* @param @return
	* @return List<ERPWarehouse>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ERPWarehouseVo> getWarehouseLevel3ByLevel1Id(String wareHouseId);
	List<ERPWarehouseVo> getWarehouseLevel3ByLevel1Id(Map<String, Object> map) throws Exception;

}
