package com.zjjf.analysis.services.ajie;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.vo.supplier.SupplierFreeVo;
import com.zjjf.analysis.mapper.analysis.SupplierTurnoverMapper;
import com.zjjf.analysis.producer.ajie.ISupplierTurnoverService;
import com.zjjf.analysis.producer.base.AbstractTimeParam;

@Service(version = "1.0.0")
public class SupplierTurnoverServiceImpl extends AbstractTimeParam<SupplierFreeVo> implements ISupplierTurnoverService<SupplierFreeVo> {

	@Autowired
	private SupplierTurnoverMapper supplierTurnoverMapper;

	@Override
	public List<HashMap<String, Object>> getData(HashMap<String, Object> paramMap) {

		return supplierTurnoverMapper.getDataByParam(this.appendParam(paramMap)).stream().map(s -> mapToObject(s)).collect(Collectors.toList());
	}

	@Override
	public SupplierFreeVo mapToObject(HashMap<String, Object> map) {

		return null;
	}

	@Override
	public HashMap<String, Object> mapToObject(SupplierFreeVo vo) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("turnover", vo.getTodayTurnover());
		map.put("supplierId", vo.getSupplierId());
		map.put("supplierCode", vo.getSupplierCode());
		return map;
	}

	private HashMap<String, Object> appendParam(HashMap<String, Object> paramMap) {

		HashMap<String, Object> map = this.paramTimeConver(paramMap);
		map.put("supplierId", paramMap.get("supplierId"));
		map.put("supplierCode", paramMap.get("supplierCode"));
		map.put("cityId", paramMap.get("cityId"));
		map.put("areaId", paramMap.get("areaId"));
		map.put("areaIdList", paramMap.get("areaIdList"));
		map.put("orderType", paramMap.get("orderType"));
		System.out.println("ajie================================= paramMap: " + map);
		return map;
	}

}
