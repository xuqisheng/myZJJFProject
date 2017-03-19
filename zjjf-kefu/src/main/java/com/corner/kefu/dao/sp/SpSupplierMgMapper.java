/**   
* @Title: SpSupplierMgMapper.java 
* @Package com.corner.kefu.dao.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月24日 上午10:08:27 
* @version V1.0   
*/

package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Supplier;
import com.corner.core.utils.callable.SocktOperateLog;
import com.corner.kefu.beans.ro.erp.ERPManagerRo;
import com.corner.kefu.beans.ro.sp.SupplierRo;
import com.corner.kefu.beans.vo.RegionVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;

/** 
* @ClassName: SpSupplierMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午10:08:27 
*  
*/

public interface SpSupplierMgMapper {

	/**
	 * 根据条件获取供应商
	 * @author Dick 2015年6月15日
	 * @Email  823882651@qq.com
	 * @Desc
	 * @param supplierRo
	 * @return
	 */
	List<SupplierVo> selectSupplierSelective(SupplierRo supplierRo);
	Integer selectSupplierSelectiveCount(SupplierRo supplierro);
	Supplier selectSupplierBySupplierCode(String supplierCode);
	
	/**
	 * 
	* @Title: getRegionSupplierList 
	* @Description:获取某个区域下 未被删除,审核通过的批发商列表
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return List<Supplier>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<Supplier> getRegionSupplierList(Integer id) throws Exception;
	
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
	List<Supplier> getSupplier() throws Exception;
	
	SupplierVo getSupplierVoById(Supplier supplier) throws Exception;
	
	List<SupplierVo> selectSupplierList(SupplierVo supplierVo);
	Integer getMaxSequenceNum(Supplier supplier);
	List<SupplierVo> getSupplierBySpGroupId(Integer[] spGroupIds);
	
	Supplier getSupplierByMobile(String mobile);
	
	/**
	 * 查询已经存在的最新数据
	 * @Title: getSupplierByMap
	 * @date 2016年8月30日  上午11:41:35
	 * @author 小武
	 * @version  七彩虹
	 * @param param
	 * @return
	 */
	List<SupplierVo> getSupplierByMap(Map<String,Object> param);
	/**
	 * 
	* @Title: getTreeSupplierList 
	* @Description:获取Supplier ztree批发商列表
	* @param @return
	* @return List<RegionVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<RegionVo> getTreeSupplierList();
	
	List<SupplierVo> getMnagerSupplierList(ERPManagerRo manager);
}
