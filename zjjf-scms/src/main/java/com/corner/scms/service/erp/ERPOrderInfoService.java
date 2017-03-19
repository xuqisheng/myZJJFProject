package com.corner.scms.service.erp;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.ERPOrderDetailRo;
import com.corner.scms.beans.ro.erp.ERPPurchaseStockRo;
import com.corner.scms.beans.vo.erp.ERPOrderDetailVo;
import com.corner.scms.beans.vo.erp.ERPOrderInfoVo;
import com.corner.scms.beans.vo.erp.ERPWarehouseUserVo;
import com.corner.scms.service.BaseService;

import java.util.List;

public interface ERPOrderInfoService extends BaseService{
	ERPManagerOrderInfo selectByOrderId(String orderId) throws Exception;
	List<ERPManagerOrderDetail> selectDetailByOrderId(String orderId) throws Exception;
	/**
	 * 添加采购单
	 * @param ro
	 * @return
     */
	ModelMsg addOrderInfo(ERPOrderDetailRo ro) throws Exception;

	/**
	 * 获取采购单列表
	 * @param ro
	 * @return
     */
	Pager<ERPManagerOrderInfo> list(ERPOrderDetailRo ro);

	/**
	 * 采购单详情
	 * @param orderId
	 * @return
     */
	ERPOrderInfoVo detail(String orderId) throws Exception;

	/**
	 * 添加入库单
	 * @param ro
	 * @return
     */
	ModelMsg addERPPurchaseStockInInfo(ERPOrderDetailRo ro , String userId , String userName)throws Exception;
	/**
	 * 校验用户名是否重复
	 * @param userName
	 * @return
     */
	boolean checkUserName(String userName);
	ModelMsg addERPWarehouseUser(ERPWarehouseUser erpWarehouse , String id) throws RuntimeException;

	Pager<ERPWarehouseUserVo> erpWarehouseUserListPage(ERPOrderDetailRo erpOrderDetailRo , String supplierId);

	ERPWarehouseUserVo getERPWarehouseUserById(String id);

	void updateDetail(ERPManagerOrderDetail detail) throws Exception;
	void updateInfo(ERPManagerOrderInfo info) throws Exception;
	ERPManagerOrderDetail selectDetailById(String id) throws Exception;
}
