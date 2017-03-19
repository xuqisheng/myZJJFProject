package com.zjjf.analysis.services.items;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.items.PlantItemDaily;
import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderDetail;
import com.zjjf.analysis.beans.origin.items.PlantItem;
import com.zjjf.analysis.beans.origin.orders.SpOrderDetailVo;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderDetailMapper;
import com.zjjf.analysis.mapper.analysis.PlantItemDailyMapper;
import com.zjjf.analysis.mapper.origin.PlantItemMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class ItemBaseDailyServiceJobImpl extends AbstractTimeService<AnalysisSpOrderDetail, PlantItemDaily> {

	@Autowired
	private AnalysisSpOrderDetailMapper analysisSpOrderDetailMapper;

	@Autowired
	private PlantItemDailyMapper plantItemDailyMapper;

	@Autowired
	private PlantItemMapper plantItemMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Override
	public List<? extends AnalysisSpOrderDetail> getDataByTime(String yyyyMMdd, Integer offset) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dayTime", yyyyMMdd);
		map.put("offset", (offset - 1) * 1000);
		return analysisSpOrderDetailMapper.getPlantItemList(map);
	}

	@Override
	public void _process(AnalysisSpOrderDetail t, PlantItemDaily v, String dayTime) {

		addBean(t, v, dayTime);
	}

	public void addBean(AnalysisSpOrderDetail t, PlantItemDaily v, String dayTime) {

		String itemId = t.getItemId();
		Integer itemBaseId = t.getItemBaseId();
		Integer spGroupId = t.getSpGroupId();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemId", itemId);
		paramMap.put("itemBaseId", itemBaseId);
		paramMap.put("spGroupId", spGroupId);
		paramMap.put("dayTime", dayTime);

		PlantItemDaily exitBean = plantItemDailyMapper.getExitRecord(paramMap);
		if (exitBean != null && exitBean.getItemId() != null) {
			update_plantdaily(exitBean, paramMap);
		} else {
			add_plantdaily(v, paramMap, itemId, dayTime, itemBaseId);
		}
	}

	private void update_plantdaily(PlantItemDaily exitBean, HashMap<String, Object> paramMap) {

		SpOrderDetailVo updateBean = analysisSpOrderDetailMapper.getTurnOverByItemId(paramMap);
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

		v.setItemId(itemId);
		v.setItemBaseId(itemBaseId);
		getSupplierOrderInfo(v, paramMap, dayTime);
		getSupplierPrice(v, paramMap, itemBaseId, itemId);
		getSupplierItemInfo(v, paramMap, itemBaseId, itemId);
		getSupplierQueryParam(v, paramMap, v.getSupplierId(), v.getSpGroupId());
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
		addPlantItemDaily(v);
	}

	// 获取供应商订单信息
	private void getSupplierOrderInfo(PlantItemDaily v, HashMap<String, Object> paramMap, String dayTime) {

		SpOrderDetailVo temp = analysisSpOrderDetailMapper.getTurnOverByItemId(paramMap);
		if (temp != null && temp.getItemId() != null) {
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
			v.setClassOneId(plantItemInfo.getClassOneId());
			v.setClassTwoId(plantItemInfo.getClassTwoId());
			v.setSupplierId(plantItemInfo.getSpId());
			v.setSpGroupId(plantItemInfo.getSpGroupId());
		}
	}

	// 获取供应商城市，区域，定格等查询条件的信息
	private void getSupplierQueryParam(PlantItemDaily v, HashMap<String, Object> paramMap, String supplierId, Integer spGroupId) {

		paramMap.clear();
		SpGroup spGroup = spGroupMapper.getById(spGroupId);
		if (spGroup != null) {
			v.setCityId(spGroup.getCityId());
			v.setAreaId(spGroup.getAreaId());
		}
	}

	private void addPlantItemDaily(PlantItemDaily v) {

		plantItemDailyMapper.insert(v);
	}
}
