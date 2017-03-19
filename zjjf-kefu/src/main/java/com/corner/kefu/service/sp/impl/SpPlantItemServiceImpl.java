package com.corner.kefu.service.sp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.corner.core.beans.PlantItemProduct;
import com.corner.kefu.service.callable.CallAbleService;
import com.corner.kefu.service.sp.PlantItemProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.PlantItem;
import com.corner.core.beans.PlantItemPre;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.PlantItemLogMapper;
import com.corner.core.dao.PlantItemMapper;
import com.corner.core.dao.PlantItemPreMapper;
import com.corner.kefu.beans.ro.sp.PlantItemRo;
import com.corner.kefu.beans.vo.ItemBaseVo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;
import com.corner.kefu.beans.vo.sp.SKUActiveSessionObject;
import com.corner.kefu.dao.ItemBaseMgMapper;
import com.corner.kefu.dao.sp.SpGroupMgMapper;
import com.corner.kefu.dao.sp.SpPlantItemMgMapper;
import com.corner.kefu.service.sp.SpPlantItemService;

/**
 * 
* @ClassName: SpPlantItemServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午10:30:34 
*
 */
@Service
public class SpPlantItemServiceImpl implements SpPlantItemService {

	@Autowired
	PlantItemMapper plantItemMapper;

	@Autowired
	ItemBaseMgMapper itemBaseMgMapper;

	@Autowired
	SpPlantItemMgMapper spPlantItemMgMapper;

	@Autowired
	PlantItemPreMapper plantItemPreMapper;

	@Autowired
	PlantItemLogMapper plantItemLogMapper;

	@Autowired
	SpGroupMgMapper spGroupMgMapper;
	@Autowired
	PlantItemProductService plantItemProductService;
	@Autowired
	CallAbleService callAbleService;
	@Override
	public PlantItem selectByPrimaryKey(String id) throws Exception {
		return plantItemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	* Title: upPlantItem 
	* Description:上下架PlantItem 
	* @param plantItem
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpPlantItemService#upPlantItem(com.corner.core.beans.PlantItem)
	 */
	@Override
	public void upPlantItem(PlantItem plantItem) throws Exception {
		plantItemMapper.updateByPrimaryKeySelective(plantItem);
	}

	/**
	 * 
	* @Title: addPrePlantItem 
	* @Description: 添加商品到PlantItemPre表
	* @param @param map
	* @param @return
	* @param @throws Exception    设定文件 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public ModelMsg addPrePlantItem(Map<String, Object> map) throws Exception {
		ItemBaseVo itemBaseVo = itemBaseMgMapper.selectItemBaseByMdseId((String) map.get("mdseId"));// mdseId
																									// 是条形码
		ModelMsg modelMsg = new ModelMsg();
		if (itemBaseVo == null) {
			modelMsg.setSuccess(false);
			modelMsg.setMessage("没有该商品!");
			return modelMsg;
		}
		map.put("itemBaseId", itemBaseVo.getId());

		// 查询预设表
		/*
		 * List<PlantItem>currentList =
		 * spPlantItemMgMapper.getPlantItemListBySpIdAndSpGroupIdAndItemBaseId(
		 * map); if(currentList!=null&&currentList.size()!=0){
		 * spPlantItemMgMapper.insertIgnorePlantItemPre(currentList); }
		 */
		modelMsg.setSuccess(true);
		return modelMsg;
	}

	@Override
	public Map<String, Object> selectPrePlantItem(Map<String, Object> map) throws Exception {
		ItemBaseVo it = itemBaseMgMapper.selectItemBaseByMdseId((String) map.get("mdseId"));
		if (it == null) {
			return null;
		}
		Map<String, Object> itb = new HashMap<String, Object>();
		itb.put("itembase", it);
		map.put("itemBaseId", it.getId());
		String priceStr = "";
		List<PlantItem> list = spPlantItemMgMapper.selectPrePlantItembyitemBaseIdAndSupplierId(map);
		for (Iterator<PlantItem> iterator = list.iterator(); iterator.hasNext();) {
			PlantItem plantItem = iterator.next();
			priceStr += plantItem.getSpGroupId() + ":" + plantItem.getPlantDisPrice() + ":"// 进货价
					+ plantItem.getAreaPrice() + ":"// 出货价
					+ plantItem.getPlantMemPrice() + ":"// 市场价
					+ plantItem.getMaoli() + ":"// 毛利
					+ plantItem.getStatus() + ":" // 状态
					+ plantItem.getRemark() + ":"// 备注
					+ plantItem.getRestrict() + ":"// 数量限制
					+ plantItem.getYouHui() // 优惠
					+ ";";
		}
		itb.put("str", priceStr);
		return itb;
	}

