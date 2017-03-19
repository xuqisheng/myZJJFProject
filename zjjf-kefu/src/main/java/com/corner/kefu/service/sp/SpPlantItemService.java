/**   
* @Title: SpPlantItemService.java 
* @Package com.corner.kefu.service.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月24日 上午10:30:03 
* @version V1.0   
*/

package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.PlantItem;
import com.corner.core.beans.PlantItemPre;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.PlantItemRo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;
import com.corner.kefu.beans.vo.sp.SKUActiveSessionObject;

/** 
* @ClassName: SpPlantItemService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午10:30:03 
*  
*/

public interface SpPlantItemService {

	/**
	 * 
	* @Title: selectByPrimaryKey 
	* @Description:根据id查询PlantItem
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return PlantItem    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	PlantItem selectByPrimaryKey(String id) throws Exception;

	/**
	 * 
	* @Title: upPlantItem 
	* @Description:上下架PlantItem
	* @param @param plantItem
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void upPlantItem(PlantItem plantItem) throws Exception;

	/**
	 * 
	* @Title: addPrePlantItem 
	* @Description:添加商品到PlantItemPre表
	* @param @param map
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ModelMsg addPrePlantItem(Map<String, Object> map) throws Exception;

	
	/**
	 * 
	* @Title: selectPrePlantItem 
	* @Description:查询PlantItemPre
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> selectPrePlantItem(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: selectItemPc 
	* @Description:
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Map<String, Object> selectItemPc(Map<String, Object> map) throws Exception;

	List<PlantItem> getPlantItemListBySpIdAndSpGroupIdAndItemBaseId(Map<String, Object> map) throws Exception;

	void upPlantItemOderId(PlantItem plantItem) throws Exception;

	PlantItemPre getPlantItemPreById(String id) throws Exception;

	void upPlantItemPreOderId(PlantItemPre plantItempre) throws Exception;

	List<PlantItem> getPlantItemPreListByItemBaseIdAndSupplierId(Map<String, Object> map) throws Exception;

	void batchAddPlantItemPre(List<PlantItem> insertList) throws Exception;

	void batchUpdatePlantItemPre(List<PlantItem> updateList) throws Exception;

	List<PlantItemPre> getAllPlantItemPre();

	PlantItem getPlantItemById(String id);

	void addPlantItemList(List<PlantItemPre> insertList);

	int update(PlantItem plant);

	void removeAllPlantItemPre();
	
	
	public Pager<PlantItemVo> getSupplierAllStock(PlantItemRo plantItemRo);
	public List<SpGroup> getAllSpGroup();

	PlantItemVo getPlantItemByGroupAndSpId(PlantItemRo plantItemRo);

	Pager<PlantItemVo> getPlantItemGoodsByGroupAndSpId(PlantItemRo plantItemRo);

	int savePlantItem(PlantItem plantItem);

	List<PlantItem> selectPlantItemByCondition(Map<String, Object> map);

	
	/**
	 * 
	* @Title: getPlantItemPrice 
	* @Description:根据批发商id和商品条形码，查询PlantItem
	* 将该商品在批发商所在的所有定格下的价格显示出来
	* @param @param paramMap
	* @param @return
	* @param @throws Exception    设定文件 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	ModelMsg getPlantItemPrice(Map<String, Object> paramMap) throws Exception;

	ModelMsg getPlantItemPrePrice(Map<String, Object> paramMap) throws Exception;

	/**
	 * 
	* @Title: updateOrInserPlantItemPre 
	* @Description:往PlantItemPre表中更新或者插入数据
	* @param @param paramMap
	* @param @return
	* @param @throws Exception
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ModelMsg updateOrInserPlantItemPre(Map<String, Object> paramMap) throws Exception;

	/**
	 * 
	* @Title: updatePriceTask 
	* @Description:定时任务,将PlantItemPre表中的数据更新到PlantItemPre中
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void updatePriceTask() throws Exception;

	List<PlantItemVo> getPlantItemBySpGroupIdAndSupplierIdAndItemBaseId(Integer[] spGroupIds, String[] supplierIds,
			SKUActiveSessionObject sessionObject);

	void updatePlantItemRestrict(String id);
	

}
