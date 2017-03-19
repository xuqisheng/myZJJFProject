package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.Supplier;
import com.corner.kefu.beans.ro.scms.ScmsDistributionAreaRo;
import com.corner.kefu.beans.vo.ScmsGroupVo;
public interface ScmsGroupMgMapper {
	
		public List<ScmsGroupVo> getPageList(ScmsDistributionAreaRo condition);
		public int getPageListSize(ScmsDistributionAreaRo condition);
		
		/**
		 * 
		* @Title: findScmsGroupByUpId 
		* @Description: 根据UpId获取对应区域信息 
		* @param @param upId
		* @param @return    设定文件 
		* @return List<ScmsGroup>    返回类型 
		* @author 孟星魂	mengxinghun@izjjf.cn
		* @throws
		 */
		public List<ScmsGroup> findScmsGroupByUpId(String upId);
		public List<ScmsGroup> findScmsGroupBy0();
		
		public List<ScmsGroup> getScmsGroupname(String name);
		
		/**
		 * 
		* @Title: findScmsGroupByManagerId 
		* @Description: 通过经销商ID获取对应区域信息 
		* @param @param managerId
		* @param @return    设定文件
		* @return List<ScmsGroup>    返回类型
		* @author 孟星魂	mengxinghun@izjjf.cn
		* @throws
		 */
		List<ScmsGroup> findScmsGroupByManagerId(String managerId);
		
		
		public int deletecategory(Integer id);
		
		public void updateAreaByUid(Integer id);
		
		public List<ScmsGroup> findBywarehouseId(String warehouseId);


		/**
		 * 
		* @Title: getGorupItemlistPage 
		* @Description: 联合采购商品信息分页 
		* @param @param condition
		* @param @return    设定文件 
		* @return List<Map<String,Object>>    返回类型 
		* @author 孟星魂	mengxinghun@izjjf.cn
		* @throws
		 */
		List<Map<String, Object>> selectGorupItemListPage(Map<String, Object> condition);
		/**
		 * 
		* @Title: getGorupItemlistPage 
		* @Description:  联合采购商品信息  总页数
		* @param @param condition
		* @param @return    设定文件 
		* @return List<Map<String,Object>>    返回类型 
		* @author 孟星魂	mengxinghun@izjjf.cn
		* @throws
		 */
		int selectGorupItemListPageSize(Map<String, Object> condition);
		
		public List<ScmsItem> findItemByAreaid(Integer id);
		
		public List<Supplier> findSupplierByAreaId(Integer id);
		
		public int findOrders(String warehouseId);

}
