package com.corner.scms.dao.erp;

import com.corner.core.beans.ERPManagerOrderInfo;
import com.corner.core.beans.ERPWarehouse;
import com.corner.scms.beans.ro.erp.ERPOrderDetailRo;
import com.corner.scms.beans.vo.erp.ERPOrderDetailVo;
import com.corner.scms.beans.vo.erp.ERPOrderInfoVo;
import com.corner.scms.beans.vo.erp.ERPWarehouseUserVo;

import java.util.List;

public interface ERPManagerOrderInfoMgMapper {
	List<ERPOrderInfoVo> getERPOrderInfoPageList(ERPOrderDetailRo ro);
	Integer getERPOrderInfoPageListCount(ERPOrderDetailRo ro);

	List<ERPWarehouseUserVo> getERPWarehouseUserListPage(ERPOrderDetailRo ro);
	Integer getERPWarehouseUserListPageCount(ERPOrderDetailRo ro);

	ERPOrderInfoVo getERPOrderInfoByOrderId(String orderId);

	Integer updateByOrderIdSelective(ERPManagerOrderInfo erpManagerOrderInfo);
	List<ERPOrderDetailVo> selectPlantItemLogListByOrderId(String orderId);
	Integer checkUserName(String name);

	ERPWarehouseUserVo getERPWarehouseUserById(String id);
}
