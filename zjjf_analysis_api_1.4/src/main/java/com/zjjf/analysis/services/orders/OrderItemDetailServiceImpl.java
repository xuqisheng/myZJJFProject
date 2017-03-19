package com.zjjf.analysis.services.orders;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.orders.SpOrderDetailVo;
import com.zjjf.analysis.beans.analysis.store.Store;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderDetailMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.producer.orders.IOrdersItemDetailService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;

@Service(version = "1.0.0")
public class OrderItemDetailServiceImpl extends AbstractModelService<SpOrderDetailVo> implements IOrdersItemDetailService<SpOrderDetailVo> {

	@Autowired
	private AnalysisSpOrderDetailMapper analysisSpOrderDetailMapper;

	@Autowired
	private StoreMapper storeMapper;

	@Override
	public List<SpOrderDetailVo> getDataList(HashMap<String, Object> paramMap) {

		return analysisSpOrderDetailMapper.getData(appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return analysisSpOrderDetailMapper.getDataCount(appendParam(paramMap));
	}

	@Override
	public List<SpOrderDetailVo> getDataExcel(HashMap<String, Object> paramMap) {

		return analysisSpOrderDetailMapper.getDataExcel(paramMap);
	}

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> paramMap) {

		paramMap.put("dayTimeBegin", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(paramMap.get("addTimeBegin") + ""));
		paramMap.put("dayTimeEnd", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(paramMap.get("addTimeEnd") + ""));
		return paramMap;
	}

	@Override
	public void adaptConvert(SpOrderDetailVo s, HashMap<String, Object> paramMap) {

		Store store = storeMapper.getById(s.getStoreId());
		if (store != null) {
			s.setAddress(store.getAddress());
			s.setStoreName(store.getName());
			s.setMobile(store.getMobile());
		}
	}
}
