/**   
* @Title: ScmsSupplierMgMapper.java 
* @Package com.corner.scms.dao 
* @Description:  
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月11日 下午4:40:20 
* @version V1.0   
*/

package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.Supplier;
import com.corner.scms.beans.ro.sc.ScmsManagerMgRo;
import com.corner.scms.beans.vo.SupplierVo;

/** 
 * @ClassName: ScmsSupplierMgMapper 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn
 * @date 2015年12月11日 下午4:40:20 
 *  
 */

public interface ScmsSupplierMgMapper {
	/**
     * 
    * @Title: getSupplierAndSpGroup 
    * @Description:查询供应商所属的定格
    * @param @param id 供应商id
    * @param @return
    * @return List<SpGroup>
    * @author 杨开泰  yangkaitai@izjjf.cn
    * @throws
     */
	List<SpGroup> getSupplierAndSpGroup(String spId);

    /**
     * 
    * @Title: getSupplierVo 
    * @Description:查询批发商视图类
    * @param @param supplier
    * @param @return
    * @return SupplierVo    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	SupplierVo getSupplierVo(Supplier supplier);

	
	/**
	 * 
	* @Title: updateSupplierWallet 
	* @Description:改变用户钱包的余额
	* @param @param map 有3个key supplier--对应的批发商对象 operate--- +表示钱包余额增加 -表示钱包余额减少 operateMoney--表示要增加或减少的金额
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void updateSupplierWallet(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: updateSupplierPayPassword 
	* @Description:修改批发商支付密码信息
	* @param @param supplier
	* @param @return
	* @param @throws Exception
	* @return int    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	int updateSupplierPayPassword(Supplier supplier) throws Exception;

	/**
	 * 
	* @Title: getSupplierById 
	* @Description:根据批发商id获取批发商信息
	* @param @param supplierId
	* @param @return
	* @param @throws Exception
	* @return Supplier    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Supplier getSupplierById(String supplierId) throws Exception; 
}
