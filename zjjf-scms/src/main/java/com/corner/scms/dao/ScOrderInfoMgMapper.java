package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScOrderInfo;
import com.corner.scms.beans.ro.sc.MaOrderInfoMgRo;
import com.corner.scms.beans.ro.sc.ScOrderInfoMgRo;
import com.corner.scms.beans.vo.sc.MaOrderInfoMgVo;
import com.corner.scms.beans.vo.sc.ScOrderDetailVo;
import com.corner.scms.beans.vo.sc.ScOrderInfoMgVo;
import com.corner.scms.beans.vo.sc.ScOrderInfoVo;

public interface ScOrderInfoMgMapper { 
	/**
	 * 获得经销商所有的订单
	* @Title
	* @Description: TODO 
	* @param @param command
	* @param @return
	* @2016年1月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
    public List<MaOrderInfoMgVo> getPageList(MaOrderInfoMgRo maOrderInfoMgRo);
    int getPageListSize(MaOrderInfoMgRo maOrderInfoMgRo);

    /***查询仓库订单列表	by	孟星魂	2016年3月1日15:35:00***/
    List<ScOrderInfoMgVo> selectWarehouseOrderPageList(ScOrderInfoMgRo scOrderInfoMgRo);
    /***查询仓库订单列表总条数	by	孟星魂	2016年3月1日15:35:00***/
    int selectWarehouseOrderPageListCount(ScOrderInfoMgRo scOrderInfoMgRo);
   /**********************************************************************************************************************/
  
   
   
    
    
    
	/**
	 * 获取仓库入库订单列表
	* @Title
	* @Description: TODO 
	* @param @param scOrderInfoMgRo
	* @param @return
	* @2016年1月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
    public List<MaOrderInfoMgVo> getWarehouseOrderList(MaOrderInfoMgRo maOrderInfoMgRo);
    public int getWarehouseOrderCount(MaOrderInfoMgRo maOrderInfoMgRo);
	/**************************************仓库提单************************************************/	
	/**
	 * 	根据id查出采购单的状态 
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年3月2日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
    public Byte getStatusById(Map<String, Object> map);
    
    
	/**
	 * 仓库打印订单
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年3月3日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ScOrderInfoMgVo printWarehouseOrder(Map<String, Object> map);

	/**
	 * 根据id查询选中打印的商品
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年3月3日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public List<ScOrderDetailVo> getPrintGoodsById(Map<String, Object> map);
    
    /**************************************************************************************/
    
