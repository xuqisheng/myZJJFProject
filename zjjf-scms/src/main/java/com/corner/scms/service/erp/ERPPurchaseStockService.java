package com.corner.scms.service.erp;

import com.corner.core.beans.ERPPurchaseStockDetail;
import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.ERPPurchaseStockRo;
import com.corner.scms.beans.vo.erp.ERPPurchaseStockVo;

import java.util.List;


public interface ERPPurchaseStockService {
	void insertInfo(ERPPurchaseStockInfo info) throws Exception;
	void insertDetail(ERPPurchaseStockDetail detail) throws Exception;

	ERPPurchaseStockInfo getInfoById(String id) throws Exception;
	ERPPurchaseStockDetail getDetailById(String id) throws Exception;

	void updateInfo(ERPPurchaseStockInfo info) throws Exception;
	void updateDetail(ERPPurchaseStockDetail detail) throws Exception;

	List<ERPPurchaseStockDetail> getDetailsByOrderId(String orderId) throws Exception;

	Pager<ERPPurchaseStockInfo> getInfoListByRo(ERPPurchaseStockRo ro) throws Exception ;

	void bacthCheck(Byte status, String userId , String userName, String ... id) throws Exception;

	void bacthDelete(String userId , String userName,String ... id) throws Exception;

	List<ERPPurchaseStockVo> selectStockListByPorderId(String orderId) throws Exception;
	ERPPurchaseStockVo selectStockListByOrderId(String orderId) throws Exception;


	void addERPPurchaseStockOutInfo(ERPPurchaseStockRo ro, String userId, String userName) throws Exception;
}
