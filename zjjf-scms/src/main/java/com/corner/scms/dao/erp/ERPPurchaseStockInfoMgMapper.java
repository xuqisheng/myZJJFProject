package com.corner.scms.dao.erp;

import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.scms.beans.ro.erp.ERPPurchaseStockRo;

import java.util.List;

public interface ERPPurchaseStockInfoMgMapper {
	Integer getInfoListPageByRoCOUNT(ERPPurchaseStockRo ro);
	List<ERPPurchaseStockInfo> getInfoListPageByRo(ERPPurchaseStockRo ro);
}
