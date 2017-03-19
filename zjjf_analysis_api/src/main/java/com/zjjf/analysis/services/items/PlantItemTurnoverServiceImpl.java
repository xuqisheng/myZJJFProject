package com.zjjf.analysis.services.items;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.items.ItemBase;
import com.zjjf.analysis.beans.analysis.items.ItemCatelog;
import com.zjjf.analysis.beans.analysis.items.PlantItemDailyVo;
import com.zjjf.analysis.mapper.analysis.PlantItemDailyMapper;
import com.zjjf.analysis.mapper.origin.ItemBaseMapper;
import com.zjjf.analysis.mapper.origin.ItemCatelogMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.producer.items.IPlantItemService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;
import com.zjjf.analysis.utils.StringUtil;

@Service(version = "1.0.0")
public class PlantItemTurnoverServiceImpl extends AbstractModelService<PlantItemDailyVo> implements IPlantItemService<PlantItemDailyVo> {

	@Autowired
	private PlantItemDailyMapper plantItemDailyMapper;

	@Autowired
	private ItemBaseMapper itemBaseMapper;

	@Autowired
	private ItemCatelogMapper itemCatelogMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Override
	public List<PlantItemDailyVo> getDataList(HashMap<String, Object> paramMap) {

		return plantItemDailyMapper.getData(appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return plantItemDailyMapper.getDataCount(appendParam(paramMap));
	}

	@Override
	public List<PlantItemDailyVo> getDataExcel(HashMap<String, Object> paramMap) {

		return plantItemDailyMapper.getDataExcel(appendParam(paramMap));
	}

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> map) {

		if (map.containsKey("nameOrbarCode") && map.get("nameOrbarCode") != null) {
			map.put("itemBaseIdList", itemBaseMapper.getByLikeNameOrMisde(map));
		}
		String sql = "having";
		String averageTurnoverMin = map.get("averageTurnoverMin") + "";
		String averageTurnoverMax = map.get("averageTurnoverMax") + "";
		String addTimeBegin = map.get("addTimeBegin") + "";
		String addTimeEnd = map.get("addTimeEnd") + "";
		if (StringUtil.isNotNullAndEmpty(averageTurnoverMin) && StringUtil.isNotNullAndEmpty(averageTurnoverMax)) {
			sql += " (sum(t1.turnover)/sum(t1.quantity)) >= " + averageTurnoverMin + " and (sum(t1.turnover)/sum(t1.quantity)) <= "
					+ averageTurnoverMax;
			map.put("averageTurnover", sql);
		} else if (StringUtil.isNotNullAndEmpty(averageTurnoverMin) && !StringUtil.isNotNullAndEmpty(averageTurnoverMax)) {
			sql += " (sum(t1.turnover)/sum(t1.quantity)) >= " + averageTurnoverMin;
			map.put("averageTurnover", sql);
		} else if (!StringUtil.isNotNullAndEmpty(averageTurnoverMin) && StringUtil.isNotNullAndEmpty(averageTurnoverMax)) {
			sql += " (sum(t1.turnover)/sum(t1.quantity)) <= " + averageTurnoverMax;
			map.put("averageTurnover", sql);
		}
		if (StringUtil.isNotNullAndEmpty(addTimeBegin)) {
			map.put("dayTimeBegin", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeBegin));
		}
		if (StringUtil.isNotNullAndEmpty(addTimeEnd)) {
			map.put("dayTimeEnd", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeEnd));
		}
		if (map.containsKey("supplierName") && map.get("supplierName") != null) {
			List<String> supplierIdList = supplierMapper.getByName(map.get("supplierName") + "").stream().map(s -> s.getId())
					.collect(Collectors.toList());
			if (supplierIdList != null && supplierIdList.size() > 0) {
				map.put("supplierIdList", supplierIdList);
			}
		}
		return map;
	}

	@Override
	public void adaptConvert(PlantItemDailyVo s, HashMap<String, Object> paramMap) {

		ItemBase item = itemBaseMapper.getById(s.getItemBaseId());
		if (item != null) {
			s.setName(item.getName());
			s.setSpec(item.getSpec());
			s.setBarCode(item.getMdseId());
		}
		ItemCatelog one = itemCatelogMapper.getById(s.getClassOneId());
		if (one != null) {
			s.setClassifi_one(one.getName());
		}
		ItemCatelog Two = itemCatelogMapper.getById(s.getClassTwoId());
		if (Two != null) {
			s.setClassifi_two(Two.getName());
		}
	}
}
