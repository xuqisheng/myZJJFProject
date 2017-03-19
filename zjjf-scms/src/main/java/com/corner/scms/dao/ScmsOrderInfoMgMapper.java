package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScmsOrderDetail;
import com.corner.core.beans.ScmsOrderInfo;
import com.corner.scms.beans.ro.ScmsOrderInfoMgCondition;

public interface ScmsOrderInfoMgMapper { 
	/**
	 * 
	* @Title: getPageList 
	* @Description: 查询集合 
	* @param command
	* @return List<ScmsOrderInfo>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
    List<ScmsOrderInfo> getPageList(ScmsOrderInfoMgCondition command);
    
    /**
     * 
    * @Title: getPageListSize 
    * @Description: 查询总条数 
    * @param command
    * @return int    返回类型
    * @author 孟星魂	mengxinghun@izjjf.cn
    * @throws
     */
    int getPageListSize(ScmsOrderInfoMgCondition command);
    
    /**
     * 
    * @Title: getOrderTodayAll 
    * @Description: 查询今天订单总数据  
    * @param @param info
    * @param @return    设定文件
    * @return Map<String,Object>    返回类型
    * @author 孟星魂	mengxinghun@izjjf.cn
    * @throws
     */
    Map<String, Object> getOrderTodayAll(String spId);
    
    /**
     * 
    * @Title: getScmsOrderTodayAll 
    * @Description: 查询今日订单笔数，订单金额
    * @param @return    设定文件
    * @return Map<String,Object>    返回类型
    * @author 孟星魂	mengxinghun@izjjf.cn
    * @throws
     */
    Map<String, Object> getScmsOrderTodayAll(String spId);
    
    /**
     * 
    * @Title: selectByOrderId 
    * @Description: 查询单条订单信息通过orderId 
    * @param @return    设定文件
    * @return ScmsOrderInfo    返回类型
    * @author 孟星魂	mengxinghun@izjjf.cn
    * @throws
     */
    ScmsOrderInfoMgCondition selectByOrderId(String orderId);
    
    /**
     * 
    * @Title: getDetailByOrderId 
    * @Description: 查询子订单信息
    * @param @param orderId
    * @param @return    设定文件
    * @return List<ScmsOrderDetail>    返回类型
    * @author 孟星魂	mengxinghun@izjjf.cn
    * @throws
     */
    List<ScmsOrderDetail> getDetailByOrderId(String orderId);
    
}