	@Override
	public List<PlantItem> getPlantItemListBySpIdAndSpGroupIdAndItemBaseId(Map<String, Object> map) throws Exception {
		return spPlantItemMgMapper.getPlantItemListBySpIdAndSpGroupIdAndItemBaseId(map);
	}

	@Override
	public void upPlantItemOderId(PlantItem plantItem) throws Exception {
		spPlantItemMgMapper.upPlantItemOderId(plantItem);
	}

	@Override
	public PlantItemPre getPlantItemPreById(String id) throws Exception {
		return plantItemPreMapper.selectByPrimaryKey(id);
	}

	@Override
	public void upPlantItemPreOderId(PlantItemPre plantItempre) throws Exception {
		spPlantItemMgMapper.upPlantItemPreOderId(plantItempre);
	}

	@Override
	public List<PlantItem> getPlantItemPreListByItemBaseIdAndSupplierId(Map<String, Object> map) throws Exception {
		return spPlantItemMgMapper.selectPrePlantItembyitemBaseIdAndSupplierId(map);
	}

	@Override
	public void batchAddPlantItemPre(List<PlantItem> insertList) throws Exception {
		for (Iterator<PlantItem> iterator = insertList.iterator(); iterator.hasNext();) {
			PlantItem plantItem = iterator.next();
			spPlantItemMgMapper.insertPlantItemPreSelective(plantItem);
		}
	}

	@Override
	public void batchUpdatePlantItemPre(List<PlantItem> updateList) throws Exception {
		spPlantItemMgMapper.batchUpdatePlantItemPre(updateList);
	}

	@Override
	public List<PlantItemPre> getAllPlantItemPre() {
		return spPlantItemMgMapper.getAllPlantItemPre();
	}

	@Override
	public PlantItem getPlantItemById(String id) {
		return plantItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public void addPlantItemList(List<PlantItemPre> insertList) {
		for (PlantItemPre plantItemPre : insertList) {
			spPlantItemMgMapper.insertPlantItemPreSelective2(plantItemPre);
		}
	}

	@Override
	public int update(PlantItem plant) {
		spPlantItemMgMapper.insertLog(plant);
		return plantItemMapper.updateByPrimaryKeySelective(plant);
	}

	@Override
	public void removeAllPlantItemPre() {
		spPlantItemMgMapper.removeAllPlantItemPre();
	}

	@Override
	public Pager<PlantItemVo> getSupplierAllStock(PlantItemRo plantItemRo) {
		List<PlantItemVo> PlantItemVoList = spPlantItemMgMapper.getSupplierAllStock(plantItemRo);
		int num = spPlantItemMgMapper.getSupplierAllStockCount(plantItemRo);
		return new Pager<PlantItemVo>(num, PlantItemVoList);
	}

	@Override
	public List<SpGroup> getAllSpGroup() {
		return spPlantItemMgMapper.getAllSpGroup();
	}

	@Override
	public PlantItemVo getPlantItemByGroupAndSpId(PlantItemRo plantItemRo) {
		// TODO Auto-generated method stub
		return spPlantItemMgMapper.getPlantItemByGroupAndSpId(plantItemRo);
	}

	@Override
	public Pager<PlantItemVo> getPlantItemGoodsByGroupAndSpId(PlantItemRo plantItemRo) {
		// TODO Auto-generated method stub
		List<PlantItemVo> plantItemVoList = spPlantItemMgMapper.getPlantItemGoodsByGroupAndSpId(plantItemRo);
		int num = spPlantItemMgMapper.getPlantItemGoodsByGroupAndSpIdCount(plantItemRo);
		return new Pager(num, plantItemVoList);
	}

	@Override
	public int savePlantItem(PlantItem plantItem) {
		PlantItemProduct product = new PlantItemProduct();
		product.setId(plantItem.getId());
		product.setProductPrice(plantItem.getAreaPrice());
		product.setProMarketPrice(plantItem.getPlantMemPrice());

		product.setBuyLimitNum(plantItem.getRestrict());
		product.setPkgName("默认");
		product.setTotalLimitNum(plantItem.getSKUProTotalLimitNum());
		product.setRemark(plantItem.getRemark());
		product.setYouHui(plantItem.getYouHui());
		try {
			plantItemProductService.insertDefaultProduct(product);
			plantItem.setLogicStockId(callAbleService.checkItemIsHave(plantItem.getWarehouseId() , plantItem.getItemBaseId() , plantItem.getLogicStockTypeMg().intValue()));
		}catch (Exception e){
			System.out.println("error:"+e.toString());
			return 0;
		}

		return plantItemMapper.insertSelective(plantItem);
	}

	@Override
	public List<PlantItem> selectPlantItemByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spPlantItemMgMapper.selectPlantItemByCondition(map);
	}

