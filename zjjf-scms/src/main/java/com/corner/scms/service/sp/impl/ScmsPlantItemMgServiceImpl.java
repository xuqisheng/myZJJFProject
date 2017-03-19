package com.corner.scms.service.sp.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.PlantItemMapper;
import com.corner.core.dao.ScmsSpSalePriceMapper;
import com.corner.core.dao.ScmsStockLogMapper;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.PlantItemRo;
import com.corner.scms.beans.ro.StockManagerParamRo;
import com.corner.scms.beans.vo.PlantItemVo;
import com.corner.scms.dao.ScmsPlantItemMgMapper;
import com.corner.scms.dao.ScmsSpSalePriceMgMapper;
import com.corner.scms.dao.ScmsSupplierMgMapper;
import com.corner.scms.dao.SpOrderMgMapper;
import com.corner.scms.service.sp.ScmsPlantItemMgService;
import com.corner.scms.service.sp.ScmsStockLogMgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScmsPlantItemMgServiceImpl implements ScmsPlantItemMgService {

	@Autowired
	// core工程里面的PlantItemMapper-dao层接口对象 scmsPlantItemMgMapper
	private PlantItemMapper plantItemMapper;
	@Autowired
	// 本工程里面的scmsPlantItemMgMapper-dao层接口对象
	private ScmsPlantItemMgMapper scmsPlantItemMgMapper;
	@Autowired
	private ScmsSpSalePriceMapper scmsSpSalePriceMapper;
	@Autowired
	private ScmsSpSalePriceMgMapper scmsSpSalePriceMgMapper;
	@Autowired
	private ScmsStockLogMapper scmsStockLogMapper;
	@Autowired
	private ScmsSupplierMgMapper scmsSupplierMgMapper;
	@Autowired
	private ScmsStockLogMgService scmsStockLogMgService;
	@Autowired
	private SpOrderMgMapper spOrderMgMapper;

	/**
	 * 根据条件查询所有的库存信息
	 * 
	 * @Title: getAllPlantItemByParam
	 * @Description:
	 * @param stockManagerParam
	 * @return List<PlantItemVo>
	 * @author longwu at 2015年12月9日上午10:10:21
	 * @email longwu@izjjf.cn
	 */
	@Override
	public Pager<PlantItemVo> getAllPlantItem(StockManagerParamRo stockManagerParam) {
	  try {
			List<PlantItemVo> plantItemVoList = scmsPlantItemMgMapper.getAllPlantItemByParam(stockManagerParam);
			//根据批发商和商品查出订单的中对应已在途的库存
			Map<String, Object> map = null;
			for (PlantItemVo plantItem : plantItemVoList) {
				map = new HashMap<String,Object>();
				map.put("spId", plantItem.getSpId());
				map.put("itemBaseId", plantItem.getItemBaseId());
				Integer sendStock = spOrderMgMapper.getGoodsSendStockBySpIdAndItemBaseId(map);
				if(sendStock != null){
					plantItem.setMiddleStock(plantItem.getMiddleStock().intValue()-sendStock.intValue());
					plantItem.setSendStock(sendStock);
				}else{
					plantItem.setSendStock(0);
				}
			}
			int count = scmsPlantItemMgMapper.getPlantItemCount(stockManagerParam);
			return new Pager<PlantItemVo>(count, plantItemVoList);
	} catch (Exception e) {
       e.printStackTrace();
       return null;
	}


	}

	/**
	 * 根据批发商id查询旗下的商品名称
	 * 
	 * @Title: getCommodityNameBySpId
	 * @Description:
	 * @param stockManagerParam
	 * @return List<PlantItemVo>
	 * @author 龙五 at 2015年12月9日上午10:13:12
	 * @email longwu@izjjf.cn
	 */
	@Override
	public List<PlantItemVo> getCommodityName(StockManagerParamRo stockManagerParam) {
		List<PlantItemVo> CommodityNameList = scmsPlantItemMgMapper.getCommodityNameBySpId(stockManagerParam);
		return CommodityNameList;
	}

	/**
	 * 修正库存数量
	 * 
	 * @Title: updatePlantItem
	 * @Description:
	 * @param stockManagerParam
	 * @return void
	 * @author 龙五 at 2015年12月9日上午10:13:49
	 * @email longwu@izjjf.cn
	 */
	@Override
	public int updatePlantItemA(StockManagerParamRo stockManagerParam) {
		return scmsPlantItemMgMapper.updatePlantItem(stockManagerParam);
	}

	/**
	 * 修正库存上下限
	 * 
	 * @Title: updatePlantItemUpperLower
	 * @Description:
	 * @param @param stockManagerParam
	 * @return void
	 * @author 龙五 at 2015年12月9日上午10:14:18
	 * @email longwu@izjjf.cn
	 */
	@Override
	public void updateUpperOrLower(StockManagerParamRo stockManagerParam) {
		scmsPlantItemMgMapper.updatePlantItemUpperLower(stockManagerParam);
	}

	/**
	 * 根据商品名称查出商品名称和itembaseid
	 * 
	 * @Title: getAllCommodityNameAndItemBaseIdByName
	 * @Description:
	 * @param @param commodityName
	 * @param @return
	 * @return List<PlantItemVo>
	 * @author 龙五 at 2015年12月9日上午11:43:08
	 * @email longwu@izjjf.cn
	 */
	@Override
	public List<PlantItemVo> getAllCommodityNameAndItemBaseId(Map<String, Object> param) {
		List<PlantItemVo> PlantItemVoList = scmsPlantItemMgMapper.getAllCommodityNameAndItemBaseIdByName(param);
		return PlantItemVoList;
	}
	
	
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
	@Override
	public PlantItemVo getGoodsStockByitemBaseIdSpId(Map<String, Object> param){
		List<PlantItemVo> PlantItemVo = scmsPlantItemMgMapper.getGoodsStockByitemBaseIdAndspId(param);
		if(PlantItemVo!=null && PlantItemVo.size()>0){
			return PlantItemVo.get(0);
		}else{
			return null;
		}
	}

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
	@Override
	public Pager<PlantItemVo> selectSupplierAllPlantItem(PlantItemRo plantItemRo) {
		List<PlantItemVo> list = scmsPlantItemMgMapper.selectSupplierAllPlantItem(plantItemRo);
		int totalSize = scmsPlantItemMgMapper.selectCountSupplierAllPlantItem(plantItemRo);
		return new Pager<PlantItemVo>(totalSize, list);
	}

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
	@Override
	public List<PlantItemVo> selectItemBaseByName(String productName) {
		return scmsPlantItemMgMapper.selectItemBaseByName(productName);
	}

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
	@Override
	public List<PlantItemVo> selectPlantItemByCondition(PlantItemRo plantItemRo) {
		return scmsPlantItemMgMapper.selectPlantItemByCondition(plantItemRo);
	}

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
	@Override
	public List<ScmsSpSalePrice> selectSpPriceList(PlantItemRo plantItemRo) {
		return scmsPlantItemMgMapper.selectSpPriceList(plantItemRo);
	}

	/**
	 * 
	 * @Title: addOrUpdateSpPlantItemPrice
	 * @Description:新增或修改供应商对某一商品不同类型用户的出售价格
	 * @param @param map
	 * @return void
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@Override
	public void addOrUpdateSpPlantItemPrice(Map<String, Object> map) {
		synchronized (this) {
		ScmsSpSalePrice scmsSpSalePrice = new ScmsSpSalePrice();
		scmsSpSalePrice.setAddTime(new Date());
		scmsSpSalePrice.setUpdateTime(new Date());
		scmsSpSalePrice.setSpId((String)map.get("spId"));
		scmsSpSalePrice.setItemBaseId((Integer)map.get("itemBaseId"));
		
		scmsSpSalePrice.setPlantItemId((String)map.get("plantItemId"));
		PlantItem p = new PlantItem();
		p.setSpId((String)map.get("spId"));
		p.setItemBaseId((Integer)map.get("itemBaseId"));
		List<PlantItem> list2 = scmsPlantItemMgMapper.selectPlantItemBySpIdAndBaseId(p);
		if(list2!=null&&list2.size()>0){
			map.put("flag", false);
		}
		if ((boolean) map.get("flag")) {
			List<SpGroup> list = scmsSupplierMgMapper.getSupplierAndSpGroup((String)map.get("spId"));
			for (SpGroup spGroup : list) {
				PlantItem plantItem = (PlantItem) map.get("plantItem");
				plantItem.setId(StringUtil.getUUID());
				plantItem.setSpGroupId(spGroup.getId());
				plantItem.setPlantDisPrice(new BigDecimal(0));
				plantItemMapper.insertSelective(plantItem);
			}
			scmsSpSalePrice.setId(StringUtil.getUUID());
			//scmsSpSalePrice.setPlantItemId(plantItem.getId());
			scmsSpSalePrice.setCusType(new Byte("1"));// 便利店
			if((BigDecimal) map.get("storePrice")!=null){
				scmsSpSalePrice.setSalePrice((BigDecimal) map.get("storePrice"));
			}else {
				scmsSpSalePrice.setSalePrice(new BigDecimal(0));
			}
			scmsSpSalePriceMapper.insertSelective(scmsSpSalePrice);
			scmsSpSalePrice.setId(StringUtil.getUUID());
			scmsSpSalePrice.setCusType(new Byte("2"));// 餐饮店
			if((BigDecimal) map.get("resPrice")!=null){
				scmsSpSalePrice.setSalePrice((BigDecimal) map.get("resPrice"));
			}else{
				scmsSpSalePrice.setSalePrice(new BigDecimal(0));
			}
			scmsSpSalePriceMapper.insertSelective(scmsSpSalePrice);
		} else {
			scmsSpSalePrice.setItemBaseId((Integer)map.get("itemBaseId"));
//			scmsSpSalePrice.setAreaPrice((BigDecimal)map.get("areaPrice"));
			//scmsSpSalePrice.setPlantItemId(((PlantItem)map.get("plantItem")).getId());
			List<ScmsSpSalePrice> list = scmsSpSalePriceMgMapper.getPriceByCondition(map);
			String scmsId = "";
			if(list!=null&&list.size()!=0){
				//if((BigDecimal) map.get("storePrice")!=null){
					for (ScmsSpSalePrice scmsSpSalePrice2 : list) {
						if(scmsSpSalePrice2.getCusType()==1){
							scmsId=scmsSpSalePrice2.getId();
                            break;							
						}
					}
					if (!StringUtil.stringIsNullOrEmpty(scmsId)) {
						scmsSpSalePrice.setId(scmsId);
						scmsId="";
					}
					scmsSpSalePrice.setCusType(new Byte("1"));
					if((BigDecimal) map.get("storePrice")!=null){
						scmsSpSalePrice.setSalePrice((BigDecimal) map.get("storePrice"));
					}else {
						scmsSpSalePrice.setSalePrice(new BigDecimal(0));
					}
					scmsSpSalePriceMgMapper.insertOrUpdate(scmsSpSalePrice);
				//}
				//if((BigDecimal) map.get("resPrice")!=null){
					scmsSpSalePrice.setId(StringUtil.getUUID());
					for (ScmsSpSalePrice scmsSpSalePrice2 : list) {
						if(scmsSpSalePrice2.getCusType()==2){
							scmsId=scmsSpSalePrice2.getId();
                            break;							
						}
					}
					if (!StringUtil.stringIsNullOrEmpty(scmsId)) {
						scmsSpSalePrice.setId(scmsId);
					}
					scmsSpSalePrice.setCusType(new Byte("2"));
					if((BigDecimal) map.get("resPrice")!=null){
						scmsSpSalePrice.setSalePrice((BigDecimal) map.get("resPrice"));
					}else {
						scmsSpSalePrice.setSalePrice(new BigDecimal(0));
					}
					scmsSpSalePriceMgMapper.insertOrUpdate(scmsSpSalePrice);
				//}
			}else {
				//if((BigDecimal) map.get("storePrice")!=null){
					scmsSpSalePrice.setCusType(new Byte("1"));
					if((BigDecimal) map.get("storePrice")!=null){
						scmsSpSalePrice.setSalePrice((BigDecimal) map.get("storePrice"));
					}else {
						scmsSpSalePrice.setSalePrice(new BigDecimal(0));
					}
					scmsSpSalePriceMgMapper.insertOrUpdate(scmsSpSalePrice);
				//}
				//if((BigDecimal) map.get("resPrice")!=null){
					scmsSpSalePrice.setId(StringUtil.getUUID());
					scmsSpSalePrice.setCusType(new Byte("2"));
					if((BigDecimal) map.get("resPrice")!=null){
						scmsSpSalePrice.setSalePrice((BigDecimal) map.get("resPrice"));
					}else {
						scmsSpSalePrice.setSalePrice(new BigDecimal(0));
					}
					scmsSpSalePriceMgMapper.insertOrUpdate(scmsSpSalePrice);
				//}
			}
			//商品上下架改价
			Map<String, Object> mapParam = null;
			Integer[] spGroupIds = (Integer[])map.get("spGroupIds");
			BigDecimal[] areaPrices = (BigDecimal[])map.get("areaPrices"); 
			Byte[] statuss = (Byte[])map.get("statuss");
			if((spGroupIds != null && spGroupIds.length>0) && (areaPrices != null && areaPrices.length>0) && (statuss != null && statuss .length >0)){
				for (int i = 0; i < spGroupIds.length; i++) {
					mapParam = new HashMap<String, Object>();
					mapParam.put("spId", map.get("spId"));
					mapParam.put("itemBaseId", map.get("itemBaseId"));
					mapParam.put("spGroupId", spGroupIds[i]);
					mapParam.put("areaPrice", areaPrices[i]);
					mapParam.put("status", statuss[i]);
					scmsPlantItemMgMapper.updatePlantItemStatus(mapParam);
				}
			}
		}
		
		}
	}

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
	@Override
	public PlantItemVo selectPlantItemAndUserTypePrice(PlantItemRo plantItemRo) {
		return scmsPlantItemMgMapper.selectPlantItemAndUserTypePrice(plantItemRo);
	}

	@Override
	//根据批发商id 做入库操作
	public void InKuOperation(ScOrderDetail d,String supplierid) { //订单里面有商品id字段为itemId,批发商id为  supplierid 订单里面有商品的价格 zjjfPrice
		//1根据 商品id 和 批发商id 查询 库存有没有 商品      if 有 则 做更新操作  if 没有  做插入操作  然后更新 入库日志表
		Integer itemId=this.scmsPlantItemMgMapper.findId(d.getItemId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("supplierid", supplierid);
		PlantItem plantItem=this.scmsPlantItemMgMapper.findItemBySidAndItemid(map);
		if(plantItem==null){
			//插入数据
			plantItem=new PlantItem();
			plantItem.setAddTime(new Date());
			plantItem.setItemBaseId(itemId);
			plantItem.setSpId(supplierid);
			plantItem.setStatus((byte) 1);
			plantItem.setGoodsStock(d.getQuantity().intValue());
			plantItem.setPlantDisPrice(d.getZjjfPrice());
			this.plantItemMapper.insertSelective(plantItem);
		}else{
			//跟新数据
			StockManagerParamRo ro=new StockManagerParamRo();
			ro.setItemBaseId(plantItem.getItemBaseId());
			ro.setSpId(supplierid);
			ro.setxType((byte) 2);
			ro.setNum(d.getQuantity().intValue());
			ro.setScInPrice(d.getZjjfPrice());
			ro.setUpdateTime(new Date());
			this.scmsPlantItemMgMapper.updatePlantItem(ro);
		}
		//插入入库日志
		ScmsStockLog scmsStockLog = new ScmsStockLog();
		scmsStockLog.setSpId(d.getSpId());
		scmsStockLog.setPlantItemId(plantItem.getId());
		String num =d.getQuantity().toString();
		scmsStockLog.setQuantity(Integer.parseInt(num));
		scmsStockLog.setXtype(Byte.parseByte("2"));
		scmsStockLogMgService.addStockLog(scmsStockLog);

	}
	
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
	 public Pager<PlantItemVo> getSupplierGoodsByParam(PlantItemVo plantItem){
		 List<PlantItemVo> PlantItemList = scmsPlantItemMgMapper.getSupplierGoodsByParam(plantItem);
		 int num = scmsPlantItemMgMapper.getSupplierGoodsByParamCount(plantItem);
		 return new Pager<PlantItemVo>(num, PlantItemList);
	 }

//	 public void updatePlantItemStatus(Map<String, Object> map){
//		 scmsPlantItemMgMapper.updatePlantItemStatus(map);
//	 }

	@Override
	public Pager<PlantItemVo> getPlantItemGoodsByGroupAndSpId(StockManagerParamRo plantItemRo) {
		// TODO Auto-generated method stub
		List<PlantItemVo> plantItemVoList = scmsPlantItemMgMapper.getPlantItemGoodsByGroupAndSpId(plantItemRo);
		int num = scmsPlantItemMgMapper.getPlantItemGoodsByGroupAndSpIdCount(plantItemRo);
		return new Pager(num, plantItemVoList);
	}
}
