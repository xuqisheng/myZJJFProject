/**   
* @Title: ERPWarehouseServiceImpl.java 
* @Package com.corner.kefu.service.sp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月23日 下午5:24:38 
* @version V1.0   
*/

package com.corner.kefu.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPWarehouseMapper;
import com.corner.kefu.beans.ro.erp.ERPWarehouseRo;
import com.corner.kefu.beans.vo.erp.ERPWarehouseVo;
import com.corner.kefu.dao.ERPWarehouseMgMapper;
import com.corner.kefu.service.sp.ERPWarehouseService;

/** 
* @ClassName: ERPWarehouseServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月23日 下午5:24:38 
*  
*/
@Service
public class ERPWarehouseServiceImpl implements ERPWarehouseService {

	@Autowired
	ERPWarehouseMgMapper eRPWarehouseMgMapper;
	
	@Autowired
	ERPWarehouseMapper eRPWarehouseMapper;

	/**
	 * 
	* Title: getSupplierAllERPWarehouseById 
	* Description:根据批发商id查询批发商所有的仓库 
	* @param id
	* @return 
	* @see com.corner.kefu.service.sp.ERPWarehouseService#getSupplierAllERPWarehouseById(java.lang.String)
	 */
	@Override
	public List<ERPWarehouse> getSupplierAllERPWarehouseById(String id) {
		return eRPWarehouseMgMapper.getSupplierAllERPWarehouseById(id);
	}

	/**
	 * 
	* @Title: getSupplierAllERPWarehouseById 
	* @Description:根据批发商id获取
	* @param @param id
	* @param @return
	* @return List<ERPWarehouse>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public Pager<ERPWarehouseVo> getAllJsonErpWareHose(ERPWarehouseRo warehouseRo) {
		List<ERPWarehouseVo> list = eRPWarehouseMgMapper.getAllJsonErpWareHose(warehouseRo);
		Integer count = 0;
		if(list!=null&&list.size()!=0){
			count = eRPWarehouseMgMapper.getCountyAllJsonErpWareHose(warehouseRo);
		}
		return new Pager<>(count, list);
	}

	@Override
	public ERPWarehouse selectByPrimaryKey(String whId) {
		return eRPWarehouseMapper.selectByPrimaryKey(whId);
	}

}