	/**
	 * 
	* Title: selectItemPc 
	* Description:判断条形码是否存在 
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpPlantItemService#selectItemPc(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectItemPc(Map<String, Object> map) throws Exception {
		ItemBaseVo it = itemBaseMgMapper.selectItemBaseByMdseId((String) map.get("mdseId"));
		if (it == null) {
			return null;
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("itembase", it);

		map.put("itemBaseId", it.getId());
		List<PlantItem> list = spPlantItemMgMapper.selecPlantItembyitemBaseIdAndSupplierId(map);
		String priceStr = "";
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			PlantItem plantItem = (PlantItem) iterator.next();
			priceStr += plantItem.getSpGroupId() + ":" + plantItem.getPlantDisPrice() + ":"// 进货价
					+ plantItem.getAreaPrice() + ":"// 出货价
					+ plantItem.getPlantMemPrice() + ":"// 市场价
					+ plantItem.getMaoli() + ":"// 毛利
					+ plantItem.getStatus() + ":" // 状态
					+ plantItem.getRemark() + ":"// 备注
					+ plantItem.getRestrict() + ":"// 数量限制
					+ plantItem.getYouHui() // 优惠
					+ ";";
		}
		map2.put("str", priceStr);
		return map2;
	}

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
	@Override
	public ModelMsg getPlantItemPrice(Map<String, Object> paramMap) throws Exception {
		String mdseId = (String) paramMap.get("mdseId");// 商品条形码
		// String spId = (String) paramMap.get("id");//批发商id
		ModelMsg modelMsg = new ModelMsg();
		modelMsg.setSuccess(false);
		ItemBaseVo it = itemBaseMgMapper.selectItemBaseByMdseId(mdseId);
		if (it == null) {
			modelMsg.setMessage("没有该商品!");
			return modelMsg;
		}
		paramMap.put("itemBaseId", it.getId());// ItemBase id
		List<PlantItemVo> resultList = spPlantItemMgMapper.getPlantItemVoList(paramMap);
		for (PlantItemVo plantItemVo : resultList) {
			if (plantItemVo.getAreaPrice() != null || plantItemVo.getPlantDisPrice() != null) {
				plantItemVo.setIsNew("0");// 表示在PlantItem表中有该数据的
			} else {
				plantItemVo.setIsNew("1");// 表示在PlantItem表中没有该数据的
			}
		}
		modelMsg.setSuccess(true);
		// 如果resultList 不为空，且size不为0 表示在PlantItem表中已经有该mdseId
		// 的商品需要往PlantItemPre表中插入数据
		// if(resultList!=null&&resultList.size()!=0){
		// //根据plantItemVo 的 id 先在PlantItemPre表中删除一遍数据，再插入
		// //spPlantItemMgMapper.deleteByIds(resultList);
		// //spPlantItemMgMapper.batchAddByPlantItemoVoList(resultList);
		// //循环resultList
		// for (PlantItemVo plantItemVo : resultList) {
		// //如果areaPrice 或 plantDisPrice 或 plantMemPrice 这些字段不为空
		// if(plantItemVo.getAreaPrice()!=null||plantItemVo.getPlantDisPrice()!=null||plantItemVo.getPlantMemPrice()!=null){
		// spPlantItemMgMapper.deleteByPlantItemVoId(plantItemVo);
		// spPlantItemMgMapper.insertPlantItemPreWithPlantItemVo(plantItemVo);
		// }
		// }
		// }

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("itemBase", it);
		resultMap.put("resultList", resultList);
		modelMsg.setData(resultMap);
		return modelMsg;
	}

	/**
	 * 
	* Title: getPlantItemPrePrice 
	* Description:根据批发商id和商品条形码，查询PlantItemPre
	* 将该商品在批发商所在的所有定格下的价格显示出来 
	* @param paramMap
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpPlantItemService#getPlantItemPrePrice(java.util.Map)
	 */
	@Override
	public ModelMsg getPlantItemPrePrice(Map<String, Object> paramMap) throws Exception {
		String mdseId = (String) paramMap.get("mdseId");// 商品条形码
		// String spId = (String) paramMap.get("id");//批发商id
		ModelMsg modelMsg = new ModelMsg();
		modelMsg.setSuccess(false);
		ItemBaseVo it = itemBaseMgMapper.selectItemBaseByMdseId(mdseId);
		if (it == null) {
			modelMsg.setMessage("没有该商品!");
			return modelMsg;
		}
		paramMap.put("itemBaseId", it.getId());// ItemBase id
		List<PlantItemVo> resultList = spPlantItemMgMapper.getPlantItemPreVoList(paramMap);
		modelMsg.setSuccess(true);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("itemBase", it);
		resultMap.put("resultList", resultList);
		modelMsg.setData(resultMap);
		return modelMsg;
	}

