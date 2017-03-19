package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.Supplier;
import com.corner.core.beans.User;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.erp.ERPManagerRo;
import com.corner.kefu.beans.ro.sp.SupplierMgRo;
import com.corner.kefu.beans.ro.sp.SupplierRo;
import com.corner.kefu.beans.vo.RegionVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;

@Service
public interface SpSupplierService {

	/**
	 * 跟新供应商信息
	 * 
	 * @author Dick 2015年6月15日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplier
	 * @return
	 */
	public Boolean update(Supplier supplier);

	/**
	 * 添加供应商
	 * 
	 * @author Dick 2015年6月15日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplier
	 * @return
	 */
	public ModelMsg addSupplier(Supplier supplier);

	/**
	 * 根据条件获取供应商
	 * 
	 * @author Dick 2015年6月15日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplierRo
	 * @return
	 */
	public Pager<SupplierVo> getSuppliers(SupplierRo supplierRo);

	public Supplier selectSupplierBySupplierCodee(String supplierCode);

	/**
	 * 通过id 查询supplier
	 * TODO
	 * @param supplier
	 * @return
	 */
	public Supplier getSupplierById(String id);
	
	/**
	 * @param supplierMgRo 
	 * 
	* @Title: getSupplierProductList 
	* @Description:查询批发商在不同定格下的商品列表
	* @param @return
	* @param @throws Exception
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> getSupplierProductList(SupplierMgRo supplierMgRo) throws Exception;

	/**
	 * 
	* @Title: getSupplier 
	* @Description:获取所有批发商列表
	* @param @return
	* @param @throws Exception
	* @return List<Supplier>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<Supplier> getSupplier() throws Exception;

	/**
	 * 
	* @Title: getSupplierVoById 
	* @Description:
	* @param @param supplier
	* @param @return
	* @param @throws Exception
	* @return SupplierVo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public SupplierVo getSupplierVoById(Supplier supplier) throws Exception;

	public List<SupplierVo> selectSupplierList(SupplierVo supplierVo);

	public List<SupplierVo> getSupplierBySpGroupId(Integer[] intSpGroupIds);
	
	/**
	 * 
	* @Title: updateUserAndSupplier 
	* @Description:修改User和Supplier对应数据
	* @param @param user
	* @param @param supplier
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateUserAndSupplier(User user, Supplier supplier);

	/**
	 * 
	* @Title: getSupplierByMobile 
	* @Description:根据手机号查询批发商表
	* @param @param mobile
	* @param @return
	* @return Supplier    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Supplier getSupplierByMobile(String mobile);
	/**
	 * 
	 * @Title: getSupplierBySupplierVoList
	 * @date 2016年8月30日  上午11:45:27
	 * @author 小武
	 * @version  七彩虹
	 * @param list
	 * @return
	 */
	public List<SupplierVo> getSupplierBySupplierVoList(List<SupplierVo> list);

	/**
	 * 
	* @Title: getTreeSupplierList 
	* @Description:获取Supplier ztree批发商列表
	* @param @return
	* @return List<RegionVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<RegionVo> getTreeSupplierList();

	public List<SupplierVo> getMnagerSupplierList(ERPManagerRo manager);
}
