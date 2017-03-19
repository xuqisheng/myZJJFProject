package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.PlantItem;
import com.corner.core.beans.ScmsSpSalePrice;
import com.corner.scms.beans.ro.PlantItemRo;
import com.corner.scms.beans.ro.StockManagerParamRo;
import com.corner.scms.beans.vo.PlantItemVo;

public interface ScmsPlantItemMgMapper {
	/**
	 *  根据条件查询所有的库存信息
	* @Title: getAllPlantItemByParam 
	* @Description:
	* @param stockManagerParam
	* @return List<PlantItemVo>  
	* @author 龙五 at 2015年12月9日上午10:10:21
	* @email longwu@izjjf.cn
	 */
	public List<PlantItemVo> getAllPlantItemByParam(StockManagerParamRo stockManagerParam);
	/**
	 * 获取所有记录数
	* @Title: getPlantItemCount 
	* @Description: 
	* @param stockManagerParam 
	* @return int  
	* @author 龙五 at 2015年12月9日上午10:12:35
	* @email longwu@izjjf.cn
	 */
	public int getPlantItemCount(StockManagerParamRo stockManagerParam);
	/**
	 * 根据批发商id查询旗下的商品名称
	* @Title: getCommodityNameBySpId 
	* @Description: 
	* @param stockManagerParam
	* @return List<PlantItemVo>  
	* @author 龙五 at 2015年12月9日上午10:13:12
	* @email longwu@izjjf.cn
	 */
	public List<PlantItemVo> getCommodityNameBySpId(StockManagerParamRo stockManagerParam);
	/**
	 * 修正库存数量
	* @Title: updatePlantItem 
	* @Description: 
	* @param stockManagerParam   
	* @return void  
	* @author 龙五 at 2015年12月9日上午10:13:49
	* @email longwu@izjjf.cn
	 */
	public int updatePlantItem(StockManagerParamRo stockManagerParam);
	/**
	 * 修正库存上下限
	* @Title: updatePlantItemUpperLower 
	* @Description: 
	* @param @param stockManagerParam   
	* @return void  
	* @author 龙五 at 2015年12月9日上午10:14:18
	* @email longwu@izjjf.cn
	 */
	public void updatePlantItemUpperLower(StockManagerParamRo stockManagerParam);
	/**
	 * 根据商品名称查出商品名称和itembaseid
	* @Title: getAllCommodityNameAndItemBaseIdByName 
	* @Description: 
	* @param @param commodityName
	* @param @return   
	* @return List<PlantItemVo>  
	* @author 龙五 at 2015年12月9日上午11:43:08
	* @email longwu@izjjf.cn
	 */
	public List<PlantItemVo> getAllCommodityNameAndItemBaseIdByName(Map<String, Object> param);
	/**
	 * 根据商品编号和批发商id查出商品库存
	* @Title: getGoodsStockByCommodityNoAndspid 
	* @Description: 
	* @param @param commodityName
	* @param @return   
	* @return List<PlantItemVo>  
	* @author 龙五 at 2015年12月9日上午11:43:08
	* @email longwu@izjjf.cn
	 */
	public List<PlantItemVo> getGoodsStockByitemBaseIdAndspId(Map<String, Object> param);
	/**
	 * 
	* @Title: selectSupplierAllPlantItem 
	* @Description:查询供应商的所有商品
	* @param @param supplier 必须要有id
	* @param @return
	* @return List<PlantItemVo>
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<PlantItemVo> selectSupplierAllPlantItem(PlantItemRo plantItemRo);
	
	/**
	 * 
	* @Title: selectItemBaseByName 
	* @Description:根据商品名搜索ItemBase表
	* @param @param productName 商品名
	* @param @return
	* @return List<PlantItemVo>
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<PlantItemVo> selectItemBaseByName(String productName);
	
	/**
	 * 
	* @Title: selectPlantItemByCondition 
	* @Description:查询商品
	* @param @param plantItemRo
	* @param @return
	* @return List<PlantItemVo>
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<PlantItemVo> selectPlantItemByCondition(PlantItemRo plantItemRo);
	
	/**
	 * 
	* @Title: selectCountSupplierAllPlantItem 
	* @Description:
	* @param @param plantItemRo
	* @param @return
	* @return Integer
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Integer selectCountSupplierAllPlantItem(PlantItemRo plantItemRo);

	/**
	 * 
	* @Title: updatePlantItemBySpIdAndBaseId 
	* @Description: 修改库存，必传要输      spId ，itemBaseId ，  goodsStock 库存(传人减少值即可)
	* @param @param plantItem
	* @param @return    设定文件
	* @return int    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	public int updatePlantItemBySpIdAndBaseId(PlantItem plantItem);
	/**
	 * 
	 * @Title: selectPlantItemBySpIdAndBaseId 
	 * @Description:  根据商品商品ID，批发商ID 查出库存信息 
	 * @param @param plantItem
	 * @param @return    设定文件
	 * @return int    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	public List<PlantItem> selectPlantItemBySpIdAndBaseId(PlantItem plantItem);
	
	/**
	 * 
	* @Title: selectSpPriceList 
	* @Description:查询供应商对不同类型的店铺销售商品的价格
	* @param @param plantItemRo
	* @param @return
	* @return List<ScmsSpSalePrice>
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<ScmsSpSalePrice> selectSpPriceList(PlantItemRo plantItemRo);
	
	 /**
     * 
    * @Title: selectPlantItemAndUserTypePrice 
    * @Description:
    * @param @param plantItemRo
    * @param @return
    * @return List<PlantItemVo>
    * @author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	public PlantItemVo selectPlantItemAndUserTypePrice(PlantItemRo plantItemRo);
	
	/**
	 * 
	* @Title: selectSalePrice 
	* @Description: 根据批发商ID，商品ID，客户类型获取出货价
	* @param @param map
	* @param @return    设定文件
	* @return ScmsSpSalePrice    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	public ScmsSpSalePrice selectSalePrice(Map<String, Object> map);
	
	/**
	 * 
	* @Title: updatePlantItemBySpIdAndItemBaseId 
	* @Description: 出库专用
	* @param @param stockManagerParam
	* @param @return    设定文件
	* @return int    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	public int updatePlantItemBySpIdAndItemBaseId(Map<String, Object> map);
	/**
	 * 
	 * @Title: updatePlantItemStock 
	 * @Description: 线下打单库存处理
	 * @param @param stockManagerParam
	 * @param @return    设定文件
	 * @return int    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	public int updatePlantItemStock(Map<String, Object> map);
	/**
	 * 
	* @Title: getAllItemBaseIdPrice 
	* @Description:
	* @param @param map
	* @param @return
	* @return List<PlantItemVo>
	* @author 杨开泰  yangkaitai@izjjf.cn
	* @throws
	 */
	public List<PlantItemVo> getAllItemBaseIdPrice(Map<String, Object> map);
	
	public PlantItem findItemBySidAndItemid(Map<String, Object> map);
	public Integer findId(String itemId);
	
	//*********************************************************************
	 /**
	  * 根据参数获得所有批发商的商品 
	 * @Title
	 * @Description: TODO 
	 * @param @param plantItem
	 * @param @return
	 * @2016年3月7日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	  */
	 public List<PlantItemVo> getSupplierGoodsByParam(PlantItemVo plantItem);
 	/**
 	 * 获取总数
 	* @Title
 	* @Description: TODO 
 	* @param @param plantItem
 	* @param @return
 	* @2016年3月7日     
 	* @author 龙五  longwu@izjjf.cn
 	* @return
 	* @throws
 	 */
	 public int getSupplierGoodsByParamCount(PlantItemVo plantItem);
	 
	 
	 public void updatePlantItemStatus(Map<String, Object> map);
	//**********************************************************************
	public List<PlantItemVo> getPlantItemGoodsByGroupAndSpId(StockManagerParamRo plantItemRo);
	public int getPlantItemGoodsByGroupAndSpIdCount(StockManagerParamRo plantItemRo);
}
