package com.corner.scms.service.erp;

import com.corner.core.beans.ERPMarketStockDetail;
import com.corner.core.beans.ERPMarketStockInfo;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.ERPMarketStockRo;
import com.corner.scms.beans.vo.SpOrderDetailVo;

import java.util.List;


public interface ERPMarketStockService {
	void insertInfo(ERPMarketStockInfo info) throws Exception;
	void insertDetail(ERPMarketStockDetail detail) throws Exception;

	ERPMarketStockInfo getInfoById(String id) throws Exception;
	List<ERPMarketStockInfo> getInfoByPId(String PId , String POrderId) throws Exception;
	ERPMarketStockDetail getDetailById(String id) throws Exception;

	void updateInfo(ERPMarketStockInfo info) throws Exception;
	void updateDetail(ERPMarketStockDetail detail) throws Exception;

	List<ERPMarketStockDetail> getDetailsByOrderId(String orderId) throws Exception;

	Pager<ERPMarketStockInfo> getInfoListByRo(ERPMarketStockRo ro) throws Exception ;
	void updateMarketByOwner(String pid) throws Exception;
	void bacthCheck(Byte status, String userId, String userName, String... id) throws Exception;
	void bacthOutStock(String... id) throws Exception;
	void bacthSend(String... id) throws Exception;
	void bacthDelete(String userId , String userName,String ... id) throws Exception;
	void addMarketStockIn(ERPMarketStockRo ro, String userId, String userName ) throws Exception;

	List<SpOrderDetailVo> getOwnerDetailToVo(String orderId) throws Exception;
}
