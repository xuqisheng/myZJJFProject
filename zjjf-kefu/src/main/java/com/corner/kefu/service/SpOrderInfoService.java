package com.corner.kefu.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.corner.core.beans.AcActionRecord;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.ro.SpOrderInfoRo;
import com.corner.kefu.beans.ro.SpOrderMgCondition;
import com.corner.kefu.beans.vo.sp.SpOrderInfoVo;

public interface SpOrderInfoService extends BaseService{

	List<SpOrderInfo> getSpOrderInfoByOrderId(SpOrderMgCondition command);

	List<SpOrderDetail> getSpOrderDetailByOrderId(SpOrderMgCondition command);

	void getSpOrderAllForPage(String spOrderId, Model model);

	ModelMsg updateAcStatus(CheckBillCondition command);

	List<SpOrderInfo> getSpOrderInfospc(SpOrderInfoRo spOrderInfoRo);

	Integer getSpOrderInfospcCount(SpOrderInfoRo spOrderInfoRo);

	SpOrderInfo getSpOrderInfo(SpOrderInfoRo spOrderInfoRo);

	List<SpOrderDetail> getOrderDetail(String orderid);

	List<SpOrderInfoVo> selectSpOrderInfoFinace(SpOrderInfoRo spOrderInfoRo);

	Integer selectSpOrderCountOfFinace(SpOrderInfoRo spOrderInfoRo);

	List<SpOrderInfoVo> selectFinaceSpOrderInfo(SpOrderInfoRo spOrderInfoRo);

	Integer selectCountFinaceSpOrderInfo(SpOrderInfoRo spOrderInfoRo);

	List<SpOrderInfoVo> selectSpOrderInfoJiesuan(SpOrderInfoRo spOrderInfoRo);

	List<SpOrderInfoVo> selectSpOrderInfoJiesuanDe(SpOrderInfoRo spOrderInfoRo);

	int updateGetOrderAcStatus(SpOrderInfo spOrderInfo);

	void addAcSystemAction(AcActionRecord acrecord);

	Integer getSpOrderInfoCountspc(SpOrderInfoRo spOrderInfoRo);

	/**
	 * 
	* @Title: getOrdersFromSpOrderActiveMap 
	* @Description:从SpOrderActiveMap表中根据活动id和批发商id获取订单信息
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<SpOrderInfo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Pager<SpOrderInfo> getOrdersFromSpOrderActiveMap(Map<String, Object> map) throws Exception;
}
