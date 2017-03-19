	package com.corner.kefu.service.sp;

	import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.Store;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.SpGroupMgRo;
import com.corner.kefu.beans.ro.sp.SpGroupRo;
import com.corner.kefu.beans.ro.sp.SupplierMgRo;
import com.corner.kefu.beans.vo.RegionVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;
import com.corner.kefu.beans.vo.sp.StoreMgVo;


	/**
	 * 
	 * @ClassName: SpGroupService
	 * 
	 * @Description: TODO
	 * 
	 * @author: 杨开泰
	 * 
	 * @date: 2015年10月10日 上午10:59:38
	 */

	public interface SpGroupService {
		/**
		 * 查询所有定格
		 * @return
		 * @throws Exception 
		 */
		List<SpGroup> selectSpGroupALL();
		/**
		 * 查询所有定格
		 * @return
		 * @throws Exception 
		 */
		public List<SpGroup> selectSpGroupList() throws Exception ;
		/**
		 * 
		* @Title: selectByPrimaryKey 
		* @Description:根据id获取定格
		* @param @param spGroupId
		* @param @return
		* @param @throws Exception
		* @return SpGroup    返回类型
		*@author 杨开泰 yangkaitai@izjjf.cn
		* @throws
		 */
		SpGroup selectByPrimaryKey(Integer spGroupId) throws Exception;

		/**
		 * 
		* @Title: getSpGroupListBySupplierId 
		* @Description:查询供应商所在的定格
		* @param @param supplier
		* @param @return
		* @param @throws Exception
		* @return List<SpGroup>    返回类型
		*@author 杨开泰 yangkaitai@izjjf.cn
		* @throws
		 */
		List<SpGroup> getSpGroupListBySupplierId(SupplierMgRo supplierMgRo) throws Exception;
		/**
		 * 查询批发商分组,关联查询下属商铺和批发商
		 * 
		 * @param map
		 * @return
		 * @throws Exception
		 */
		public Pager<SpGroupVo> getSpGropuWithList(SpGroupMgRo spGroupMgRo) throws Exception;
	    
		/**
		 * 
		* @Title: getSpGroupAndSupplierList 
		* @Description:查询定格下有哪些批发商 
		* @param @param spGroupRo
		* @param @return    设定文件 
		* @return SpGroupVo    返回类型 
		* @author 杨开泰   yangkaitai@izjjf.cn
		* @throws
		 */
		public Pager<Supplier> getSpGroupAndSupplierList(SpGroupRo spGroupRo);
		
		/**
		 * 根据id查询定格
		 * 
		 * @param spGroup
		 * @return
		 * @throws Exception
		 */
		public SpGroup getSpGroupById(Integer id);
		
		/**
		 * 修改定格
		 * 
		 * @param spGroup
		 * @throws Exception
		 */
		public ModelMsg updateSpGroup(SpGroup spGroup);
		
		/**
		 * 判断是否已经存在定格
		 * @param map
		 * @return
		 */
		public List<SpGroup> isExist(Map<String, Object> map);
		
		
		/**
		 * 
		* @Title: getSpGroupAndStoreList 
		* @Description:查询定格下关联的店铺集合 
		* @param @param spGroupRo
		* @param @return    设定文件 
		* @return Pager<Store>    返回类型 
		* @author 杨开泰   yangkaitai@izjjf.cn
		* @throws
		 */
		public Pager<Store> getSpGroupAndStoreList(SpGroupRo spGroupRo);
		
		/**
		 * 定格中批量添加批发商
		 * @param map
		 * @throws Exception 
		 */
		public void batchAddSupplier(Map<String, Object> map);
		
		/**
		 * 定格中添加批发商,将商品表中,该spId 和 spGroupId的商品重新上架
		 * @param map
		 */
		public void addSupplier(Map<String, Object> map);
		
		/**
		 * 批量删除定格中的供应商
		 * @param map
		 * @throws Exception 
		 */
		public void batchRemoveSupplier(Map<String, Object> map);
		
		/**
		 * 移除某个定格下的批发商,将改批发商在改定格下的商品的将删除
		 * @param map
		 * @throws Exception 
		 */
		public void removeSupplier(Map<String, Object> map);
		
		
		List<SpGroup> getSpGroupListBySupplierId(Supplier supplier) throws Exception;
		List<SpGroupVo> getSelectedSpGroupList(Map<String, Object> map);
		Integer getMatchConditionSpGroupCount(Map<String, Object> map);
		List<SpGroupVo> getSelectedSpGroupListFromSpPushMsgMap(Map<String, Object> map);
		List<SpGroup> getAllSpGroup(Map<String, Object> map);
		
		/**
		 * 
		* @Title: getAcTiveSpGroupList 
		* @Description:获取活动管理定格列表
		* @param @param regionVo
		* @param @return
		* @param @throws Exception
		* @return List<RegionVo>    返回类型
		*@author 杨开泰 yangkaitai@izjjf.cn
		* @throws
		 */
		List<RegionVo> getAcTiveSpGroupList(RegionVo regionVo) throws Exception;
		
		/**
		 * 
		* @Title: getSpGroupListByIds 
		* @Description:根据定格id数组查询定格相关信息
		* @param @param spGroupIdArr
		* @param @return
		* @param @throws Exception
		* @return List<SpGroupVo>    返回类型
		*@author 杨开泰 yangkaitai@izjjf.cn
		* @throws
		 */
		List<SpGroupVo> getSpGroupListByIds(String[] spGroupIdArr) throws Exception;
		
		Pager<SpGroupVo> getVoucherSpGroupList(Map<String, Object> map) throws Exception;
		
		/**
		 * 
		* @Title: insertSelective 
		* @Description:新建定格
		* @param @param spGroup
		* @param @throws Exception
		* @return void    返回类型
		*@author 杨开泰 yangkaitai@izjjf.cn
		* @throws
		 */
		void insertSelective(SpGroup spGroup) throws Exception;
		
		/**
		 * 
		* @Title: getCountSpGroupAndSupplierList 
		* @Description:查询定格下批发商总数 
		* @param @param spGroupRo
		* @param @return    设定文件 
		* @return Integer    返回类型 
		* @author 杨开泰   yangkaitai@izjjf.cn
		* @throws
		 */
		Integer getCountSpGroupAndSupplierList(SpGroupRo spGroupRo);
		/**
		 * 
		* @Title: getSpGoupVoById 
		* @Description:根据id获得spGroupVo对象
		* @param @param spGroupRo
		* @param @return
		* @param @throws Exception    设定文件 
		* @return SpGroupVo    返回类型 
		* @author 杨开泰   yangkaitai@izjjf.cn
		* @throws
		 */
		SpGroupVo getSpGoupVoById(SpGroupRo spGroupRo) throws Exception;
		
		/**
		 * 
		* @Title: removeBatchStore 
		* @Description:批量删除定格下的店铺,并且清空购物车 
		* @param @param map
		* @param @throws Exception    设定文件 
		* @return void    返回类型 
		* @author 杨开泰   yangkaitai@izjjf.cn
		* @throws
		 */
		ModelMsg removeBatchStore(Map<String, Object> map) throws Exception;
		
		List<StoreMgVo> getActiveStoreMember(Map<String, Object> map) throws Exception;
		List<SpGroup> getAllSpGroup1();
		List<Supplier> getSupplierIdByGroupId(Integer groupId);
		/**
		 * 
		* @Title: batchAddStore 
		* @Description:批量新增店铺
		* @param @param map
		* @param @throws Exception
		* @return void    返回类型
		*@author 杨开泰 yangkaitai@izjjf.cn
		* @throws
		 */
		ModelMsg batchAddStore(Map<String, Object> map) throws Exception;
		
		/**
		 * 根据区域id获取对应定格
		* @Title
		* @Description: TODO 
		* @param @param map
		* @param @return
		* @2016年4月26日     
		* @author 龙五  longwu@izjjf.cn
		* @return
		* @throws
		 */
		public List<SpGroup> getSpGroupByAreaId(Map<String, Object> map);
		
		/**
		 * 
		* @Title: getManjianActiveStore 
		* @Description:获取参与满减活动的店铺信息以及分组信息 
		* @param @param map
		* @param @return
		* @param @throws Exception    设定文件 
		* @return List<StoreMgVo>    返回类型 
		* @author 杨开泰   yangkaitai@izjjf.cn
		* @throws
		 */
		List<StoreMgVo> getManjianActiveStore(Map<String, Object> map) throws Exception;
		List<SpGroup> getSpGroupBySpGroupName(Map<String, Object> map);
	}
