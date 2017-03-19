package com.corner.scms.service.sp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScmsSpSalePrice;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.PlantItemRo;
import com.corner.scms.beans.ro.StockManagerParamRo;
import com.corner.scms.beans.vo.PlantItemVo;
@Service
public interface ScmsPlantItemMgService {
	/**
	 *  根据条件查询所有的库存信息
	* @Title: getAllPlantItemByParam 
	* @Description:
	* @param stockManagerParam
	* @return List<PlantItemVo>  
	* @author longwu at 2015年12月9日上午10:10:21
	* @email longwu@izjjf.cn
	 */
	public Pager<PlantItemVo> getAllPlantItem(StockManagerParamRo stockManagerParam);
	
	
	/**
	 * 根据批发商id查询旗下的商品名称
	* @Title: getCommodityNameBySpId 
	* @Description: 
	* @param stockManagerParam
	* @return List<PlantItemVo>  
	* @author 龙五 at 2015年12月9日上午10:13:12
	* @email longwu@izjjf.cn
	 */
	public List<PlantItemVo> getCommodityName(StockManagerParamRo stockManagerParam);
	/**
	 * 修正库存数量
	* @Title: updatePlantItem 
	* @Description: 
	* @param stockManagerParam   
	* @return void  
	* @author 龙五 at 2015年12月9日上午10:13:49
	* @email longwu@izjjf.cn
	 */
	public int updatePlantItemA(StockManagerParamRo stockManagerParam);
	/**
	 * 修正库存上下限
	* @Title: updatePlantItemUpperLower 
	* @Description: 
	* @param @param stockManagerParam   
	* @return void  
	* @author 龙五 at 2015年12月9日上午10:14:18
	* @email longwu@izjjf.cn
	 */
	public void updateUpperOrLower(StockManagerParamRo stockManagerParam);
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
	
	
	public List<PlantItemVo> getAllCommodityNameAndItemBaseId(Map<String, Object> param);
	
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
	public PlantItemVo getGoodsStockByitemBaseIdSpId(Map<String, Object> param);
	
	/**
	 * 查询供应商所有商品
	* @Title: selectSupplierAllPlantItem 
	* @Description:
	* @param @param supplier
	* @param @return
	* @return List<PlantItemVo>
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Pager<PlantItemVo> selectSupplierAllPlantItem(PlantItemRo plantItemRo);
	
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
	* @Title: addOrUpdateSpPlantItemPrice 
	* @Description:新增或修改供应商对某一商品不同类型用户的出售价格
	* @param @param map
	* @return void
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void addOrUpdateSpPlantItemPrice(Map<String, Object> map);

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


	public void InKuOperation(ScOrderDetail d, String supplierid);
	
	public Pager<PlantItemVo> getSupplierGoodsByParam(PlantItemVo plantItem);

//	 public void updatePlantItemStatus(Map<String, Object> map);


	public Pager<PlantItemVo> getPlantItemGoodsByGroupAndSpId(StockManagerParamRo plantItemRo);
}