    /**
     * 
    * @Title: getScOrderInfo 
    * @Description:根据批发商id和ScOrderInfO Id 查询订单
    * @param @param map
    * @param @return
    * @return ScOrderInfo    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	ScOrderInfo getScOrderInfo(Map<String, Object> map);

	/**
	 * 
	* @Title: selectScOrderInfoByManagerIdAndScmsGroupId 
	* @Description: 通过订单详情对象中的managerId 和  scmsGroupId 查询ScOrderInfo表  状态status=6,level=2的数据
	* @param @param scOrderDetail 订单详情对象
	* @param @return    设定文件 
	* @return ScOrderInfo    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	ScOrderInfo selectScOrderInfoByManagerIdAndScmsGroupId(ScOrderDetail scOrderDetail);

	

	public List<ScOrderInfoVo> findMyOrderInfo(ScOrderInfoMgRo scOrderInfoMgRo);
	
	/**
	 * 
	* @Title: updatePaymentStatus 
	* @Description:
	* @param @param scOrderInfo
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updatePaymentStatus(ScOrderInfo scOrderInfo) throws Exception;

	
	//修改订单状态
	public void updateOrderStatus(Map<String, Object> map);

	
	//根据 批发商id 和  订单 id 去查询数据
	public ScOrderInfo findMyorderInfoByRo(ScOrderInfoMgRo scOrderInfoMgRo);
	//批发商 删除 订单
	public int deleteMyOrderinfo(ScOrderInfoMgRo scOrderInfoMgRo);
	public int findMyOrderInfoSize(ScOrderInfoMgRo scOrderInfoMgRo);
	
	/**
	 * 
	* @Title: getAllNoPayOrderInfoList 
	* @Description:查询所有距离现在超过24小时未支付且未被删除的订单
	* @param @return
	* @return List<ScOrderInfo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<ScOrderInfo> getAllNoPayOrderInfoList() throws Exception;
	
	/**
	 * 
	* @Title: deleteNoPayOrderInfo 
	* @Description:删除订单
	* @param @param scOrderInfo
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void deleteNoPayOrderInfo(ScOrderInfo scOrderInfo) throws Exception;
	
	/**
	 * 
	* @Title: updateOrderPrice 
	* @Description:update ScOrderInfo的 订单金额
	* @param @param scOrderInfo
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateOrderPrice(ScOrderInfo scOrderInfo) throws Exception;
	
	/**
	 * 
	* @Title: getScOrderInfoById 
	* @Description:根据订单id获取订单
	* @param @param out_trade_no
	* @param @return
	* @param @throws Exception
	* @return ScOrderInfo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public ScOrderInfo getScOrderInfoById(String out_trade_no) throws Exception;
	
	public ScOrderInfo findOrderByIdAndSupplierid(ScOrderInfoMgRo scOrderInfoMgRo);
	
	public void updateStatesInfo(ScOrderInfoMgRo scOrderInfoMgRo);
	
	
	public void updateStatesDetail(ScOrderInfoMgRo scOrderInfoMgRo);
	
	
	public void deletedetail(ScOrderInfoMgRo scOrderInfoMgRo);
	
	public void updateStatesDetailByid(String id);
	
	public ScOrderInfo selectByForWxTradeNo(String out_trade_no);
	
	//仓库中修改采购单状态
	public void updatePurchaseOrderStatus(Map<String, Object> map);
	
	/**
	 * 
	* @Title: getAllOrderInfo 
	* @Description:查询所有订单的信息,包括被删除的订单
	* @param @param scOrderInfoMgRo
	* @param @return
	* @param @throws Exception
	* @return List<ScOrderInfoVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<ScOrderInfoVo> getAllOrderInfo(ScOrderInfoMgRo scOrderInfoMgRo) throws Exception;
	public void updateSupplierGoodsStatus(Map<String, Object> map2);
	
	/**
	 * 
	* @Title: getLevelIs2OrderByOrderId 
	* @Description:根据ScOrderDetail 中的orderId2查询Level为2的订单信息
	* @param @param orderId2
	* @param @return
	* @param @throws Exception
	* @return ScOrderInfo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public ScOrderInfo getOrderByOrderId(String orderId) throws Exception;
	/**
	 * 
	* @Title: updateLeve2OrderStatus 
	* @Description:修改Level为2的订单状态status 为13
	* @param @param scOrderInfo
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateLeve2OrderStatus(ScOrderInfo scOrderInfo) throws Exception;
	
	/**
	 * 
	* @Title: updateThirdPayment 
	* @Description:更新订单第三方支付信息
	* @param @param scOrderInfo
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateThirdPayment(ScOrderInfo scOrderInfo) throws Exception;
	
	/**
	 * 
	* @Title: updateMarketPrice 
	* @Description:更新订单市场价
	* @param @param scOrderInfo
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateMarketPrice(ScOrderInfo scOrderInfo) throws Exception;
	
	/**
	 * 
	* @Title: batchSaveLevel2 
	* @Description:批量新增level=2的ScOrderInfo数据
	* @param @param scOrderInfoMap
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void batchSaveLevel2(List<ScOrderInfo> scOrderInfoLevel2s) throws Exception;
	
	/**
	 * 
	* @Title: findSonInfosByPid 
	* @Description:根据父订单的id去查询所有的子单
	* @param @param id
	* @param @throws Exception
	* @return void    List<ScOrderInfo>
	*@author 海灵子      hailingzi@izjjf.cn
	* @throws
	 */
	public List<ScOrderInfo> findSonInfosByPid(Map<String, Object> map);

	
	/**
	 * 
	* @Title: getLevel2List 
	* @Description:根据批发商父单,获取Pid为父单id的ScOrderInfo
	* @param @param scOrderInfoLevel1 批发商父单
	* @param @return
	* @param @throws Exception
	* @return List<ScOrderInfo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<ScOrderInfo> getLevel2List(ScOrderInfo scOrderInfoLevel1) throws Exception;

}