	/**
	 * 
	* Title: updateOrInserPlantItemPre 
	* Description:往PlantItemPre表中插入或更新数据 
	* @param paramMap
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpPlantItemService#updateOrInserPlantItemPre(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized ModelMsg updateOrInserPlantItemPre(Map<String, Object> paramMap) throws Exception {
		List<PlantItemPre> plantItemPreList = (List<PlantItemPre>) paramMap.get("plantItemPreList");
		if (plantItemPreList != null && plantItemPreList.size() != 0) {
			for (PlantItemPre plantItemPre : plantItemPreList) {
				/**
				 * PlantItem plantItem = spPlantItemMgMapper.getPlantItemByPlantItemPreId(plantItemPre);
				if(plantItem==null){
					PlantItem minGoodsStock = spPlantItemMgMapper.getMinGoodsStockByItemBaseIdAndSpId(plantItemPre);
					if(minGoodsStock!=null){
					  plantItemPre.setGoodsStock(minGoodsStock.getGoodsStock());	
					}
					spPlantItemMgMapper.preInsert(plantItemPre);
				}
				spPlantItemMgMapper.deletePlantItemPreById(plantItemPre);
				plantItemPreMapper.insertSelective(plantItemPre);*/
				if (plantItemPre.getItemBaseId() != null && !StringUtils.isEmpty(plantItemPre.getSpId())
						&& plantItemPre.getSpGroupId() != null) {
					List<PlantItem> list = spPlantItemMgMapper
							.getPlantItemListWithSpIdAndSpGroupIdAndItemBaseId(plantItemPre);
					if (list != null && list.size() != 0) {
						for (PlantItem plantItem : list) {
							if (plantItem.getId().equals(plantItemPre.getId())) {
								spPlantItemMgMapper.deletePlantItemPreById(plantItemPre);
								plantItemPreMapper.insertSelective(plantItemPre);
								break;
							}
						}
					} else {
						spPlantItemMgMapper.deleteFromPlantItemPreWithSpIdAndSpGroupIdAndItemBaseId(plantItemPre);
						PlantItem minGoodsStock = spPlantItemMgMapper.getMinGoodsStockByItemBaseIdAndSpId(plantItemPre);
						if (minGoodsStock != null) {
							plantItemPre.setGoodsStock(minGoodsStock.getGoodsStock());
						}
						PlantItem plantItem = new PlantItem();
						BeanUtils.copyProperties(plantItemPre, plantItem);
						plantItem.setStatus((byte) 0);
						plantItemMapper.insertSelective(plantItem);
						plantItemPreMapper.insertSelective(plantItemPre);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	* @Title: updatePriceTask 
	* @Description:定时任务,将PlantItemPre表中的数据更新到PlantItemPre中
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public void updatePriceTask() throws Exception {

		List<PlantItemPre> list = spPlantItemMgMapper.getAllPlantItemPre();
		// 更新价格
		if (list != null && list.size() != 0) {
			spPlantItemMgMapper.insertOrUpdatePlantItem(list);
			// 将数据插入PlantItemLog表中
			spPlantItemMgMapper.batchAddPlantItemLog(list);
		}
		// 清空PlantItemPre表
		spPlantItemMgMapper.removeAllPlantItemPre();
	}

	@Override
	public List<PlantItemVo> getPlantItemBySpGroupIdAndSupplierIdAndItemBaseId(Integer[] spGroupIds,
			String[] supplierIds, SKUActiveSessionObject sessionObject) {
		Map<String, Object> map = null;
		// session里面的商品数据
		List<ItemBaseVo> itemBaseList = sessionObject.getItemBases();
		// 存放下一页的数据（plantItem）
		List<PlantItemVo> list = new ArrayList<PlantItemVo>();
		for (int i = 0; i < supplierIds.length; i++) {
			map = new HashMap<String, Object>();
			map.put("spGroupId", spGroupIds[i]);
			map.put("supplierId", supplierIds[i]);
			for (ItemBaseVo itemBase : itemBaseList) {
				map.put("itemBaseId", itemBase.getId());
				// 匹配商品
				String id = spPlantItemMgMapper.checkGoods(map);
				if (id != null && !"".equals(id)) {
					PlantItemVo plantItem = spPlantItemMgMapper.getPlantItemById(id);
					if (plantItem != null) {
						plantItem.setSKUProLimitNum(itemBase.getProLimitNum());
						plantItem.setSKUProPrice(itemBase.getProPrice());
						plantItem.setTagLabelId1(itemBase.getTagId());
						plantItem.setBrandId(itemBase.getBrandId());
						list.add(plantItem);
					}
				}
			}
		}
		return list;
	}

	@Override
	public void updatePlantItemRestrict(String id) {
         //spPlantItemMgMapper.updatePlantItemRestrict(id);
         //spPlantItemMgMapper.updatePlantItemPreRestrict(id);
		spPlantItemMgMapper.removeBuyLimit(id);
	}

}
