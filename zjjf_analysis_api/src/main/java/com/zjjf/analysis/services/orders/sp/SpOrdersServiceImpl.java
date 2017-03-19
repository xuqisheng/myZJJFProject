package com.zjjf.analysis.services.orders.sp;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.store.Store;
import com.zjjf.analysis.beans.vo.orders.sporder.SpOrderVo;
import com.zjjf.analysis.mapper.analysis.SpOrderMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.producer.orders.ISpOrdersService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;

@Service(version = "1.0.0")
public class SpOrdersServiceImpl extends AbstractModelService<SpOrderVo> implements ISpOrdersService<SpOrderVo> {

	@Autowired
	private SpOrderMapper spOrderMapper;

	@Autowired
	private StoreMapper storeMapper;

	@Override
	public List<SpOrderVo> getDataList(HashMap<String, Object> paramMap) {

		return spOrderMapper.getData(appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return spOrderMapper.getDataCount(appendParam(paramMap));
	}

	@Override
	public List<SpOrderVo> getDataExcel(HashMap<String, Object> paramMap) {

		return spOrderMapper.getDataExcel(appendParam(paramMap));
	}

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> paramMap) {

		paramMap.put("dayTimeBegin", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(paramMap.get("dayTimeBegin") + ""));
		paramMap.put("dayTimeEnd", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(paramMap.get("dayTimeEnd") + ""));
		return paramMap;
	}

	@Override
	public void adaptConvert(SpOrderVo s, HashMap<String, Object> paramMap) {

		Store store = storeMapper.getById(s.getStoreId());
		if (store != null) {
			s.setMobile(store.getMobile());
			s.setAddress(store.getAddress());
		}
	}
}
