/**   
* @Title: ScmsShoppingCartMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月19日 下午3:01:54 
* @version V1.0   
*/

package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScmsShoppingCart;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.SystemInfo;
import com.corner.scms.beans.vo.ScmsMinimumVo;
import com.corner.scms.beans.vo.ScmsShoppingCartVo;

/** 
* @ClassName: ScmsShoppingCartMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月19日 下午3:01:54 
*  
*/

public interface ScmsShoppingCartMgMapper {

	/**
	 * 
	* @Title: getCartList 
	* @Description:获取批发商购物车列表
	* @param @param supplier
	* @param @return
	* @param @throws Exception
	* @return List<ScmsMinimumVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScmsMinimumVo> getCartList(Supplier supplier) throws Exception;
	/**
	 * 
	* @Title: save 
	* @Description: 保存修改订单 
	* @param: @param scmsShoppingCart
	* @param: @return
	* @param: @throws Exception	设定文件 
	* @return Integer    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	Integer save(ScmsShoppingCart scmsShoppingCart) throws Exception;

	/**
	 * 
	* @Title: checkCartListBySupplierId 
	* @Description:选中购物车中的商品
	* @param @param map map key=supplier,登入的批发商对象;key=cartIdList,选中的购物车id集合;key=type,check表示选中
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void checkCartListBySupplierId(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getCheckedCartListBySupplierId 
	* @Description:获取选中的购物车商品集合
	* @param @param supplier 登入的批发商对象
	* @param @return
	* @return List<ScmsShoppingCart>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScmsShoppingCart> getCheckedCartListBySupplierId(Supplier supplier) throws Exception;

	/**
	 * 
	* @Title: deleteShoppingCartBySupplierId 
	* @Description: 删除购物车中的商品
	* @param @param map
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void deleteShoppingCartBySupplierId(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getConfirmedCartList 
	* @Description:查询批发商想要买的购物车列表集合
	* @param @param supplier
	* @param @return
	* @param @throws Exception
	* @return List<ScmsMinimumVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScmsMinimumVo> getConfirmedCartList(Supplier supplier) throws Exception;

	/**
	 * 
	* @Title: getCheckedScmsShoppingCartVoListBySupplierId 
	* @Description:
	* @param @param supplier
	* @param @return
	* @return List<ScmsShoppingCartVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScmsShoppingCartVo> getCheckedScmsShoppingCartVoListBySupplierId(Supplier supplier) throws Exception;

	/**
	 * 
	* @Title: deleteBuyedShoppingCartBySullieper 
	* @Description:删除批发商已经购买的商品
	* @param @param supplier
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void deleteBuyedShoppingCartBySullieper(Supplier supplier) throws Exception;

	/**
	 * 
	* @Title: selectShoppingCartById 
	* @Description:查询购物车对象
	* @param @param string
	* @param @return
	* @return ScmsShoppingCart    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ScmsShoppingCart selectShoppingCartById(String string);

	/**
	 * 
	* @Title: getScmsMinimumVoList 
	* @Description:根据购物车id,查询相关联的起购量数据
	* @param @param string
	* @param @return
	* @return List<ScmsMinimumVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ScmsMinimumVo> getScmsMinimumVoList(String cartId);

	/**
	 * 
	* @Title: getThirdFee 
	* @Description:获取第三方支付费率
	* @param @return
	* @return SystemInfo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	SystemInfo getThirdFee() throws Exception;

}
