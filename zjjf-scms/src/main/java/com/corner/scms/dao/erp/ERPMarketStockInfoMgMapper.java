/**   
* @Title: ERPMarketStockInfoMgMapper.java 
* @Package com.corner.scms.dao.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月8日 下午2:22:31 
* @version V1.0   
*/

package com.corner.scms.dao.erp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ERPMarketStockDetail;
import com.corner.core.beans.ERPMarketStockInfo;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.scms.beans.ro.erp.ERPMarketStockInfoRo;
import com.corner.scms.beans.ro.erp.ERPMarketStockRo;
import com.corner.scms.beans.ro.erp.SpOrderInfoMgRo;
import com.corner.scms.beans.vo.SpOrderDetailMgVo;
import com.corner.scms.beans.vo.SpOrderInfoMgVo;
import com.corner.scms.beans.vo.erp.ERPMarketStockDetailVo;
import com.corner.scms.beans.vo.erp.ERPMarketStockInfoVo;

/** 
* @ClassName: ERPMarketStockInfoMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月8日 下午2:22:31 
*  
*/

public interface ERPMarketStockInfoMgMapper {

	List<SpOrderInfoMgVo> getSpOrderInfoList(SpOrderInfoMgRo orderInfoMgRo);

	Integer getCountSpOrderInfoList(SpOrderInfoMgRo orderInfoMgRo);
	
	Integer getInfoListPageByRoCOUNT(ERPMarketStockRo ro);
	List<ERPMarketStockInfo> getInfoListPageByRo(ERPMarketStockRo ro);


	Integer getCountOrDerDetailList(SpOrderInfoMgRo orderInfoMgRo);

	List<SpOrderDetailMgVo> getOrDerDetailList(SpOrderInfoMgRo orderInfoMgRo);

	List<ERPMarketStockInfoVo> getSaleOutList(ERPMarketStockInfoRo infoRo);

	Integer getCountSaleOutList(ERPMarketStockInfoRo infoRo);

	ERPMarketStockInfoVo getERPMarketStockInfoVoById(ERPMarketStockInfoRo infoRo);

	List<ERPMarketStockDetailVo> getStockDetailList(ERPMarketStockInfoVo erpMarketStockInfoVo);

	void updateStatus(Map<String, Object> paramMap);

	void updateOutStock(Map<String, Object> paramMap);

	void updateSend(Map<String, Object> paramMap);

	void updateOrderBack(Map<String, Object> paramMap);

	List<ERPMarketStockInfoVo> getListByIds(String[] idArr);

	void updateBatchDelete(List<ERPMarketStockInfoVo> list);

	void updateBatchDetailDelete(List<ERPMarketStockInfoVo> list);

	List<ERPMarketStockInfo> getStockInfoByIds(Map<String, Object> paramMap);

	List<ERPMarketStockDetail> getMarketStockDetails(ERPMarketStockInfo erpMarketStockInfo);

	void updateSpOrderInfoStockNum(List<ERPMarketStockDetail> details);

	List<SpOrderDetail> getSpOrDerDetails(ERPMarketStockInfo erpMarketStockInfo);

	SpOrderInfo getSpOrderInfoById(String getpId);

	void updateSpOrderInfoIsOutStock(SpOrderInfo orderInfo);

	List<Map<String, Object>> getErpMarketStockInfos(ERPMarketStockInfoRo stockInfoRo);

	Integer getCountErpMarketStockInfos(ERPMarketStockInfoRo stockInfoRo);

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
	* @Title: deleteERPMarketStockDetailByOrderId 
	* @Description:根据orderId 删除 ERPMarketStockDetail
	* @param @param stockInfo
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void deleteERPMarketStockDetailByOrderId(ERPMarketStockInfo stockInfo);

	List<ERPMarketStockInfoVo> getAllSendStockVo(ERPMarketStockInfoVo erpMarketStockInfoVo);

	List<ERPMarketStockInfoVo> getAllConfrimStokVo(ERPMarketStockInfoVo erpMarketStockInfoVo);



}
