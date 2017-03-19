package com.zjjf.analysis.services.items;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.items.PlantItemDaily;
import com.zjjf.analysis.beans.origin.items.ItemBase;
import com.zjjf.analysis.beans.origin.items.PlantItem;
import com.zjjf.analysis.beans.origin.orders.SpOrderDetailVo;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.beans.origin.supplier.Supplier;
import com.zjjf.analysis.common.utils.DateUtil;
import com.zjjf.analysis.mapper.analysis.PlantItemDailyMapper;
import com.zjjf.analysis.mapper.origin.ItemBaseMapper;
import com.zjjf.analysis.mapper.origin.PlantItemMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.SpOrderDetailMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class PlantItemDailyServiceJobImpl extends AbstractTimeService<SpOrderDetailVo, PlantItemDaily> {

	@Autowired
	private PlantItemDailyMapper plantItemDailyMapper;

	@Autowired
	private PlantItemMapper plantItemMapper;
	
	@Autowired
	private ItemBaseMapper itemBaseMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;
	
	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private SpOrderDetailMapper spOrderDetailMapper;

	@Override
	public List<? extends SpOrderDetailVo> getDataByTime(String yyyyMMdd, Integer offset) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("gaveTimeBegin", DateUtil.timeyyyyMMddConvertyyyy_MM_dd(yyyyMMdd) + " 00:00:00");
		map.put("gaveTimeEnd", DateUtil.timeyyyyMMddConvertyyyy_MM_dd(yyyyMMdd) + " 59:59:59");
		map.put("offset", (offset - 1) * 1000);
		return spOrderDetailMapper.getPlantItemList(map);
	}

	@Override
	public void _process(SpOrderDetailVo t, PlantItemDaily v, String dayTime) {

		addBean(t, v, dayTime);
	}

	public void addBean(SpOrderDetailVo t, PlantItemDaily v, String dayTime) {

		String itemId = t.getItemId();
		Integer spGroupId = t.getSpGroupId();
		Integer itemBaseId = t.getItemBaseId();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemId", itemId);
		paramMap.put("spGroupId", spGroupId);
		paramMap.put("itemBaseId", itemBaseId);
		paramMap.put("addTime", dayTime);

		PlantItemDaily exitBean = plantItemDailyMapper.getExitRecord(paramMap);
		if (exitBean != null && exitBean.getItemId() != null) {
			update_plantdaily(exitBean, paramMap);
		} else {
			add_plantdaily(v, paramMap, itemId, dayTime, itemBaseId);
		}
	}

	private void update_plantdaily(PlantItemDaily exitBean, HashMap<String, Object> paramMap) {

		SpOrderDetailVo updateBean = spOrderDetailMapper.getTurnOverByItemId(paramMap);
		if (updateBean != null && updateBean.getId() != null) {
			PlantItemDaily v = new PlantItemDaily();
			v.setTurnover(updateBean.getTurnover());
			v.setFee(updateBean.getFee());
			v.setMaoli(updateBean.getMaoli());
			v.setOrderCount(updateBean.getOrderCount());
			v.setQuantity(updateBean.getQuantity());
			plantItemDailyMapper.update_daily(v);
		}
	}

	private void add_plantdaily(PlantItemDaily v, HashMap<String, Object> paramMap, String itemId, String dayTime,
			Integer itemBaseId) {

		ItemBase itemBase = itemBaseMapper.getById(itemBaseId);
		if(itemBase != null){
			v.setItemName(itemBase.getName());
			v.setMdseId(itemBase.getMdseId());
		}
		v.setItemId(itemId);
		v.setItemBaseId(itemBaseId);
		getSupplierOrderInfo(v, spOrderDetailMapper.getTurnOverByItemId(paramMap), dayTime);
		getSupplierPrice(v, paramMap, itemBaseId, itemId);
		getSupplierItemInfo(v, paramMap, itemBaseId, itemId);
		getSupplierQueryParam(v, paramMap, v.getSupplierId(), v.getSpGroupId());
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
		addPlantItemDaily(v);
	}

	// 获取供应商订单信息
	private void getSupplierOrderInfo(PlantItemDaily v, SpOrderDetailVo temp, String dayTime) {

		if (temp != null) {
			v.setDayTime(Integer.valueOf(dayTime));
			v.setTurnover(temp.getTurnover());
			v.setFee(temp.getFee());
			v.setMaoli(temp.getMaoli());
			v.setOrderCount(temp.getOrderCount());
			v.setQuantity(temp.getQuantity());
		}
	}

	// 获取供应商最高价，最低价，平均价
	private void getSupplierPrice(PlantItemDaily v, HashMap<String, Object> paramMap, Integer itemBaseId, String itemId) {

		PlantItem plantItemVo = plantItemMapper.getDipolarPrice(paramMap);
		if (plantItemVo != null && plantItemVo.getItemId() != null) {
			v.setMaxPrice(plantItemVo.getMaxPrice());
			v.setMinPrice(plantItemVo.getMinPrice());
			v.setAveragePrice(plantItemVo.getAveragePrice());
		}
	}

	// 获取供应商对应商品大小分类，supplierId， spGroupId
	private void getSupplierItemInfo(PlantItemDaily v, HashMap<String, Object> paramMap, Integer itemBaseId, String itemId) {

		PlantItem plantItemInfo = plantItemMapper.getItemInfo(paramMap);
		if (plantItemInfo != null && plantItemInfo.getItemBaseId() != null) {
			v.setClassOne(plantItemInfo.getClassOne());
			v.setClassTwo(plantItemInfo.getClassTwo());
			v.setClassOneId(plantItemInfo.getClassOneId());
			v.setClassTwoId(plantItemInfo.getClassTwoId());
			v.setSupplierId(plantItemInfo.getSpId());
			v.setSpGroupId(plantItemInfo.getSpGroupId());
		}
	}

	// 获取供应商城市，区域，定格等查询条件的信息
	private void getSupplierQueryParam(PlantItemDaily v, HashMap<String, Object> paramMap, String supplierId, Integer spGroupId) {

		paramMap.clear();
		paramMap.put("supplierId", supplierId);
		paramMap.put("spGroupId", spGroupId);
		Supplier supplier = supplierMapper.getBySupplierId(paramMap);
		if (supplier != null) {
			v.setCityId(supplier.getCityId());
			v.setAreaId(supplier.getAreaId());
			v.setCityName(supplier.getCityName());
			v.setAreaName(supplier.getAreaName());
			v.setSupplierName(supplier.getSupplierName());
		}
		SpGroup spGroup = spGroupMapper.getById(spGroupId);
		if (spGroup != null) {
			v.setSpGroupName(spGroup.getName());
		}
	}

	private void addPlantItemDaily(PlantItemDaily v) {

		plantItemDailyMapper.insert(v);
	}
}
