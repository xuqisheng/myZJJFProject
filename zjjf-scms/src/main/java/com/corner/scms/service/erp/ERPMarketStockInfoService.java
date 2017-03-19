/**   
* @Title: ERPMarketStockInfoService.java 
* @Package com.corner.scms.service.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月8日 下午2:20:58 
* @version V1.0   
*/

package com.corner.scms.service.erp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.ERPMarketStockInfoRo;
import com.corner.scms.beans.ro.erp.SpOrderInfoMgRo;
import com.corner.scms.beans.vo.SpOrderDetailMgVo;
import com.corner.scms.beans.vo.SpOrderInfoMgVo;
import com.corner.scms.beans.vo.erp.ERPMarketStockDetailVo;
import com.corner.scms.beans.vo.erp.ERPMarketStockInfoVo;

/** 
* @ClassName: ERPMarketStockInfoService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月8日 下午2:20:58 
*  
*/

public interface ERPMarketStockInfoService {

	Pager<SpOrderInfoMgVo> getSpOrderInfoList(SpOrderInfoMgRo orderInfoMgRo);

	Pager<SpOrderDetailMgVo> getOrDerDetailList(SpOrderInfoMgRo orderInfoMgRo);

	ModelMsg save(Map<String, Object> paramMap) throws Exception;

	Pager<ERPMarketStockInfoVo> getSaleOutList(ERPMarketStockInfoRo infoRo);

	ERPMarketStockInfoVo getERPMarketStockInfoVoById(ERPMarketStockInfoRo infoRo);

	List<ERPMarketStockDetailVo> getStockDetailList(ERPMarketStockInfoVo erpMarketStockInfoVo);

	ModelMsg updateStatus(Map<String, Object> paramMap);

	void updateOutStock(Map<String, Object> paramMap) throws Exception;

	void updateSend(Map<String, Object> paramMap) throws Exception;

	void updateOrderBack(Map<String, Object> paramMap);

	void updateDel(Map<String, Object> paramMap);

	Pager<Map<String, Object>> getErpMarketStockInfos(ERPMarketStockInfoRo stockInfoRo);

	/**
	 * 
	* @Title: getStockDetailListByStockInfoId 
	* @Description:查询ERPMarketStockDetail 集合
	* @param @param id ERPMarketStockInfo 集合
	* @param @return ERPMarketStockDetail 集合
	* @return List<ERPMarketStockInfoVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ERPMarketStockDetailVo> getStockDetailListByStockInfoId(String id);

	/**
	 * 
	* @Title: addSaleBack 
	* @Description:保存销售退货单
	* @param @param paramMap
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ModelMsg addSaleBack(Map<String, Object> paramMap);

	/**
	 * 
	* @Title: selectMarketStockVoById 
	* @Description:连表查询(spOrDerInfo) ERPMarketStockInfoVo
	* @param @param stockInfoRo
	* @param @return
	* @return ERPMarketStockInfoVo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ERPMarketStockInfoVo selectMarketStockVoById(ERPMarketStockInfoRo stockInfoRo);
    
	/**
	 * 
	* @Title: selectMarketStockDetail 
	* @Description:连表查询(spOrderDetail)  ERPMarketStockDetailVo
	* @param @param stockInfoVo
	* @param @return
	* @return List<ERPMarketStockDetailVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ERPMarketStockDetailVo> selectMarketStockDetail(ERPMarketStockInfoVo stockInfoVo);

	/**
	 * 
	* @Title: updateSaleOut 
	* @Description:编辑销售出库单
	* @param @param paramMap
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ModelMsg updateSaleOut(Map<String, Object> paramMap);

	/**
	 * 
	* @Title: updateSaleBackStatus 
	* @Description:审核退货单
	* @param @param paramMap
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ModelMsg updateSaleBackStatus(Map<String, Object> paramMap);

}
