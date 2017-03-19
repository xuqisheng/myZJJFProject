/**   
 * @Title: ScOrderDetailMgMapper.java 
 * @Package com.corner.scms.dao 
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn 
 * @date 2016年1月21日 下午6:16:19 
 * @version V1.0   
 */

package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.Supplier;
import com.corner.scms.beans.ro.sc.ScOrderDetailRo;
import com.corner.scms.beans.ro.sc.ScOrderInfoMgRo;
import com.corner.scms.beans.vo.sc.ScOrderDetailVo;

/** 
 * @ClassName: ScOrderDetailMgMapper 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn
 * @date 2016年1月21日 下午6:16:19 
 *  
 */

public interface ScOrderDetailMgMapper {
	List<ScOrderDetail> findOrderDetailList(Map<String, Object> map);

	void batchSave(List<ScOrderDetailVo> scOrderDetailList) throws Exception;

	/**
	 * 
	 * @Title: getOrderDetailListBySupplier 
	 * @Description:查询批发商单次购物的订单详情列表
	 * @param @param supplier 登录的批发商
	 * @param @return
	 * @param @throws Exception
	 * @return List<ScOrderDetail>    返回类型
	 *@author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	List<ScOrderDetailVo> getOrderDetailListBySupplier(Supplier supplier) throws Exception;

	/**
	 *  查询经销商下所有的订单
	 * @Title
	 * @Description: TODO 
	 * @param @param map
	 * @param @return
	 * @2016年1月25日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	public List<ScOrderDetailVo> getScOrderDetailById(Map<String, Object> map);

	/**
	 * 获取订单明细汇总
	 * @Title
	 * @Description: TODO 
	 * @param @param scOrderDetailRo
	 * @param @return
	 * @2016年1月27日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	public ScOrderDetailVo getOrderDetail(ScOrderDetailRo scOrderDetailRo);

	/**
	 * 查询订单明细 列表
	 * @Title
	 * @Description: TODO 
	 * @param @param scOrderDetailRo
	 * @param @return
	 * @2016年1月27日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	public List<ScOrderDetailVo> getOrderDetailList(ScOrderDetailRo scOrderDetailRo);
	//查询订单明细 列表总数
	public int getOrderDetailListCount(ScOrderDetailRo scOrderDetailRo);


	/**
	 * 
	* @Title: getOrderDetailListByScOrderInfo 
	* @Description:通过ScOrderInfo查询ScOrderDetail集合 
	* @param @param scOrderInfo
	* @param @return    设定文件 
	* @return List<ScOrderDetail>    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScOrderDetail> getOrderDetailListByScOrderInfo(Map<String, Object> map) throws Exception;

	List<ScOrderDetail> findDetailByOid(String orderId);
	List<ScOrderDetailVo> findDetailByOrderIdOrOrderId2(Map<String, Object> map);

	/**
	 * 
	* @Title: getOrderDetailVoListByScOrderInfo 
	* @Description:通过ScOrderInfo查询ScOrderDetailVo集合 
	* @param @param map
	* @param @return
	* @return List<ScOrderDetailVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScOrderDetailVo> getOrderDetailVoListByScOrderInfo(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: updateOrderId2 
	* @Description:修改orderDetail orderId2
	* @param @param scOrderDetailVo
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void updateOrderId2(Map<String, Object> map) throws Exception;

	Map<String, Object> selectByPrimaryKey(String string) throws Exception;

	
	int deleteMyOrderinfoDetail(ScOrderInfoMgRo scOrderInfoMgRo);

	void deleteByScOrderInfo(ScOrderInfo scOrderInfo) throws Exception;

	/**
	 * 
	* @Title: getNewestPriceByScOrderInfo 
	* @Description:获取该笔订单中包含的商品的最新价格
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<ScOrderDetail>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScOrderDetail> getNewestPriceByScOrderInfo(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: updaeScOrderdetailPrice 
	* @Description:update ScOrderdetail 的totalPrice价格
	* @param @param scOrderDetail
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void updaeScOrderdetailPrice(ScOrderDetail scOrderDetail) throws Exception;

	ScOrderDetail selectByprimaryKey(String id);
	
	/**
	 * 修改商品状态
	* @Title
	* @Description: TODO 
	* @param @param map
	* @2016年3月2日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public void updateGoodsStatus(Map<String, Object> map);

	
	  /**
	   * 查出采购单下所有商品的状态
	  * @Title
	  * @Description: TODO 
	  * @param @param map
	  * @param @return
	  * @2016年3月2日     
	  * @author 龙五  longwu@izjjf.cn
	  * @return
	  * @throws
	   */
	  public int getAllGoodsStatus(Map<String, Object> map);
	  
	  /**
	   * 根据id获得商品的状态
	  * @Title
	  * @Description: TODO 
	  * @param @param map
	  * @param @return
	  * @2016年3月3日     
	  * @author 龙五  longwu@izjjf.cn
	  * @return
	  * @throws
	   */
	  public Byte getGoodsStatusById(Map<String, Object> map);
	  
	/**
	 * 
	* @Title: batchUpdatePrice 
	* @Description:批量修改ScOrderDetail 中的价格
	* @param @param list
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void batchUpdatePrice(List<ScOrderDetail> list) throws Exception;
	
	
	/**
	 * 
	* @Title: selectScOrderDetailCountByID
	* @Description: 通过订单号获取是否有未确认收货的订单
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	int updateScOrderInfoStatusByOrderId2(String orderId2);

	int getSupplierAllGoodsStatus(Map<String, Object> map);

	/**
	 * 
	* @Title: getIsLastAffrimOrder 
	* @Description:根据orderId2来判断是否是某个合单下最后一笔确认收货的订单
	* @param @param orderId2
	* @param @return
	* @param @throws Exception
	* @return List<ScOrderDetail>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScOrderDetail> getIsLastAffrimOrder(String orderId2) throws Exception;

	/**
	 * 
	* @Title: getScOrderDetailListByOrderId 
	* @Description:
	* @param @param scOrderInfoLevel1
	* @param @return
	* @param @throws Exception
	* @return List<ScOrderDetail>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScOrderDetail> getScOrderDetailListByOrderId(ScOrderInfo scOrderInfoLevel1) throws Exception;

	void batchUpdateMaOrderInfoId(List<ScOrderDetail> list) throws Exception;

	List<ScOrderDetail> findDetailByOid2(String orderId2);
}
