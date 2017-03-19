/**   
* @Title: ERPManagerOrderInfoMgMapper.java 
* @Package com.corner.kefu.dao.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月5日 下午3:52:44 
* @version V1.0   
*/

package com.corner.kefu.dao.erp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ERPManagerOrderDetail;
import com.corner.core.beans.ERPManagerOrderInfo;
import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.core.utils.callable.SocktOperateLog;
import com.corner.kefu.beans.ro.erp.ERPManagerOrderInfoRo;
import com.corner.kefu.beans.ro.erp.ERPPurchaseStockInfoRo;
import com.corner.kefu.beans.vo.erp.ERPManagerOrderDetailVo;
import com.corner.kefu.beans.vo.erp.ERPManagerOrderInfoVo;
import com.corner.kefu.beans.vo.erp.ERPPurchaseStockDetailVo;
import com.corner.kefu.beans.vo.erp.ERPPurchaseStockInfoVo;

/** 
* @ClassName: ERPManagerOrderInfoMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月5日 下午3:52:44 
*  
*/

public interface ERPManagerOrderInfoMgMapper {

	/**
	 * 
	* @Title: getDetailListByIdArr 
	* @Description:根据ERPManagerItem id 数组获取
	* @param @param managerItemIdArr
	* @param @return
	* @return List<ERPManagerItem>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ERPManagerOrderDetail> getDetailListByIdArr(String[] managerItemIdArr);

	List<ERPManagerOrderInfoVo> getOrderInfoList(ERPManagerOrderInfoRo orderInfoRo);

	Integer getCountOrderInfoList(ERPManagerOrderInfoRo orderInfoRo);

	List<ERPManagerOrderDetailVo> getOrderInfoVoById(ERPManagerOrderInfoRo orderInfoRo);

	void delteDetailByOrderId(ERPManagerOrderInfo info);

	void deleteOrderInfoById(ERPManagerOrderInfo orderInfo);

	void checkErpOrderInfo(Map<String, Object> paramMap);

	List<ERPPurchaseStockInfoVo> getPurchaseBack(ERPPurchaseStockInfoRo stockInfoRo);

	Integer getCountPurchaseBack(ERPPurchaseStockInfoRo stockInfoRo);

	List<ERPPurchaseStockDetailVo> getPurcherStockDetailsByOrDerId(ERPPurchaseStockInfo purchaseStockInfo);

	void updatePurchaseOrder(Map<String, Object> paramMap);
}
