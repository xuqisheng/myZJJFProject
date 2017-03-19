package com.zjjf.analysis.services.store;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.store.Store;
import com.zjjf.analysis.beans.analysis.store.StoreDailyVo;
import com.zjjf.analysis.mapper.analysis.StoreDailyMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.producer.store.IStoreDailyService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;
import com.zjjf.analysis.utils.StringUtil;

@Service(version = "1.0.0")
public class StoreDailyServiceImpl extends AbstractModelService<StoreDailyVo> implements IStoreDailyService<StoreDailyVo> {

	@Autowired
	private StoreDailyMapper storeDailyMapper;

	@Autowired
	private StoreMapper storeMapper;

	@Override
	public List<StoreDailyVo> getDataList(HashMap<String, Object> paramMap) {

		return storeDailyMapper.getData(appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return storeDailyMapper.getDataCount(appendParam(paramMap));
	}

	@Override
	public List<StoreDailyVo> getDataExcel(HashMap<String, Object> paramMap) {

		return storeDailyMapper.getDataExcel(appendParam(paramMap));
	}

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> map) {

		String sql = "having";
		String turnoverMin = map.get("turnoverMin") + "";
		String turnoverMax = map.get("turnoverMax") + "";
		String addTimeBegin = map.get("addTimeBegin") + "";
		String addTimeEnd = map.get("addTimeEnd") + "";
		if (StringUtil.isNotNullAndEmpty(turnoverMin) && StringUtil.isNotNullAndEmpty(turnoverMax)) {
			sql += " sum(t1.turnover) >= " + turnoverMin + " and sum(t1.turnover) <= " + turnoverMax;
			map.put("turnover", sql);
		} else if (StringUtil.isNotNullAndEmpty(turnoverMin) && !StringUtil.isNotNullAndEmpty(turnoverMax)) {
			sql += " sum(t1.turnover) >= " + turnoverMin;
			map.put("turnover", sql);
		} else if (!StringUtil.isNotNullAndEmpty(turnoverMin) && StringUtil.isNotNullAndEmpty(turnoverMax)) {
			sql += " sum(t1.turnover) <= " + turnoverMax;
			map.put("turnover", sql);
		}
		if (StringUtil.isNotNullAndEmpty(addTimeBegin)) {
			map.put("dayTimeBegin", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeBegin));
		}
		if (StringUtil.isNotNullAndEmpty(addTimeEnd)) {
			map.put("dayTimeEnd", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeEnd));
		}
		return map;
	}

	@Override
	public void adaptConvert(StoreDailyVo s, HashMap<String, Object> paramMap) {

		Store store = storeMapper.getById(s.getStoreId());
		if(store != null){
			s.setMobile(store.getMobile());
			s.setAddress(store.getAddress());
		}
	}
}
