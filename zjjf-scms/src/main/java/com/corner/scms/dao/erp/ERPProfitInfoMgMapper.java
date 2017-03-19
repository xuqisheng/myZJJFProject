package com.corner.scms.dao.erp;

import com.corner.core.beans.ERPProfitInfo;
import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.scms.beans.ro.erp.ERPProfitRo;
import com.corner.scms.beans.ro.erp.ERPPurchaseStockRo;

import java.util.List;

public interface ERPProfitInfoMgMapper {
	Integer getInfoListPageByRoCOUNT(ERPProfitRo ro);
	List<ERPProfitInfo> getInfoListPageByRo(ERPProfitRo ro);
}
