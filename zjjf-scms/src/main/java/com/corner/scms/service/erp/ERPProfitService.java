package com.corner.scms.service.erp;

import com.corner.core.beans.ERPMarketStockDetail;
import com.corner.core.beans.ERPMarketStockInfo;
import com.corner.core.beans.ERPProfitDetail;
import com.corner.core.beans.ERPProfitInfo;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.ERPMarketStockRo;
import com.corner.scms.beans.ro.erp.ERPProfitRo;
import com.corner.scms.beans.vo.erp.ERPProfitVo;

import java.util.List;


public interface ERPProfitService {
	void insertInfo(ERPProfitInfo info) throws Exception;
	void insertDetail(ERPProfitDetail detail) throws Exception;

	ERPProfitInfo getInfoById(String id) throws Exception;
	ERPProfitDetail getDetailById(String id) throws Exception;

	void updateInfo(ERPProfitInfo info) throws Exception;
	void updateDetail(ERPProfitDetail detail) throws Exception;

	List<ERPProfitDetail> getDetailsByOrderId(String orderId) throws Exception;

	Pager<ERPProfitInfo> getInfoListByRo(ERPProfitRo ro) throws Exception ;

	void bacthCheck(Byte status, String userId , String userName, String ... id) throws Exception;
	void bacthDelete(String userId , String userName,String ... id) throws Exception;

	ERPProfitVo selectStockListByOrderId(String orderId) throws Exception;


	void addERPProfitInfo(ERPProfitRo ro, String userId, String userName) throws Exception;
}
