/**  
 * @Title: ScMgSupplierService.java
 * @Package com.corner.scms.service.sp
 * @Description: TODO
 * @author 杨开泰
 * @date 2015年12月3日
 */
package com.corner.scms.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.Supplier;
import com.corner.scms.service.BaseService;


/**
 * 
* @ClassName: SupplierMgService 
* @Description: 供应商业务实现类 
* @author 杨开泰  yangkaitai@izjjf.cn 
* @date 2015年12月4日 上午11:48:58
 */
public interface ScmsSupplierMgService extends BaseService{

	Supplier selectByPrimaryKey(String id);
    /**
     * 
    * @Title: getSupplierAndSpGroup 
    * @Description:查询供应商所属的定格
    * @param @param id
    * @param @return
    * @return List<SpGroup>
    * @author 杨开泰  yangkaitai@izjjf.cn
    * @throws
     */
	List<SpGroup> getSupplierAndSpGroup(String id);
	
	/**
	 * @throws Exception 
	 * 
	* @Title: updateSupplierWallet 
	* @Description:更新用户钱包余额
	* @param @param map
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void updateSupplierWallet(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	* @Title: updateScOrderInfoPayStatus 
	* @Description:更新钱包余额及使用信息,更新订单支付信息
	* @param @param map
	* @param @return
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> updateScOrderInfoPayStatus(Map<String, Object> map) throws Exception;
	
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
	int updateSupplier(Supplier supplier) throws Exception;
	/**
	 * 
	* @Title: addSupplierWallet 
	* @Description:给批发商钱包加钱
	* @param @param map
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void addSupplierWallet(Map<String, Object> map) throws Exception;

}
