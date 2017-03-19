package com.zjjf.analysis.services.ajie;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.orders.SpOrderDetail;
import com.zjjf.analysis.mapper.origin.SpOrderDetailMapper;
import com.zjjf.analysis.producer.ajie.ISpOrderDetailService;

@Service(version = "1.0.0")
public class SpOrderDetailServiceImpl implements ISpOrderDetailService<SpOrderDetail> {

	@Autowired
	private SpOrderDetailMapper spOrderDetailMapper;

	@Override
	public List<HashMap<String, Object>> getData(HashMap<String, Object> paramMap) {

		return null;
	}

	@Override
	public SpOrderDetail mapToObject(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> mapToObject(SpOrderDetail t) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("brand", t.getBrand());
		map.put("name", t.getName());
		// map.put("mdseId", t.getMdseId());
		map.put("spec", t.getSpec());
		// map.put("pkg", t..getPkg());
		// map.put("areaPrice", t.getAreaPrice());
		map.put("quantity", t.getQuantity());
		map.put("price", t.getPrice());
		return map;
	}

	@Override
	public List<HashMap<String, Object>> getOrderDetail(String orderNo) {

		return spOrderDetailMapper.getItemByOrderId(orderNo).stream().map(s -> mapToObject(s)).collect(Collectors.toList());
	}
}
