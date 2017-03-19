package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScOrderInfo;
import com.corner.kefu.beans.ro.scms.ScOrderInfoMgRo;
import com.corner.kefu.beans.ro.scms.ScmsPurchaseRo;
import com.corner.kefu.beans.vo.ScOrderDetailVo;
import com.corner.kefu.beans.vo.ScOrderInfoVo;
import com.corner.kefu.beans.vo.sc.ScOrderInfoMgVo;

public interface ScOrderInfoMgMapper { 
	/**
     * 根据采购单号查出下单的批发商详情页面
    * @Title
    * @param @param scOrderInfoMgRo
    * @param @return
    * @2016年1月29日     
    * @author 龙五  longwu@izjjf.cn
    * @return
    * @throws
     */
	public List<ScOrderInfoMgVo> getSupplerOrderDetail(ScOrderInfoMgRo scOrderInfoMgRo);
	public int getSupplerOrderDetailCount(ScOrderInfoMgRo scOrderInfoMgRo);
	
	@SuppressWarnings("rawtypes")
	List<Map> getDetailPageList(Map<String, Object> map);
	int getDetailPageListSize(Map<String, Object> map);
    /**
     * 
    * @Title: findOrderDetailSumByItemIdAndOrderId2 
    * @Description: 查询出详情数据总和通过   ItemId 和 OrderId2
    * @param @return    设定文件
    * @return List<ScOrderDetail>    返回类型
    * @author 孟星魂	mengxinghun@izjjf.cn
    * @throws
     */
    Map<String, Object> findOrderDetailSumByItemIdAndOrderId2(Map<String, Object> map);
    
    List<ScOrderInfoVo> getList(ScmsPurchaseRo condition);
    
	int getSize(ScmsPurchaseRo condition);

	ScOrderInfoVo findOrderByOid(String orderId);

	List<ScOrderDetailVo> findOrderDetail(String orderId);
	/**
	 * 
	* @Title: selectScOrderInfo
	* @Description: 通过UpId获取订单列表信息
	* @param @param upId
	* @param @return    设定文件 
	* @return List<ScOrderInfo>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScOrderInfo> selectScOrderInfoList(Map<String, Object> map);
}