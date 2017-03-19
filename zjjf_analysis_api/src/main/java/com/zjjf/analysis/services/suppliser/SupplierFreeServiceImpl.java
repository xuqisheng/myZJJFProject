package com.zjjf.analysis.services.suppliser;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.SpGroup;
import com.zjjf.analysis.beans.analysis.supplier.Supplier;
import com.zjjf.analysis.beans.vo.supplier.SupplierFreeVo;
import com.zjjf.analysis.mapper.analysis.SupplierFreePageMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.producer.orders.ISupplierFreeService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;

@Service(version = "1.0.0")
public class SupplierFreeServiceImpl extends AbstractModelService<SupplierFreeVo> implements ISupplierFreeService<SupplierFreeVo> {

	@Autowired
	private RegionMapper regionMapper;
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	private SpGroupMapper spGroupMapper;
	
	@Autowired
	private SupplierFreePageMapper supplierFreePageMapper;

	@Override
	public List<SupplierFreeVo> getDataList(HashMap<String, Object> paramMap) {

		return supplierFreePageMapper.getData(appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return supplierFreePageMapper.getDataCount(appendParam(paramMap));
	}

	@Override
	public List<SupplierFreeVo> getDataExcel(HashMap<String, Object> paramMap) {

		return supplierFreePageMapper.getDataExcel(appendParam(paramMap));
	}

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> map) {

		map.put("dayTime", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(map.get("dayTime") + ""));
		if (map.containsKey("supplierName") && map.get("supplierName") != null) {
			List<String> supplierIdList = supplierMapper.getByName(map.get("supplierName") + "").stream().map(s -> s.getId()).collect(Collectors.toList());
			if (supplierIdList != null && supplierIdList.size() > 0) {
				map.put("spIdList", supplierIdList);
			}
		}
		return map;
	}

	@Override
	public void adaptConvert(SupplierFreeVo t, HashMap<String, Object> paramMap) {
		
		Supplier supplier = supplierMapper.getById(t.getSupplierId());
		if(supplier != null){
			t.setSupplierName(supplier.getSupplierName());
		}
		SpGroup spGroup = spGroupMapper.getById(t.getSpGroupId());
		if(spGroup != null){
			t.setSpGroupName(spGroup.getName());
		}
		t.setCityName(regionMapper.getById(t.getCityId()).getName());
		t.setAreaName(regionMapper.getById(t.getAreaId()).getName());
	}
}
