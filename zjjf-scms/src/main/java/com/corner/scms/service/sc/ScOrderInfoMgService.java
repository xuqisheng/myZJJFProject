package com.corner.scms.service.sc;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.sc.MaOrderInfoMgRo;
import com.corner.scms.beans.ro.sc.ScOrderInfoMgRo;
import com.corner.scms.beans.vo.sc.MaOrderInfoMgVo;
import com.corner.scms.beans.vo.sc.ScOrderInfoMgVo;
import com.corner.scms.beans.vo.sc.ScOrderInfoVo;
import com.corner.scms.service.BaseService;

public interface ScOrderInfoMgService extends BaseService{
	

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
	public Pager<MaOrderInfoMgVo> getScOrderInfoList(MaOrderInfoMgRo maOrderInfoMgRo);
	
	
	/**
	 * 根据id获取订单详情
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年1月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
    public MaOrderInfoMgVo getScOrderInfoById(String id);
	
	/**
	 * 获取仓库订单列表
	* @Title
	* @Description: TODO 
	* @param @param scOrderInfoMgRo
	* @param @return
	* @2016年1月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
    public Pager<MaOrderInfoMgVo> getWarehouseOrderList(MaOrderInfoMgRo maOrderInfoMgRo);
    /**查询仓库配送单信息**/
    public Pager<ScOrderInfoMgVo> selectWarehouseOrderPageList(ScOrderInfoMgRo scOrderInfoMgRo);
    

	
//    /**
//	 * 仓库配送单列表
//	* @Title
//	* @Description: TODO 
//	* @param @param scOrderInfoMgRo
//	* @param @return
//	* @2016年3月1日     
//	* @author 龙五  longwu@izjjf.cn
//	* @return
//	* @throws
//	 */
//	public Pager<ScOrderInfoMgVo> getWarehouseDeliveryList(ScOrderInfoMgRo scOrderInfoMgRo);

    
	/**
	 * 
	* @Title: confiremdOrderInfo 
	* @Description:提交购物车订单
	* @param @param supplier
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void confiremdOrderInfo(Supplier supplier) throws Exception;

	/**
	 * 
	* @Title: getScOrderInfo 
	* @Description:根据批发商id和ScOrderInfo Id查询订单
	* @param @param map
	* @param @return
	* @return ScOrderInfo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ScOrderInfo getScOrderInfo(Map<String, Object> map);

	/**
	 * 
	* @Title: updateAddScOrderInfo 
	* @Description:合单
	* @param @param scorderInfo
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void updateAddScOrderInfo(Map<String, Object> map) throws Exception;

	
	/**
	 * 
	* @Title: selectByPrimaryKey 
	* @Description:查询订单
	* @param @param orderid
	* @param @return
	* @return ScOrderInfo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ScOrderInfo selectByPrimaryKey(String orderid);
	


	
	public List<ScOrderInfoVo> findMyOrderInfo(ScOrderInfoMgRo scOrderInfoMgRo) ;


	public void updateOrderStatus(Map<String, Object> map) throws Exception;


	public int findMyOrderInfoSize(ScOrderInfoMgRo scOrderInfoMgRo);

    /**
     * 
    * @Title: getAllNoPayOrderInfoList 
    * @Description:查询所有距离现在超过24小时未支付且未被删除的订单
    * @param @return
    * @param @throws Exception
    * @return List<ScOrderInfo>    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	public List<ScOrderInfo> getAllNoPayOrderInfoList() throws Exception;

	public int updateByPrimaryKeySelective(ScOrderInfo scOrderInfo) throws Exception;


	public Object deleteinfo(ScOrderInfoMgRo scOrderInfoMgRo);


    /**
     * 
    * @Title: selectByForWxTradeNo 
    * @Description:通过提交给微信的forWxTradeNo 查询订单
    * @param @param out_trade_no
    * @param @return
    * @return ScOrderInfo    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	public ScOrderInfo selectByForWxTradeNo(String out_trade_no);

	public ModelMsg updateGoodsStatus(String orderId, String[] ids, String[] names);
	
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
	public ScOrderInfoMgVo printWarehouseOrder(String orderId,String[] ids);

    /**
     * 
    * @Title: getAllDetail 
    * @Description:查询所有订单的详情信息,包括被删除的订单
    * @param @param scOrderInfoMgRo
    * @param @return
    * @param @throws Exception
    * @return List<ScOrderInfoVo>    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	public List<ScOrderInfoVo> getAllDetail(ScOrderInfoMgRo scOrderInfoMgRo) throws Exception;


	/**
	 * 
	* @Title: updateThirdPayment 
	* @Description:更新订单第三方支付方式和状态
	* @param @param scOrderInfo
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateThirdPayment(ScOrderInfo scOrderInfo) throws Exception;
	MaOrderInfo selectMaOrderInfoById(String id);
	
	List<ScOrderInfo> findSonInfosByPid(String pid , Integer level);
	
	ModelMsg updateMaOrderInfo(MaOrderInfo maOrderInfo) throws Exception;
}
