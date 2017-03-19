/**   
* @Title: ERPManagerOrderInfoService.java 
* @Package com.corner.kefu.service.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月5日 下午3:45:06 
* @version V1.0   
*/

package com.corner.kefu.service.erp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.core.beans.ERPManagerOrderInfo;
import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.erp.ERPManagerOrderInfoRo;
import com.corner.kefu.beans.ro.erp.ERPPurchaseStockInfoRo;
import com.corner.kefu.beans.vo.erp.ERPManagerOrderDetailVo;
import com.corner.kefu.beans.vo.erp.ERPManagerOrderInfoVo;
import com.corner.kefu.beans.vo.erp.ERPPurchaseStockDetailVo;
import com.corner.kefu.beans.vo.erp.ERPPurchaseStockInfoVo;

/** 
* @ClassName: ERPManagerOrderInfoService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月5日 下午3:45:06 
*  
*/

public interface ERPManagerOrderInfoService {

	/**
	 * 
	* @Title: save 
	* @Description:保存采购单
	* @param @param paramMap
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ModelMsg save(HashMap<String, Object> paramMap);

	Pager<ERPManagerOrderInfoVo> getOrderInfoList(ERPManagerOrderInfoRo orderInfoRo);

	ERPManagerOrderInfo selectById(String id);

	List<ERPManagerOrderDetailVo> getOrderInfoVoById(ERPManagerOrderInfoRo orderInfoRo);

	ModelMsg delErpManagerOrderInfo(String delManagerIdStr);

	ModelMsg checkErpOrderInfo(Map<String, Object> paramMap);

	Pager<ERPPurchaseStockInfoVo> getPurchaseBack(ERPPurchaseStockInfoRo stockInfoRo);

	ERPPurchaseStockInfo getPurchaseStockInfoById(ERPPurchaseStockInfoRo stockInfoRo);

	List<ERPPurchaseStockDetailVo> getPurcherStockDetailsByOrDerId(ERPPurchaseStockInfo purchaseStockInfo);

	ModelMsg updatePurchaseOrder(Map<String, Object> paramMap);


}
