package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.PlantItem;
import com.corner.core.beans.PlantItemPre;
import com.corner.core.beans.SpGroup;
import com.corner.kefu.beans.ro.sp.PlantItemRo;
import com.corner.kefu.beans.ro.sp.SupplierMgRo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;

/**
 * 
* @ClassName: SpPlantItemMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午10:31:09 
*
 */
public interface SpPlantItemMgMapper {

	/**
	 * 
	* @Title: getPlangItemBySupplier 
	* @Description:获取批发商在对应定格下的商品列表
	* @param @param supplierMgRo
	* @param @return
	* @param @throws Exception
	* @return List<PlantItemVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<PlantItemVo> getPlangItemBySupplier(SupplierMgRo supplierMgRo) throws Exception;

	
	/**
	 * 
	* @Title: getCountPlangItemBySupplier 
	* @Description:获取批发商在对应定格下的商品列表总量
	* @param @param supplierMgRo
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountPlangItemBySupplier(SupplierMgRo supplierMgRo) throws Exception;

	
    /**
     * 
    * @Title: selectPrePlantItembyitemBaseIdAndSupplierId 
    * @Description:根据ItemBase id 和批发商id查询PlantItemPre
    * @param @param map
    * @param @return
    * @param @throws Exception
    * @return List<PlantItem>    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	List<PlantItem> selectPrePlantItembyitemBaseIdAndSupplierId(Map<String, Object> map) throws Exception;

    /**
     * 
    * @Title: selectPlantItembyitemBaseIdAndSupplierId 
    * @Description:根据ItemBase id 和批发商id查询PlantItemPre
    * @param @param map
    * @param @return
    * @param @throws Exception
    * @return List<PlantItem>    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	List<PlantItem> selectPlantItembyitemBaseIdAndSupplierId(Map<String, Object> map) throws Exception;


	/**
	 * 
	* @Title: insertIntoPlantItemPre 
	* @Description:
	* @param @param plantItem
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void insertIntoPlantItemPre(PlantItem plantItem) throws Exception;


	List<PlantItem> selecPlantItembyitemBaseIdAndSupplierId(Map<String, Object> map) throws Exception;


	List<PlantItem> getPlantItemListBySpIdAndSpGroupIdAndItemBaseId(Map<String, Object> map) throws Exception;


	void upPlantItemOderId(PlantItem plantItem) throws Exception;


	void upPlantItemPreOderId(PlantItemPre plantItempre) throws Exception;


	List<PlantItem> getPlantItemPreListByItemBaseIdAndSupplierId(Map<String, Object> map) throws Exception;


	void batchAddPlantItemPre(List<PlantItem> insertList) throws Exception;


	void insertPlantItemPreSelective(PlantItem plantItem) throws Exception;


	void batchUpdatePlantItemPre(List<PlantItem> updateList) throws Exception;


	List<PlantItemPre> getAllPlantItemPre();


	void addPlantItemList(List<PlantItemPre> insertList);


	void insertPlantItemPreSelective2(PlantItemPre plantItemPre);


	void update(PlantItem plant);


	void insertLog(PlantItem plant);


	void removeAllPlantItemPre();
	
	public List<PlantItemVo> getSupplierAllStock(PlantItemRo plantItemRo);
	public int getSupplierAllStockCount(PlantItemRo plantItemRo);
	public List<SpGroup> getAllSpGroup();

	public PlantItemVo getPlantItemByGroupAndSpId(PlantItemRo plantItemRo);


	public List<PlantItemVo> getPlantItemGoodsByGroupAndSpId(PlantItemRo plantItemRo);
	public int getPlantItemGoodsByGroupAndSpIdCount(PlantItemRo plantItemRo);


	List<PlantItem> selectPlantItemByCondition(Map<String, Object> map);


	void insertIgnorePlantItemPre(List<PlantItem> currentList) throws Exception;

	/**
	 * 
	* @Title: getPlantItemPrice 
	* @Description:根据批发商id和ItemBase id，查询PlantItem
	* 将该商品在批发商所在的所有定格下的价格显示出来
	* @param @param paramMap
	* @param @return
	* @param @throws Exception    设定文件 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	List<PlantItemVo> getPlantItemVoList(Map<String, Object> paramMap) throws Exception;

	/**
	 * 
	* @Title: getPlantItemPrice 
	* @Description:根据批发商id和ItemBase id，查询PlantItemPre
	* 将该商品在批发商所在的所有定格下的价格显示出来
	* @param @param paramMap
	* @param @return
	* @param @throws Exception    设定文件 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	List<PlantItemVo> getPlantItemPreVoList(Map<String, Object> paramMap) throws Exception;


	/**
	 * 
	* @Title: updatePriceTask 
	* @Description:定时任务,将PlantItemPre表中的数据更新到PlantItemPre中
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void insertOrUpdatePlantItem(List<PlantItemPre> list) throws Exception;


    /**
     * 
    * @Title: batchAddPlantItemLog 
    * @Description:将更新的价格数据存入到PlantItemLog表中
    * @param @param list
    * @return void    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	void batchAddPlantItemLog(List<PlantItemPre> list);


	int updateAllGoodsStock(Integer stockNum);

    /**
     * 
    * @Title: deleteByIds 
    * @Description:根据PlantItemVo 集合删除PlantItemPre 表中的数据
    * @param @param resultList
    * @param @throws Exception
    * @return void    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	void deleteByIds(List<PlantItemVo> resultList) throws Exception;

	/**
	 * 
	* @Title: batchAddByPlantItemoVoList 
	* @Description:根据PlantItemVo 往PlantItemPre表中插入数据
	* @param @param resultList
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
 	void batchAddByPlantItemoVoList(List<PlantItemVo> resultList) throws Exception;

    /**
     * 
    * @Title: deleteByPlantItemVoId 
    * @Description:根据PlantItemVo id 删除 PlantItemPre表中的数据
    * @param @param plantItemVo
    * @param @throws Exception
    * @return void    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	void deleteByPlantItemVoId(PlantItemVo plantItemVo) throws Exception;

    /**
     * 
    * @Title: insertPlantItemPreWithPlantItemVo 
    * @Description:
    * @param @param plantItemVo
    * @param @throws Exception
    * @return void    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	void insertPlantItemPreWithPlantItemVo(PlantItemVo plantItemVo) throws Exception;

    /**
     * 
    * @Title: getPlantItemByPlantItemPreId 
    * @Description:根据PlantItemPre的id查询PlantItem
    * @param @param plantItemPre
    * @param @return
    * @param @throws Exception
    * @return PlantItem    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	PlantItem getPlantItemByPlantItemPreId(PlantItemPre plantItemPre) throws Exception;

    /**
     * 
    * @Title: deletePlantItemPreById 
    * @Description:根据id删除PlantItemPre表中的数据
    * @param @param plantItemPre
    * @param @throws Exception
    * @return void    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	void deletePlantItemPreById(PlantItemPre plantItemPre) throws Exception;

    /**
     * 
    * @Title: preInsert 
    * @Description:用PlantItemPre中的数据在PlantItem表中插入一条数据,但是status一定为0
    * @param @param plantItemPre
    * @param @throws Exception
    * @return void    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	void preInsert(PlantItemPre plantItemPre) throws Exception;

    /**
     * 
    * @Title: getMinGoodsStockByItemBaseIdAndSpId 
    * @Description:根据itemBaseId和spId 查询goodsStock最小的那条数据
    * @param @param plantItemPre
    * @param @return
    * @return PlantItem    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	PlantItem getMinGoodsStockByItemBaseIdAndSpId(PlantItemPre plantItemPre);


	String checkGoods(Map<String, Object> map);


	PlantItemVo getPlantItemById(String id);

    /**
     * 
    * @Title: getPlantItemFromPlantItemPreWithCondition 
    * @Description:根据ItemBaseId spId spGroupId查询PlantItemPre
    * @param @param plantItemPre
    * @param @return
    * @return PlantItem    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	List<PlantItem> getPlantItemFromPlantItemPreWithCondition(PlantItemPre plantItemPre);

    /**
     * 
    * @Title: getPlantItemListWithSpIdAndSpGroupIdAndItemBaseId 
    * @Description:根据spGroupId spId ItemBaseId 查询PlantItem
    * @param @param plantItemPre
    * @param @return
    * @return List<PlantItem>    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	List<PlantItem> getPlantItemListWithSpIdAndSpGroupIdAndItemBaseId(PlantItemPre plantItemPre);

    /**
     * 
    * @Title: deleteFromPlantItemPreWithSpIdAndSpGroupIdAndItemBaseId 
    * @Description:根据spId spGroupId ItemBaseId 从PlantItemPre表中删除
    * @param @param plantItemPre
    * @return void    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	void deleteFromPlantItemPreWithSpIdAndSpGroupIdAndItemBaseId(PlantItemPre plantItemPre);


	void updatePlantItemRestrict(String id);


	void updatePlantItemPreRestrict(String id);


	void removeBuyLimit(String id);

}
