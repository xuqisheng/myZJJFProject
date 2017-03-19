/**   
* @Title: ScmsShoppingCartService.java 
* @Package com.corner.scms.service.sc 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月19日 下午2:50:07 
* @version V1.0   
*/

package com.corner.scms.service.sc;

import java.util.Map;

import com.corner.core.beans.Supplier;
import com.corner.core.beans.SystemInfo;
import com.corner.scms.service.BaseService;

/** 
* @ClassName: ScmsShoppingCartService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月19日 下午2:50:07 
*  
*/

public interface ScmsShoppingCartService extends BaseService{

	/**
	 * 
	* @Title: getCartList 
	* @Description:获取批发商购物车列表
	* @param @param supplier
	* @param @return
	* @param @throws Exception
	* @return List<ScmsItemVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> getCartList(Map<String, Object> map) throws Exception;

	/**
	 * @return 
	 * 
	* @Title: checkCartListBySupplierId 
	* @Description:选中购物车中的商品
	* @param @param map key=supplier,登入的批发商对象;key=cartIdList,选中的购物车id集合;key=type,check表示选中
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> updateCartListBySupplierId(Map<String, Object> map) throws Exception;

	/**
	 * @return 
	 * 
	* @Title: addCartListBySupplierId 
	* @Description:增加或减少购物车商品数量
	* @param @param map
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> addCartListBySupplierId(Map<String, Object> map)throws Exception;

	/**
	 * @throws Exception 
	 * 
	* @Title: deleteShoppingCartBySupplierId 
	* @Description:删除购物车中的商品by登录的供应商id
	* @param @param map
	* @param @return
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> deleteShoppingCartBySupplierId(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getConfirmedCartList 
	* @Description:得到批发商要买的商品列表集合
	* @param @param supplier
	* @param @return
	* @return List<ScmsMinimumVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> getConfirmedCartList(Supplier supplier) throws Exception;


	/**
	 * 
	* @Title: addOrders 
	* @Description:提交订单
	* @param @param map
	* @param @return
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> addOrders(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: updateScOrderInfoToSettlement 
	* @Description:跳转到结算页面钱,再次联查订单中包含的商品价格
	* @param @param map
	* @param @return
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> updateScOrderInfoToSettlement(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getCheckedShoppingCartProductIsLegalBySupplier 
	* @Description:判断批发商购物车中选中的商品是否满足起购量
	* @param @param supplier
	* @param @return
	* @return Boolean    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Boolean getCheckedShoppingCartProductIsLegalBySupplier(Supplier supplier) throws Exception;

	/**
	 * 
	* @Title: updateScOrderInfoPayPriceAndGetNewestScOrderInfo 
	* @Description:更新订单最新单品价格和总价,并且返回最新总价的ScOrderInfo
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> updateScOrderInfoPayPriceAndGetNewestScOrderInfo(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getThirdFee 
	* @Description:获取第三方支付费率
	* @param @return
	* @param @throws Exception
	* @return SystemInfo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	SystemInfo getThirdFee() throws Exception;

}
